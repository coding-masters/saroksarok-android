package com.codingmasters.saroksarok.domain.repository

import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto
import com.codingmasters.saroksarok.data.response_dto.ResponseBuyDto
import com.codingmasters.saroksarok.data.response_dto.ResponseMintingDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.math.BigDecimal

interface BaseRepository {
    suspend fun getAll():Result<ResponseAllDto>
    suspend fun minting(file:MultipartBody.Part,
                        title: String,
                        description: String,
                        name: String,
                        toAddress: String):Result<ResponseMintingDto>

    suspend fun makeList(tokenId:Int, priceEth:BigDecimal):Result<ResponseMintingDto>

    suspend fun myOnSale(wallet:String):Result<ResponseAllDto>
    suspend fun myBuy(wallet: String):Result<ResponseAllDto>
    suspend fun buy(tokenId:Int, priceEth: BigDecimal, buyer:String):Result<ResponseBuyDto>
}