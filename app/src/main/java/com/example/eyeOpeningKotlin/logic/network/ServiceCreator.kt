package com.example.eyeOpeningKotlin.logic.network

import android.os.Build
import com.example.eyeOpeningKotlin.extension.logV
import com.example.eyeOpeningKotlin.extension.screenPixel
import com.example.eyeOpeningKotlin.logic.network.api.MainPageService
import com.example.eyeOpeningKotlin.ui.common.callback.GsonTypeAdapterFactory
import com.example.eyeOpeningKotlin.util.GlobalUtil
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.*

/**
 * 网络基础服务配置。
 *
 * @author driverSkr
 * @since  2023/10/20
 */
object ServiceCreator {

    const val BASE_URL = "http://baobab.kaiyanapp.com/"

    val httpClient: OkHttpClient = OkHttpClient.Builder()
        /**记录请求和响应日志**/
        .addInterceptor(LoggingInterceptor())
        /**用于添加自定义请求头，例如模型信息、用户代理和修改时间**/
        .addInterceptor(HeaderInterceptor())
        /**用于添加查询参数，例如设备信息、系统版本**/
        .addInterceptor(BasicParamsInterceptor())
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        /**如不添加.client配置，则Retrofit会生成一个默认的OkHttpClient实例**/
        .client(httpClient)
        /**用于将响应转换为字符串**/
        .addConverterFactory(ScalarsConverterFactory.create())
        /**用于将JSON响应转换为对象**/
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapterFactory(GsonTypeAdapterFactory()).create()))

    private val retrofit = builder.build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    /**记录请求和响应日志**/
    class LoggingInterceptor: Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val t1 = System.nanoTime()
            logV(TAG,"Sending request: ${originalRequest.url()} \n ${originalRequest.headers()}")

            val response = chain.proceed(originalRequest)

            val t2 = System.nanoTime()
            logV(TAG,"Received response for  ${response.request().url()} in ${(t2 - t1) / 1e6} ms\n ${response.headers()}")
            return response
        }

        companion object {
            const val TAG = "LoggingInterceptor"
        }
    }

    /**用于添加自定义请求头，例如模型信息、用户代理和修改时间**/
    class HeaderInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder().apply {
                header("model","Android")
                header("If-Modified-Since", "${Date()}")
                header("User-Agent", System.getProperty("http.agent") ?: "unknown")
            }.build()
            return chain.proceed(request)
        }
    }

    /**用于添加查询参数，例如设备信息、系统版本**/
    class BasicParamsInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url()
            val url = originalHttpUrl.newBuilder().apply {
                addQueryParameter("uuid", GlobalUtil.getDeviceSerial())
                //针对开眼官方【首页推荐 】api 变动， 需要单独做处理。原因：附加 vc、vn 这两个字段后，请求接口无响应。
                if (!originalHttpUrl.toString().contains(MainPageService.HOMEPAGE_RECOMMEND_URL)) {
                    addQueryParameter("vc", GlobalUtil.eyeopeningVersionCode.toString())
                    addQueryParameter("vn", GlobalUtil.eyeopeningVersionName)
                }
                addQueryParameter("size", screenPixel())
                addQueryParameter("deviceModel", GlobalUtil.deviceModel)
                addQueryParameter("first_channel", GlobalUtil.deviceBrand)
                addQueryParameter("last_channel", GlobalUtil.deviceBrand)
                addQueryParameter("system_version_code", "${Build.VERSION.SDK_INT}")
            }.build()
            val request = originalRequest.newBuilder().url(url).build()
            return chain.proceed(request)
        }
    }
}