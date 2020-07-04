package com.example.wanandroid_kt.adapter

import android.text.Html
import android.text.TextUtils
import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.ext.getColor
import com.example.wanandroid_kt.utils.ImageLoad
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

/**
 * 文章适配器
 * @author zs
 * @date 2020-03-16修改
 */


class ArticleAdapter : BaseMultiItemQuickAdapter<Article, BaseViewHolder>() , LoadMoreModule{

    init {
        addItemType(Constants.ITEM_ARTICLE, R.layout.item_home_article)
        addItemType(Constants.ITEM_ARTICLE_PIC,R.layout.item_project)
    }

    interface OnCollectedListener{
        fun onCollected(collected:Boolean, position : Int)
    }

    private var onCollectedListener : OnCollectedListener? = null

    fun setOnCollectedListener(onCollectedListener: OnCollectedListener){
        this.onCollectedListener = onCollectedListener
    }


    override fun convert(holder: BaseViewHolder, item: Article) {
        when(holder.itemViewType){
            //不带图片
            Constants.ITEM_ARTICLE ->{
                item.run {
                    if (type==1){
                        holder.setText(R.id.tvTag,"置顶 ")
                        holder.setTextColor(R.id.tvTag, R.color.red.getColor())
                    }else{
                        holder.setText(R.id.tvTag,"")
                    }
                    holder.setText(R.id.tvAuthor,if (!TextUtils.isEmpty(author))author else shareUser)
                    holder.setText(R.id.tvDate,niceDate)
                    holder.setText(R.id.tvTitle, Html.fromHtml(title))
                    holder.setText(R.id.tvChapterName,superChapterName)
                    if(item.collect){
                        holder.setImageResource(R.id.ivCollect, R.mipmap.article_collect)
                    }else{
                        holder.setImageResource(R.id.ivCollect, R.mipmap.article_un_collect)
                    }
                    val ivCollect  = holder.getView<ImageView>(R.id.ivCollect)
                    var collected = item.collect
                    ivCollect.clicks()
                        .throttleFirst(2, TimeUnit.SECONDS)
                        .subscribe{
                            collected = !collected
                            onCollectedListener?.onCollected(collected, holder.adapterPosition)
                        }
                }
            }

            //带图片
            Constants.ITEM_ARTICLE_PIC->{
                item.apply {
                    ImageLoad.loadRadius(holder.getView(R.id.ivTitle),envelopePic,20)
                    holder.setText(R.id.tvTitle,title)
                    holder.setText(R.id.tvDes,desc)
                    holder.setText(R.id.tvNameData,"$niceDate | $author")
                    if(item.collect){
                        holder.setImageResource(R.id.ivCollect, R.mipmap.article_collect)
                    }else{
                        holder.setImageResource(R.id.ivCollect, R.mipmap.article_un_collect)
                    }

                    val ivCollect  = holder.getView<ImageView>(R.id.ivCollect)
                    var collected = item.collect
                    ivCollect.clicks()
                        .throttleFirst(2, TimeUnit.SECONDS)
                        .subscribe{
                            collected = !collected
                            onCollectedListener?.onCollected(collected, holder.adapterPosition)
                        }
                }
            }

        }
    }
}