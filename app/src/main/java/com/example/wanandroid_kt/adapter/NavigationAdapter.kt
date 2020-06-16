package com.example.wanandroid_kt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.donkingliang.labels.LabelsView
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.entity.NaviEntity
import com.example.wanandroid_kt.view.OnSystemClickListener


class NavigationAdapter : BaseQuickAdapter<NaviEntity, BaseViewHolder>(R.layout.item_systemlist) {

    private var systemClickListener: OnSystemClickListener? = null

    fun setOnSystemClickListener(systemClickListener: OnSystemClickListener?) {
        this.systemClickListener = systemClickListener
    }

    override fun convert(holder: BaseViewHolder, item: NaviEntity) {
        item.let {
            holder.setText(R.id.tvTitle, item.name)
                .getView<LabelsView>(R.id.labelsView).apply {
                    setLabels(item.articles) { _, _, data ->
                        data.title
                    }
                    //标签的点击监听
                    setOnLabelClickListener { _, _, position ->
                        systemClickListener?.onCollectClick(
                            holder,
                            holder.adapterPosition,
                            position
                        )
                    }
                }
        }
    }

}