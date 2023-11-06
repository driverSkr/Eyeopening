package com.example.eyeOpeningKotlin.ui.common.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.extension.setOnClickListener
import com.example.eyeOpeningKotlin.extension.showToast
import com.example.eyeOpeningKotlin.ui.search.SearchFragment
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener

/**
 * Fragment基类，适用场景：页面含有ViewPager+TabLayout的界面。
 *
 * @author driverSkr
 * @since  2023/10/19
 */
abstract class BaseViewPagerFragment: BaseFragment() {

    /**日历图标*/
    private var ivCalendar: ImageView? = null
    /**搜索图标*/
    private var ivSearch: ImageView? = null
    /**用于显示可滑动的内容页面*/
    protected var viewPager: ViewPager2? = null
    /**用于显示标签页以允许用户切换不同的内容*/
    private var tabLayout: CommonTabLayout? = null
    /**页面切换回调，更新标签*/
    private var pageChangeCallback: PageChangeCallback? = null
    /**懒加载属性，用于初始化一个 VpAdapter 对象,用于为 ViewPager2 提供内容适配器,
    确保适配器对象在需要时被创建，以减少不必要的内存消耗*/
    protected val adapter: VpAdapter by lazy { VpAdapter(requireActivity()).apply { addFragments(createFragments) } }
    /**定义了在当前页面之外要保留多少个页面，以便在切换页面时不会被销毁
    默认值是 1，表示保留当前页面以及相邻的一个页面*/
    private var offscreenPageLimit = 1

    /**
     * 子类需要提供标签标题和要显示的 Fragment 列表
     */
    abstract val createTitles: ArrayList<CustomTabEntity>
    abstract val createFragments: Array<Fragment>

    /**
     * 在 Fragment 视图创建时被调用，其中调用了 setupViews() 方法，用于初始化和设置视图组件
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        pageChangeCallback?.run { viewPager?.unregisterOnPageChangeCallback(this) }
        pageChangeCallback = null
    }

    /**
     * 初始化和设置视图组件
     */
    open fun setupViews(){
        ivCalendar = rootView?.findViewById(R.id.ivCalendar)
        ivSearch = rootView?.findViewById(R.id.ivSearch)
        setOnClickListener(ivCalendar,ivSearch){
            if (this == ivCalendar) {
                R.string.currently_not_supported.showToast()
            } else if (this == ivSearch){
                SearchFragment.switchFragment(activity)
            }
        }
        initViewPager()
    }

    /**
     * 初始化 ViewPager2 和 TabLayout。它设置了 ViewPager2 的适配器、TabLayout 的标签数据和标签选中监听器
     */
    private fun initViewPager(){
        //查找布局中的 ViewPager2 和 TabLayout 控件
        viewPager = rootView?.findViewById(R.id.viewPager)
        tabLayout = rootView?.findViewById(R.id.tabLayout)

        //设置 ViewPager2 的 offscreenPageLimit 属性，以定义在当前页面之外要保留多少个页面
        viewPager?.offscreenPageLimit = offscreenPageLimit//1
        viewPager?.adapter = adapter//设置适配器
        tabLayout?.setTabData(createTitles)//设置标签数据
        //设置了 TabLayout 的标签选择监听器，用于监听标签页的选中事件
        tabLayout?.setOnTabSelectListener(object : OnTabSelectListener {

            override fun onTabSelect(position: Int) {
                //根据选中的标签切换 ViewPager 中的页面，确保标签页和内容页同步
                viewPager?.currentItem = position
            }

            override fun onTabReselect(position: Int) {
            }
        })
        pageChangeCallback = PageChangeCallback(tabLayout)
        //注册页面切换监听，更新选中标签
        viewPager?.registerOnPageChangeCallback(pageChangeCallback!!)
    }

    /**
     * 一个内部类，用于处理 ViewPager2 切换页面时，更新 TabLayout 中当前选中的标签
     */
    class PageChangeCallback(private val tabLayout: CommonTabLayout?): ViewPager2.OnPageChangeCallback(){

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            //更新标签
            tabLayout?.currentTab = position
        }
    }

    /**
     * 一个内部类，用于创建 ViewPager2 的适配器，负责加载不同的 Fragment 页面
     */
    class VpAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

        private val fragments = mutableListOf<Fragment>()

        fun addFragments(fragment: Array<Fragment>){
            fragments.addAll(fragment)
        }

        override fun getItemCount() = fragments.size

        override fun createFragment(position: Int) = fragments[position]
    }
}