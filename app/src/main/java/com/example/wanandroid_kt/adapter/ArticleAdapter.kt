package com.example.wanandroid_kt.adapter

import android.text.Html
import android.text.TextUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.ext.getColor
import com.example.wanandroid_kt.utils.ImageLoad

/**
 * 文章适配器
 * @author zs
 * @date 2020-03-16修改
 */


class ArticleAdapter : BaseMultiItemQuickAdapter<Article, BaseViewHolder>() {

    init {
        addItemType(Constants.ITEM_ARTICLE, R.layout.item_home_article)
        addItemType(Constants.ITEM_ARTICLE_PIC,R.layout.item_project)
    }

    private var collectClickListener:OnCollectClickListener? = null

    fun setCollectClickListener(collectClickListener:OnCollectClickListener){
        this.collectClickListener = collectClickListener
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
//                    holder.getView<ImageView>(R.id.ivCollect)
//                        .apply {
//                            setOnClickListener(OnLimitClickHelper(OnLimitClickListener {
//                                collectClickListener?.onCollectClick(holder, holder.adapterPosition)
//                            }))
//                            if (item.collect) {
//                                setImageResource(R.mipmap.article_collect)
//                            }else{
//                                setImageResource(R.mipmap.article_un_collect)
//                            }
//                        }
                }
            }

            //带图片
            Constants.ITEM_ARTICLE_PIC->{
                item.apply {
                    ImageLoad.loadRadius(holder.getView(R.id.ivTitle),envelopePic,20)
                    holder.setText(R.id.tvTitle,title)
                    holder.setText(R.id.tvDes,desc)
                    holder.setText(R.id.tvNameData,"$niceDate | $author")
//                    holder.getView<ImageView>(R.id.ivCollect).apply {
//                        setOnClickListener(OnLimitClickHelper(OnLimitClickListener {
//                            collectClickListener?.onCollectClick(holder, holder.adapterPosition)
//                        }))
//                        if (item.collect) {
//                            setImageResource(R.mipmap.article_collect)
//                        }else{
//                            setImageResource(R.mipmap.article_un_collect)
//                        }
//                    }
                }
            }

        }
    }

    interface OnCollectClickListener{
        fun onCollectClick(helper: Any?, adapterPosition: Any)
    }
}