package com.codingmasters.saroksarok.extension

import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto
import com.codingmasters.saroksarok.data.response_dto.ResponseBuyDto

sealed class BuyState {
    data object Loading:BuyState()
    data class Success(val allDto: ResponseBuyDto):BuyState()
    data class Error(val message:String):BuyState()
}