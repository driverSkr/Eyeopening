package com.example.eyeOpeningKotlin.ui.home.discovery

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.extension.invisible
import com.example.eyeOpeningKotlin.extension.load
import com.example.eyeOpeningKotlin.extension.visible
import com.example.eyeOpeningKotlin.logic.model.Discovery
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class DiscoveryAdapter(val fragment: DiscoveryFragment) : PagingDataAdapter<Discovery.Item, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        const val TAG = "DiscoveryAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Discovery.Item>() {

            override fun areItemsTheSame(oldItem: Discovery.Item, newItem: Discovery.Item) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Discovery.Item, newItem: Discovery.Item) = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    class HorizontalScrollCardAdapter : BaseBannerAdapter<Discovery.ItemX, HorizontalScrollCardAdapter.ViewHolder>() {

        class ViewHolder(val view: View) : BaseViewHolder<Discovery.ItemX>(view) {

            override fun bindData(item: Discovery.ItemX, position: Int, pageSize: Int) {
                val ivPicture = findView<ImageView>(R.id.ivPicture)
                val tvLabel = findView<TextView>(R.id.tvLabel)
                if (item.data.label?.text.isNullOrEmpty()) tvLabel.invisible() else tvLabel.visible()
                tvLabel.text = item.data.label?.text ?: ""
                ivPicture.load(item.data.image, 4f)
            }
        }

        override fun getLayoutId(viewType: Int) = R.layout.item_banner_item_type

        override fun createViewHolder(view: View, viewType: Int) = ViewHolder(view)

        override fun onBind(holder: ViewHolder, data: Discovery.ItemX, position: Int, pageSize: Int) {
            holder.bindData(data, position, pageSize)
        }
    }
}