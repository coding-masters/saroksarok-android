package com.codingmasters.saroksarok.extension

import com.codingmasters.saroksarok.data.response_dto.ResponseMintingDto

sealed class MakeListState {
    data object Loading:MakeListState()
    data class Success(val mintingDto: ResponseMintingDto):MakeListState()
    data class Error(val message:String):MakeListState()
}