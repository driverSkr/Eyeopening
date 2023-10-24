package com.example.eyeOpeningKotlin.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.ActivityMainBinding
import com.example.eyeOpeningKotlin.event.MessageEvent
import com.example.eyeOpeningKotlin.event.RefreshEvent
import com.example.eyeOpeningKotlin.event.SwitchPagesEvent
import com.example.eyeOpeningKotlin.extension.setOnClickListener
import com.example.eyeOpeningKotlin.extension.showToast
import com.example.eyeOpeningKotlin.ui.common.ui.BaseActivity
import com.example.eyeOpeningKotlin.ui.community.CommunityFragment
import com.example.eyeOpeningKotlin.ui.home.HomePageFragment
import com.example.eyeOpeningKotlin.ui.home.commend.CommendFragment
import com.example.eyeOpeningKotlin.ui.login.LoginActivity
import com.example.eyeOpeningKotlin.ui.mine.MineFragment
import com.example.eyeOpeningKotlin.ui.notification.NotificationFragment
import com.example.eyeOpeningKotlin.util.DialogAppraiseTipsWorker
import com.example.eyeOpeningKotlin.util.GlobalUtil
import org.greenrobot.eventbus.EventBus

/**
 * EyeOpening的主界面。
 *
 * @author driverSkr
 * @since  2023/10/19
 */
class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding: ActivityMainBinding get() = _binding!!

    //用于跟踪上次按下返回按钮的时间，以实现双击返回按钮退出应用的功能
    private var backPressTime = 0L

    /**首页界面*/
    private var homePageFragment: HomePageFragment? = null
    /**社区界面*/
    private var communityFragment: CommunityFragment? = null
    /**通知界面*/
    private var notificationFragment: NotificationFragment? = null
    /**我的界面*/
    private var mineFragment: MineFragment? = null

    private val fragmentManager: FragmentManager by lazy { supportFragmentManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        /**getRoot()函数可以得到activity_main.xml中根元素的实例*/
        setContentView(binding.root)/**调用了setupViews()方法*/
    }

    override fun onDestroy() {
        super.onDestroy()
        /**释放 _binding，以避免内存泄漏。*/
        _binding = null
    }

    /**
     * 切换界面
     */
    override fun setupViews() {
        observe()
        setOnClickListener(
            binding.navigationBar.btnHomePage, binding.navigationBar.btnCommunity, binding.navigationBar.btnNotification, binding.navigationBar.ivRelease,
            binding.navigationBar.btnMine){
            when(this){
                binding.navigationBar.btnHomePage -> {
                    notificationUiRefresh(0)
                    setTabSelection(0)
                }
                binding.navigationBar.btnCommunity -> {
                    notificationUiRefresh(1)
                    setTabSelection(1)
                }
                binding.navigationBar.btnNotification -> {
                    notificationUiRefresh(2)
                    setTabSelection(2)
                }
                binding.navigationBar.ivRelease -> {
                    LoginActivity.start(this@MainActivity)
                }
                binding.navigationBar.btnMine -> {
                    notificationUiRefresh(3)
                    setTabSelection(3)
                }
            }
        }
        setTabSelection(0)
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
        when{
            messageEvent is SwitchPagesEvent && CommendFragment::class.java == messageEvent.activityClass -> {
                binding.navigationBar.btnCommunity.performClick()
            }
            else -> {}
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStack()
        } else {
            processBackPressed()
        }
    }

    private fun processBackPressed() {
        val now = System.currentTimeMillis()
        if (now - backPressTime > 2000) {
            String.format(GlobalUtil.getString(R.string.press_again_to_exit), GlobalUtil.appName).showToast()
            backPressTime = now
        } else {
            super.onBackPressed()
        }
    }

    private fun setTabSelection(index: Int) {
        clearAllSelected()
        fragmentManager.beginTransaction().apply {
            hideFragments(this)
            when (index) {
                0 -> {
                    binding.navigationBar.ivHomePage.isSelected = true
                    binding.navigationBar.tvHomePage.isSelected = true
                    if (homePageFragment == null) {
                        homePageFragment = HomePageFragment.newInstance()
                        add(R.id.homeActivityFragContainer, homePageFragment!!)
                    } else {
                        show(homePageFragment!!)
                    }
                }
                1 -> {
                    binding.navigationBar.ivCommunity.isSelected = true
                    binding.navigationBar.tvCommunity.isSelected = true
                    if (communityFragment == null) {
                        communityFragment = CommunityFragment.newInstance()
                        add(R.id.homeActivityFragContainer, communityFragment!!)
                    } else {
                        show(communityFragment!!)
                    }
                }
                2 -> {
                    binding.navigationBar.ivNotification.isSelected = true
                    binding.navigationBar.tvNotification.isSelected = true
                    if (notificationFragment == null) {
                        notificationFragment = NotificationFragment.newInstance()
                        add(R.id.homeActivityFragContainer, notificationFragment!!)
                    } else {
                        show(notificationFragment!!)
                    }
                }
                3 -> {
                    binding.navigationBar.ivMine.isSelected = true
                    binding.navigationBar.tvMine.isSelected = true
                    if (mineFragment == null) {
                        mineFragment = MineFragment.newInstance()
                        add(R.id.homeActivityFragContainer, mineFragment!!)
                    } else {
                        show(mineFragment!!)
                    }
                }
                else -> {
                    binding.navigationBar.ivHomePage.isSelected = true
                    binding.navigationBar.tvHomePage.isSelected = true
                    if (homePageFragment == null) {
                        homePageFragment = HomePageFragment.newInstance()
                        add(R.id.homeActivityFragContainer, homePageFragment!!)
                    } else {
                        show(homePageFragment!!)
                    }
                }
            }
        }.commitAllowingStateLoss()
    }

    private fun clearAllSelected() {
        binding.navigationBar.ivHomePage.isSelected = false
        binding.navigationBar.tvHomePage.isSelected = false
        binding.navigationBar.ivCommunity.isSelected = false
        binding.navigationBar.tvCommunity.isSelected = false
        binding.navigationBar.ivNotification.isSelected = false
        binding.navigationBar.tvNotification.isSelected = false
        binding.navigationBar.ivMine.isSelected = false
        binding.navigationBar.tvMine.isSelected = false
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        transaction.run {
            if (homePageFragment != null) hide(homePageFragment!!)
            if (communityFragment != null) hide(communityFragment!!)
            if (notificationFragment != null) hide(notificationFragment!!)
            if (mineFragment != null) hide(mineFragment!!)
        }
    }

    private fun notificationUiRefresh(selectionIndex: Int) {
        when (selectionIndex) {
            0 -> {
                if (binding.navigationBar.ivHomePage.isSelected) EventBus.getDefault().post(RefreshEvent(HomePageFragment::class.java))
            }
            1 -> {
                if (binding.navigationBar.ivCommunity.isSelected) EventBus.getDefault().post(RefreshEvent(CommunityFragment::class.java))
            }
            2 -> {
                if (binding.navigationBar.ivNotification.isSelected) EventBus.getDefault().post(RefreshEvent(NotificationFragment::class.java))
            }
            3 -> {
                if (binding.navigationBar.ivMine.isSelected) EventBus.getDefault().post(RefreshEvent(MineFragment::class.java))
            }
        }
    }

    private fun observe() {
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(DialogAppraiseTipsWorker.showDialogWorkRequest.id).observe(this) { workInfo ->
            if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                WorkManager.getInstance(this).cancelAllWork()
            } else if (workInfo.state == WorkInfo.State.RUNNING) {
                if (isActive) {
                    DialogAppraiseTipsWorker.showDialog(this)
                    WorkManager.getInstance(this).cancelAllWork()
                }
            }
        }
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}