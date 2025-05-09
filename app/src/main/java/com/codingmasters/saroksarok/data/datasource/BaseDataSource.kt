package com.codingmasters.saroksarok.data.datasource

import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto
import com.codingmasters.saroksarok.data.response_dto.ResponseBuyDto
import com.codingmasters.saroksarok.data.response_dto.ResponseMintingDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part
import java.math.BigDecimal

interface BaseDataSource {
    suspend fun getAll(): ResponseAllDto
    suspend fun minting(
        file: MultipartBody.Part,
        title: RequestBody,
        description: RequestBody,
        name: RequestBody,
        toAddress: RequestBody
    ):ResponseMintingDto

    suspend fun makeList(
        tokenId:Int,
        priceEth:BigDecimal
    ):ResponseMintingDto

    suspend fun myOnSale(
        wallet:String,
    ):ResponseAllDto

    suspend fun myBuy(
        wallet:String,
    ):ResponseAllDto

    suspend fun buy(tokenId:Int, priceEth:BigDecimal, buyer:String):ResponseBuyDto
}