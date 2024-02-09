package com.mornhouse.numbers.presentation.common

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<Model>(protected var data: MutableList<Model> = mutableListOf()) :
    RecyclerView.Adapter<BaseViewHolder<Model>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<Model>, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setList(mList: List<Model>) {
        data.clear()
        data.addAll(0, mList)
        notifyItemRangeChanged(0, data.size)
    }

    fun getItem(position: Int): Model? = if (data.size > position) data[position] else null
}