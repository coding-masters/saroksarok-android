package com.codingmasters.saroksarok.extension

import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto

sealed class MyBuyState {
    data object Loading:MyBuyState()
    data class Success(val allDto: ResponseAllDto):MyBuyState()
    data class Error(val message:String):MyBuyState()
}