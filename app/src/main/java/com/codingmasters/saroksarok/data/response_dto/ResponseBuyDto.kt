package com.codingmasters.saroksarok.data.response_dto

import com.codingmasters.saroksarok.data.response_dto.ResponseMintingDto.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseBuyDto (
    @SerialName("success")
    val success:Boolean,
    @SerialName("status")
    val status: String,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: String
)