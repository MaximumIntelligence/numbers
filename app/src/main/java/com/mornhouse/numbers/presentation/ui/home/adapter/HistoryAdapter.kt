package com.mornhouse.numbers.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mornhouse.numbers.databinding.HistoryElementBinding
import com.mornhouse.numbers.domain.model.NumberFactModel
import com.mornhouse.numbers.presentation.common.BaseRecyclerAdapter
import com.mornhouse.numbers.presentation.common.BaseViewHolder
import com.mornhouse.numbers.presentation.common.NumberClickListener

class HistoryAdapter(private val numberClickListener: NumberClickListener) :
    BaseRecyclerAdapter<NumberFactModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<NumberFactModel> {
        val binding =
            HistoryElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(numberClickListener, binding)
    }

    inner class HistoryViewHolder(
        private val numberClickListener: NumberClickListener,
        private val binding: HistoryElementBinding,
    ) : BaseViewHolder<NumberFactModel>(binding.root) {
        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { numberFactModel ->
                    numberClickListener.onNumberClicked(numberFactModel)
                }
            }
        }

        override fun bind(model: NumberFactModel) {
            with(binding) {
                tvNumber.text = model.number.toString()
                tvDescription.text = model.text
            }
        }
    }
}