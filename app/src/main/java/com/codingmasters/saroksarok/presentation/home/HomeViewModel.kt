package com.codingmasters.saroksarok.presentation.home

import androidx.lifecycle.ViewModel
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.data.Content

class HomeViewModel:ViewModel(){
    val contents = listOf(
        Content(
            image = R.drawable.image1,
            title = "새벽물 모심기의 법칙",
            id = "#1001",
            price = "0.15ETH",
            type = "문서"
        ),
        Content(
            image = R.drawable.image2,
            title = "절기별 밭갈이 깊이 조절 노하우",
            id = "#1002",
            price = "0.1ETH",
            type = "문서"
        ),
        Content(
            image = R.drawable.image3,
            title = "논두렁 물길 잡는 3가지 방법",
            id = "#1003",
            price = "0.12ETH",
            type = "문서"
        ),
        Content(
            image = R.drawable.image4,
            title = "고추 순지르기 타이밍의 비밀",
            id = "#1004",
            price = "0.18ETH",
            type = "문서"
        ),
        Content(
            image = R.drawable.image5,
            title = "장마철 벼 쓰러짐 방지 지침",
            id = "#1005",
            price = "0.2ETH",
            type = "문서"
        ),
        Content(
            image = R.drawable.image6,
            title = "아침이슬 기준 풀베기 요령",
            id = "#1006",
            price = "0.1ETH",
            type = "문서"
        ),
        Content(
            image = R.drawable.image7,
            title = "가을 김장무 병 안 오는 심는 날",
            id = "#1007",
            price = "0.13ETH",
            type = "문서"
        ),
        Content(
            image = R.drawable.image8,
            title = "우렁이 농법의 진짜 효과와 한계",
            id = "#1008",
            price = "0.2ETH",
            type = "문서"
        ),
        Content(
            image = R.drawable.image9,
            title = "참깨 타작, 햇살보다 바람을 봐라",
            id = "#1009",
            price = "0.17ETH",
            type = "문서"
        ),
        Content(
            image = R.drawable.image10,
            title = "서리 내리기 전 무 수확 타이밍",
            id = "#1010",
            price = "0.14ETH",
            type = "문서"
        )
    )


}