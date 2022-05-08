package com.study.xml_view.ui.classify.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.study.common.data.model.NavigationGroup
import com.study.common.data.model.NavigationModel
import com.study.xml_view.databinding.ItemSystemBinding
import com.study.xml_view.ui.utils.dp
import com.study.xml_view.ui.utils.getBgDrawable
import com.study.xml_view.ui.utils.setTextColor

class NavigationAdapter(
    private val list: MutableList<NavigationGroup> = mutableListOf(),
    val onItemClick: (NavigationModel) -> Unit
) : RecyclerView.Adapter<NavigationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSystemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = list[position].name
            flowGroup.removeAllViews()
            list[position].articles.forEach { model ->
                flowGroup.addView(TextView(root.context).apply {
                    text = model.title
                    background = getBgDrawable(0XFFF1F6FD, 4.dp())
                    setTextColor(0xFF6FA1F1)
                    setPadding(4.dp())
                    includeFontPadding = false
                    setOnClickListener {
                        onItemClick(model)
                    }
                })
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<NavigationGroup>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: ItemSystemBinding) : RecyclerView.ViewHolder(binding.root)
}