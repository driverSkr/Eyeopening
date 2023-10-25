package com.example.eyeOpeningKotlin.ui.home.commend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.FragmentRefreshLayoutBinding
import com.example.eyeOpeningKotlin.event.MessageEvent
import com.example.eyeOpeningKotlin.event.RefreshEvent
import com.example.eyeOpeningKotlin.extension.showToast
import com.example.eyeOpeningKotlin.ui.common.ui.BaseFragment
import com.example.eyeOpeningKotlin.ui.common.ui.FooterAdapter
import com.example.eyeOpeningKotlin.util.GlobalUtil
import com.example.eyeOpeningKotlin.util.InjectorUtil
import com.example.eyeOpeningKotlin.util.ResponseHandler
import com.scwang.smart.refresh.layout.constant.RefreshState
import kotlinx.coroutines.launch

/**
 * 首页-推荐列表界面。
 *
 * @author driverSkr
 * @since  2023/10/19
 */
class CommendFragment: BaseFragment() {

    private var _binding: FragmentRefreshLayoutBinding? = null

    private val binding
        get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this, InjectorUtil.getHomePageCommendViewModelFactory())[CommendViewModel::class.java] }

    private lateinit var adapter: CommendAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRefreshLayoutBinding.inflate(layoutInflater, container, false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CommendAdapter(this)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.itemAnimator = null
        binding.recyclerView.adapter = adapter.withLoadStateFooter(FooterAdapter(adapter::retry))
        binding.refreshLayout.setOnRefreshListener { adapter.refresh() }
        addLoadStateListener()

        lifecycleScope.launch {
            viewModel.getPagingData().collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun loadFinished() {
        super.loadFinished()
        binding.refreshLayout.finishRefresh()
    }

    override fun loadFailed(msg: String?) {
        super.loadFailed(msg)
        binding.refreshLayout.finishRefresh()
        showLoadErrorView(msg ?: GlobalUtil.getString(R.string.unknown_error)) {
            startLoading()
            adapter.refresh()
        }
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
        if (messageEvent is RefreshEvent && javaClass == messageEvent.activityClass) {
            binding.refreshLayout.autoRefresh()
            if ((binding.recyclerView.adapter?.itemCount ?: 0) > 0) binding.recyclerView.scrollToPosition(0)
        }
    }

    private fun addLoadStateListener() {
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    loadFinished()
                    if (it.source.append.endOfPaginationReached) {
                        binding.refreshLayout.setEnableLoadMore(true)
                        binding.refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        binding.refreshLayout.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {
                    if (binding.refreshLayout.state != RefreshState.Refreshing) {
                        startLoading()
                    }
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    loadFailed(ResponseHandler.getFailureTips(state.error))
                }
            }
        }
        adapter.addLoadStateListener {
            when (it.append) {
                is LoadState.NotLoading -> {
                    if (it.source.append.endOfPaginationReached) {
                        binding.refreshLayout.setEnableLoadMore(true)
                        binding.refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        binding.refreshLayout.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {
                    val state = it.append as LoadState.Error
                    ResponseHandler.getFailureTips(state.error).showToast()
                }
            }
        }
    }

    companion object {

        fun newInstance() = CommendFragment()
    }
}