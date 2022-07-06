package com.study.xml_view.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.study.common.data.model.ArticleModel
import com.study.common.data.model.BannerModel
import com.study.xml_view.databinding.ItemArticleBinding
import com.study.xml_view.databinding.ItemBannerBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class RecommendAdapter : PagingDataAdapter<Any, RecyclerView.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any) = false

            override fun areContentsTheSame(oldItem: Any, newItem: Any) = false
        }
    }

    class ArticleViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)
    class BannerViewHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        if (getItem(position) is List<*>) return 1
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return BannerViewHolder(
                ItemBannerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        return ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemData = getItem(position)
        if (itemData is List<*> && holder is BannerViewHolder) {
            holder.binding.banner.setAdapter(object :
                BannerImageAdapter<BannerModel>(itemData as MutableList<BannerModel>) {
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: BannerModel?,
                    position: Int,
                    size: Int
                ) {
                    //图片加载自己实现
                    Glide.with(holder!!.itemView)
                        .load(data!!.imagePath)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                        .into(holder.imageView)
                }
            }).indicator = CircleIndicator(holder.binding.root.context)
        } else if (itemData is ArticleModel && holder is ArticleViewHolder) {
            holder.binding.apply {
                tvTitle.text = itemData.title
                tvAuthor.text =
                    if (itemData.author.isNotEmpty()) "作者：${itemData.author}" else "分享者：${itemData.shareUser}"
                tvClassify.text = "分类：${itemData.superChapterName}"
                tvTime.text = itemData.formatTime
            }
        }

    }

}
