package com.example.eyeOpeningKotlin.ui.search

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eyeOpeningKotlin.Const
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.FragmentSearchBinding
import com.example.eyeOpeningKotlin.extension.logW
import com.example.eyeOpeningKotlin.extension.setDrawable
import com.example.eyeOpeningKotlin.extension.showToast
import com.example.eyeOpeningKotlin.extension.visibleAlphaAnimation
import com.example.eyeOpeningKotlin.ui.common.ui.BaseActivity
import com.example.eyeOpeningKotlin.ui.common.ui.BaseFragment
import com.example.eyeOpeningKotlin.util.InjectorUtil
import com.umeng.analytics.MobclickAgent

/**
 * 搜索界面。
 *
 * @author driverSkr
 * @since  2023/10/20
 * @codeReview 2023/11/7
 */
class SearchFragment: BaseFragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding: FragmentSearchBinding
        get() = _binding!!

    /**使用 Kotlin 的 by lazy 延迟初始化属性的方式，用于获取一个 PlaceViewModel 的实例**/
    private val viewModel by lazy { ViewModelProvider(this, InjectorUtil.getSearchViewModelFactory())[SearchViewModel::class.java] }

    private lateinit var adapter: HotSearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.llSearch.visibleAlphaAnimation(500) //显示view，带有渐显动画效果。
        //设置TextView图标
        binding.etQuery.setDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_search_gray_17dp),14f,14f)
        binding.etQuery.setOnEditorActionListener(EditorActionListener(activity,binding))
        binding.tvCancel.setOnClickListener {
            hideSoftKeyboard()  //隐藏软键盘
            removeFragment(activity,this)
        }
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        adapter = HotSearchAdapter(this,viewModel.dataList)
        binding.recyclerView.adapter = adapter
        viewModel.onRefresh()
        observe()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideSoftKeyboard()
        _binding = null
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (enter) {
            AnimationUtils.loadAnimation(activity, R.anim.anl_push_up_in)
        } else {
            AnimationUtils.loadAnimation(activity, R.anim.anl_push_top_out)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observe() {
        viewModel.dataListLiveData.observe(viewLifecycleOwner, Observer { result ->
            //通过输入框打开软键盘
            binding.etQuery.showSoftKeyboard()
            val response = result.getOrNull()
            if (response == null) {
                /**打印异常的堆栈信息，并提前返回，不继续处理数据**/
                result.exceptionOrNull()?.printStackTrace()
                return@Observer
            }
            if (response.isNullOrEmpty() && viewModel.dataList.isEmpty()) {
                return@Observer
            }
            if (response.isNullOrEmpty() && viewModel.dataList.isNotEmpty()) {
                return@Observer
            }
            //清空 viewModel.dataList，准备填充新的数据
            viewModel.dataList.clear()
            viewModel.dataList.addAll(response)
            adapter.notifyDataSetChanged()
        })
    }

    /**
     * 隐藏软键盘
     */
    private fun hideSoftKeyboard() {
        try {
            activity.currentFocus?.run {
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        } catch (e: Exception) {
            logW(TAG, e.message, e)
        }
    }

    /**
     * 拉起软键盘
     */
    private fun View.showSoftKeyboard() {
        try {
            this.isFocusable = true
            this.isFocusableInTouchMode = true
            this.requestFocus()
            val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.showSoftInput(this, 0)
        } catch (e: Exception) {
            logW(TAG, e.message, e)
        }
    }

    class EditorActionListener(val activity: Context, val binding: FragmentSearchBinding) : TextView.OnEditorActionListener {
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                MobclickAgent.onEvent(activity, Const.Mobclick.EVENT3)
                if (binding.etQuery.text.toString().isEmpty()) {
                    R.string.input_keywords_tips.showToast()
                    return false
                }
                R.string.currently_not_supported.showToast()
                return true
            }
            return true
        }
    }

    companion object {

        /**
         * 切换Fragment，会加入回退栈。
         */
        fun switchFragment(activity: Activity) {
            (activity as BaseActivity).supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SearchFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        /**
         * 先移除Fragment，并将Fragment从堆栈弹出。
         */
        fun removeFragment(activity: Activity, fragment: Fragment) {
            (activity as BaseActivity).supportFragmentManager.run {
                beginTransaction().remove(fragment).commitAllowingStateLoss()
                popBackStack()
            }
        }
    }
}