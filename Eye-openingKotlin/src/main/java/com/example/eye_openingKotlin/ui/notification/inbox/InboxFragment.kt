package com.example.eye_openingKotlin.ui.notification.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eye_openingKotlin.databinding.FragmentNotificationLoginTipsBinding
import com.example.eye_openingKotlin.ui.common.ui.BaseFragment
import com.example.eye_openingKotlin.ui.login.LoginActivity

/**
 * 通知-私信列表界面。
 *
 * @author vipyinzhiwei
 * @since  2020/5/1
 */
class InboxFragment : BaseFragment() {

    private var _binding: FragmentNotificationLoginTipsBinding? = null

    private val binding: FragmentNotificationLoginTipsBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNotificationLoginTipsBinding.inflate(inflater, container, false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLogin.setOnClickListener { LoginActivity.start(activity) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = InboxFragment()
    }

}
