package com.codingmasters.saroksarok.data.service

import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto
import com.codingmasters.saroksarok.data.response_dto.ResponseBuyDto
import com.codingmasters.saroksarok.data.response_dto.ResponseMintingDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import java.math.BigDecimal

interface BaseService {
    @GET("nft/all")
    suspend fun getAll(
    ):ResponseAllDto

    @Multipart
    @POST("nft/mint")
    suspend fun minting(
        @Part file: MultipartBody.Part,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("name") name: RequestBody,
        @Part("toAddress") toAddress: RequestBody
    ):ResponseMintingDto

    @POST("nft/list")
    suspend fun makeList(
        @Query("tokenId") tokenId:Int,
        @Query("priceEth") priceEth:BigDecimal
    ):ResponseMintingDto

    @GET("nft/my/onsale")
    suspend fun myOnSale(
        @Query("wallet") wallet:String,
    ):ResponseAllDto

    @GET("nft/my/buy")
    suspend fun myBuy(
        @Query("wallet") wallet:String,
    ):ResponseAllDto

    @POST("nft/buy")
    suspend fun buy(
        @Query("tokenId") tokenId:Int,
        @Query("priceEth") priceEth:BigDecimal,
        @Query("buyer") buyer:String,
    ):ResponseBuyDto
}