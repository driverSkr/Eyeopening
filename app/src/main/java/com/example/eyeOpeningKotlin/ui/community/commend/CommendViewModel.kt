package com.example.eyeOpeningKotlin.ui.community.commend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eyeOpeningKotlin.logic.MainPageRepository
import com.example.eyeOpeningKotlin.logic.model.CommunityRecommend
import kotlinx.coroutines.flow.Flow

/**
 * 操控社区界面数据
 */
class CommendViewModel(private val repository: MainPageRepository): ViewModel() {

    /**用于存储社区-推荐-项目的数据*/
    var dataList = ArrayList<CommunityRecommend.Item>()

    /**
     * 获取分页数据
     * @return Flow对象 : 这是 Kotlin 协程库中的一种数据流，用于支持异步数据流操作
     *          这个函数返回了一个 PagingData 对象，其中包含了 CommunityRecommend.Item 类型的数据。
     */
    fun getPagingData(): Flow<PagingData<CommunityRecommend.Item>> {
        /**
         * 获取社区推荐数据的分页数据
         * cachedIn(viewModelScope) : 这是使用 Paging 3（分页库）时的一种常见操作，用于在 ViewModel 的生命周期内缓存数据以提高性能
         * · cachedIn(viewModelScope) 表示将数据缓存在 ViewModel 的作用域内（通常与 ViewModel 的生命周期相关联）。
         *                            这意味着数据将在 ViewModel 存在的整个生命周期内被缓存，并且不会在不同的界面组件之间重复加载。
         * */
        return repository.getCommunityRecommendPagingData().cachedIn(viewModelScope)
    }
}