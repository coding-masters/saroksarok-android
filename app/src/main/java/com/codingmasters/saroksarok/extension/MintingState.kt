package com.codingmasters.saroksarok.extension

import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto
import com.codingmasters.saroksarok.data.response_dto.ResponseMintingDto

sealed class MintingState {
    data object Loading:MintingState()
    data class Success(val mintingDto: ResponseMintingDto):MintingState()
    data class Error(val message:String):MintingState()
}