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
            type = "문서",
            description = "이른 새벽에 논에 물을 대고 모를 심으면 뿌리 활착이 좋아지고 일사병 피해도 줄일 수 있습니다."
        ),
        Content(
            image = R.drawable.image2,
            title = "절기별 밭갈이 깊이 조절 노하우",
            id = "#1002",
            price = "0.1ETH",
            type = "문서",
            description = "소한·대한엔 얕게, 입춘 이후엔 깊게 갈아주는 것이 작물 생육에 효과적입니다."
        ),
        Content(
            image = R.drawable.image3,
            title = "논두렁 물길 잡는 3가지 방법",
            id = "#1003",
            price = "0.12ETH",
            type = "문서",
            description = "직선 물길, 계단식 물길, 갈지자형 물길을 활용해 배수와 관수를 조절할 수 있습니다."
        ),
        Content(
            image = R.drawable.image4,
            title = "고추 순지르기 타이밍의 비밀",
            id = "#1004",
            price = "0.18ETH",
            type = "문서",
            description = "1차 꽃이 핀 후 순을 지르면 가지수가 늘어나고 수확량이 많아집니다."
        ),
        Content(
            image = R.drawable.image5,
            title = "장마철 벼 쓰러짐 방지 지침",
            id = "#1005",
            price = "0.2ETH",
            type = "문서",
            description = "질소 비료를 과다 사용하지 않고 이삭 패기 전 칼륨을 보충하면 쓰러짐을 예방할 수 있습니다."
        ),
        Content(
            image = R.drawable.image6,
            title = "아침이슬 기준 풀베기 요령",
            id = "#1006",
            price = "0.1ETH",
            type = "문서",
            description = "이슬이 마르기 전 풀을 베면 날림이 적고 뿌리까지 잘 잘려서 제초 효과가 좋습니다."
        ),
        Content(
            image = R.drawable.image7,
            title = "가을 김장무 병 안 오는 심는 날",
            id = "#1007",
            price = "0.13ETH",
            type = "문서",
            description = "처서 이후, 토양 온도가 25도 이하일 때 심으면 무름병 피해를 줄일 수 있습니다."
        ),
        Content(
            image = R.drawable.image8,
            title = "우렁이 농법의 진짜 효과와 한계",
            id = "#1008",
            price = "0.2ETH",
            type = "문서",
            description = "초기 제초에는 탁월하지만 모가 클수록 피해가 생길 수 있으니 투입 시기를 조절해야 합니다."
        ),
        Content(
            image = R.drawable.image9,
            title = "참깨 타작, 햇살보다 바람을 봐라",
            id = "#1009",
            price = "0.17ETH",
            type = "문서",
            description = "햇빛보다는 바람이 잘 부는 날이 껍질 마름과 탈립에 더 적합합니다."
        ),
        Content(
            image = R.drawable.image10,
            title = "서리 내리기 전 무 수확 타이밍",
            id = "#1010",
            price = "0.14ETH",
            type = "문서",
            description = "첫 서리가 오기 3~5일 전에 수확하면 단맛이 강하고 조직이 단단해져 저장성도 좋습니다."
        )
    )
}