package com.codingmasters.saroksarok.extension

import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto

sealed class MyOnSaleState {
    data object Loading:MyOnSaleState()
    data class Success(val allDto: ResponseAllDto):MyOnSaleState()
    data class Error(val message:String):MyOnSaleState()
}