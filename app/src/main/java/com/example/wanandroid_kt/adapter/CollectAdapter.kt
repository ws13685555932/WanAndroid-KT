package com.example.wanandroid_kt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.entity.Article
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class CollectAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_home_article) {

    interface OnCollectedListener{
        fun onCollected(position : Int)
    }

    private var onCollectedListener : OnCollectedListener? = null

    fun setOnCollectedListener(onCollectedListener: OnCollectedListener){
        this.onCollectedListener = onCollectedListener
    }


    override fun convert(holder: BaseViewHolder, item: Article) {
        item.run {
            holder.setText(R.id.tvTag, "")
            holder.setText(R.id.tvAuthor, author)
            holder.setText(R.id.tvDate, niceDate)
            holder.setText(R.id.tvTitle, title)
            holder.setText(R.id.tvChapterName, chapterName)
            holder.getView<ImageView>(R.id.ivCollect).setImageResource(R.mipmap.article_collect)
            val ivCollect  = holder.getView<ImageView>(R.id.ivCollect)
            ivCollect.clicks()
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe{
                    onCollectedListener?.onCollected(holder.adapterPosition)
                }

        }
    }
}