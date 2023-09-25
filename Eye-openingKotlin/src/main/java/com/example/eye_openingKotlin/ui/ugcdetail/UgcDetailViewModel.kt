package com.example.eye_openingKotlin.ui.ugcdetail

import androidx.lifecycle.ViewModel
import com.example.eye_openingKotlin.logic.model.CommunityRecommend

class UgcDetailViewModel : ViewModel() {

    var dataList: List<CommunityRecommend.Item>? = null

    var itemPosition: Int = -1
}