package com.example.wanandroid_kt.adapter

import android.annotation.SuppressLint
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.entity.CoinRecord
import com.example.wanandroid_kt.ext.toDateTime
import kotlinx.android.synthetic.main.item_coin_list.view.*

class CoinAdapter : BaseQuickAdapter<CoinRecord, BaseViewHolder>(R.layout.item_coin_list), LoadMoreModule{
    @SuppressLint("SetTextI18n")
    override fun convert(holder: BaseViewHolder, item: CoinRecord) {
        holder.itemView.run {
            tvReason.text = item.reason
            tvTime.text = item.date.toDateTime("YYYY-MM-dd HH:mm:ss")
            tvPoint.text = "+${item.coinCount}"
        }
    }

}