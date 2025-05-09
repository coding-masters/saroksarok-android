package com.codingmasters.saroksarok.extension

import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto

sealed class AllState {
    data object Loading:AllState()
    data class Success(val allDto: ResponseAllDto):AllState()
    data class Error(val message:String):AllState()
}