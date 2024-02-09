package com.mornhouse.numbers.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NumberFactResponse(
    @SerialName("text")
    val text: String? = null,
    @SerialName("number")
    val number: Long? = null,
    @SerialName("found")
    val isNumberFound: Boolean,
)
