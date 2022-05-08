package com.study.xml_view.ui.classify.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.study.common.data.model.SystemChild
import com.study.common.data.model.SystemGroup
import com.study.xml_view.databinding.ItemSystemBinding
import com.study.xml_view.ui.utils.dp
import com.study.xml_view.ui.utils.getBgDrawable
import com.study.xml_view.ui.utils.setTextColor

class SystemAdapter(
    private val list: MutableList<SystemGroup> = mutableListOf(),
    val onItemClick: (SystemChild) -> Unit
) : RecyclerView.Adapter<SystemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSystemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = list[position].name
            flowGroup.removeAllViews()
            list[position].childes.forEach { child ->
                flowGroup.addView(TextView(root.context).apply {
                    text = child.name
                    background = getBgDrawable(0XFFF1F6FD, 4.dp())
                    setTextColor(0xFF6FA1F1)
                    setPadding(4.dp())
                    includeFontPadding = false
                    setOnClickListener { _ ->
                        onItemClick(child)
                    }
                })
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<SystemGroup>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: ItemSystemBinding) : RecyclerView.ViewHolder(binding.root)
}