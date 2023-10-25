package com.example.eyeOpeningKotlin.ui.ugcdetail

import androidx.lifecycle.ViewModel
import com.example.eyeOpeningKotlin.logic.model.CommunityRecommend

class UgcDetailViewModel: ViewModel() {

    var dataList: List<CommunityRecommend.Item>? = null

    var itemPosition: Int = -1
}