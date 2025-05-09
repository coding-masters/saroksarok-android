package com.codingmasters.saroksarok.data.datasource_impl

import com.codingmasters.saroksarok.data.datasource.BaseDataSource
import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto
import com.codingmasters.saroksarok.data.response_dto.ResponseBuyDto
import com.codingmasters.saroksarok.data.response_dto.ResponseMintingDto
import com.codingmasters.saroksarok.data.service.BaseService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.math.BigDecimal
import javax.inject.Inject

class BaseDataSourceImpl @Inject constructor(
    private val baseService: BaseService
) : BaseDataSource {
    override suspend fun getAll(): ResponseAllDto = baseService.getAll()
    override suspend fun minting(
        file: MultipartBody.Part,
        title: RequestBody,
        description: RequestBody,
        name: RequestBody,
        toAddress: RequestBody
    ) = baseService.minting(file, title, description, name, toAddress)

    override suspend fun makeList(tokenId: Int, priceEth: BigDecimal): ResponseMintingDto =
        baseService.makeList(tokenId, priceEth)

    override suspend fun myOnSale(wallet: String): ResponseAllDto = baseService.myOnSale(wallet)
    override suspend fun myBuy(wallet: String): ResponseAllDto = baseService.myBuy(wallet)
    override suspend fun buy(tokenId: Int, priceEth: BigDecimal, buyer: String): ResponseBuyDto = baseService.buy(tokenId, priceEth, buyer)
}