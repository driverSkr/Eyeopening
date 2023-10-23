package com.example.eyeOpeningKotlin.ui.home.daily

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eyeOpeningKotlin.logic.model.Daily
import com.example.eyeOpeningKotlin.ui.common.holder.RecyclerViewHelp

class DailyAdapter(val fragment: DailyFragment) : PagingDataAdapter<Daily.Item, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun getItemViewType(position: Int) = RecyclerViewHelp.getItemViewType(getItem(position)!!)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    companion object {

        const val TAG = "DailyAdapter"

        const val DEFAULT_LIBRARY_TYPE = "DEFAULT"
        const val NONE_LIBRARY_TYPE = "NONE"
        const val DAILY_LIBRARY_TYPE = "DAILY"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Daily.Item>() {

            override fun areItemsTheSame(oldItem: Daily.Item, newItem: Daily.Item) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Daily.Item, newItem: Daily.Item) = oldItem == newItem

        }
    }
}