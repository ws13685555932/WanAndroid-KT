package com.example.wanandroid_kt.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.wanandroid_kt.MyApplication
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.view.GlideRoundTransform


/**
 * des 图片加载代理类
 *
 * @author zs
 * @date 2020-03-14
 */
class ImageLoad{
    companion object{

        /**
         * 默认加载
         */
        fun load(imageView: ImageView,url:String?){
            val factory =
                DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            url.let {
                Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .transition(withCrossFade())
                    //.placeholder(R.mipmap.image_holder)
                    //.error(R.mipmap.image_holder)
                    .into(imageView)
            }
        }

        /**
         * 加载圆角图片
         */
        fun loadRadius(imageView: ImageView,url:String?,round:Int){

            val factory =
                DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            url?.let {
                Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .transition(withCrossFade())
                    .transform(
                        GlideRoundTransform(
                            imageView.context,
                            round
                        )
                    )
                    //.placeholder(R.mipmap.internet_error)
                    .into(imageView)
            }

        }

        /**
         * 加载圆形图片
         */
        fun loadRound(imageView: ImageView,url:String,round:Int){
            val factory =
                DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .transition(withCrossFade())
                .placeholder(R.mipmap.internet_error)
                .transform(RoundedCorners(8))
                .into(imageView)
        }

        /**
         * 清除缓存
         */
        fun clearCache(){
            Glide.get(MyApplication.mContext).clearMemory()
            System.gc()
        }
    }
}