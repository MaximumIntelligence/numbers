package com.mornhouse.numbers.presentation.common

import com.mornhouse.numbers.domain.model.NumberFactModel

interface NumberClickListener {
    fun onNumberClicked(model: NumberFactModel)
}