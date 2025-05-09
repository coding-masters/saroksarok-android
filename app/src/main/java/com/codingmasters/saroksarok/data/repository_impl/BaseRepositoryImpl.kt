package com.codingmasters.saroksarok.data.repository_impl

import com.codingmasters.saroksarok.data.datasource.BaseDataSource
import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto
import com.codingmasters.saroksarok.data.response_dto.ResponseBuyDto
import com.codingmasters.saroksarok.data.response_dto.ResponseMintingDto
import com.codingmasters.saroksarok.domain.repository.BaseRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import java.math.BigDecimal
import javax.inject.Inject

class BaseRepositoryImpl  @Inject constructor(
    private val baseDataSource:BaseDataSource
):BaseRepository{
    override suspend fun getAll(): Result<ResponseAllDto> {
        return runCatching {
            baseDataSource.getAll()
        }.onFailure {
            Timber.e("base repository get all fail!!: $it")
        }
    }

    override suspend fun minting(
        file: MultipartBody.Part,
        title: String,
        description: String,
        name: String,
        toAddress: String
    ): Result<ResponseMintingDto> {
        return runCatching {
            baseDataSource.minting(file, title.toRequestBody(), description.toRequestBody(), name.toRequestBody(), toAddress.toRequestBody())
        }.onFailure {
            Timber.e("base repository minting fail!!: $it")
        }
    }

    override suspend fun makeList(tokenId: Int, priceEth: BigDecimal): Result<ResponseMintingDto> {
        return runCatching {
            baseDataSource.makeList(tokenId,priceEth)
        }.onFailure {
            Timber.e("base repository make list fail!!: $it")
        }
    }

    override suspend fun myOnSale(wallet: String): Result<ResponseAllDto> {
        return runCatching {
            baseDataSource.myOnSale(wallet)
        }.onFailure{
            Timber.e("base repository my on sale fail!!: $it")
        }
    }

    override suspend fun myBuy(wallet: String): Result<ResponseAllDto> {
        return runCatching {
            baseDataSource.myBuy(wallet)
        }.onFailure {
            Timber.e("base repository my buy fail!!: $it")
        }
    }

    override suspend fun buy(
        tokenId: Int,
        priceEth: BigDecimal,
        buyer: String
    ): Result<ResponseBuyDto> {
        return runCatching {
            baseDataSource.buy(tokenId, priceEth, buyer)
        }.onFailure {
            Timber.e("base repository buy fail!!!: $it")
        }
    }
}