package com.example.eyeOpeningKotlin.ui.notification.interaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eyeOpeningKotlin.databinding.FragmentNotificationLoginTipsBinding
import com.example.eyeOpeningKotlin.ui.common.ui.BaseFragment
import com.example.eyeOpeningKotlin.ui.login.LoginActivity

/**
 * 通知-互动列表界面。
 *
 * @author driverSkr
 * @since  2023/10/20
 */
class InteractionFragment: BaseFragment() {

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

        fun newInstance() = InteractionFragment()
    }
}