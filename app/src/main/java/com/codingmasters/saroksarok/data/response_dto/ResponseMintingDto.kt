package com.codingmasters.saroksarok.data.response_dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMintingDto (
    @SerialName("success")
    val success: Boolean,

    @SerialName("status")
    val status: String,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: Data
){
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int,

        @SerialName("title")
        val title: String,

        @SerialName("txhash")
        val txHash: String,

        @SerialName("tokenId")
        val tokenId: Int,

        @SerialName("price")
        val price: Double? = null,

        @SerialName("type")
        val type: String,

        @SerialName("seller")
        val seller: String,

        @SerialName("description")
        val description: String,

        @SerialName("fileURL")
        val fileUrl: String,

        @SerialName("certified")
        val certified: Boolean,

        @SerialName("status")
        val status: String
    )
}