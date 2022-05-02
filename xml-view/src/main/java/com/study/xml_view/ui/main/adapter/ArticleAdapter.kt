package com.study.xml_view.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.study.common.data.model.ArticleModel
import com.study.xml_view.R
import com.study.xml_view.databinding.ItemArticleBinding

class ArticleAdapter : PagingDataAdapter<ArticleModel, ArticleAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ArticleModel>() {
            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.binding.apply {
                tvTitle.text = article.title
                tvAuthor.text =
                    if (article.author.isNotEmpty()) "作者：${article.author}" else "分享者：${article.shareUser}"
                tvClassify.text = "分类：${article.superChapterName}"
                tvTime.text = article.formatTime
            }
        }
    }

}
