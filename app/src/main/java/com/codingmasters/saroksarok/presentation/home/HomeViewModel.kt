package com.codingmasters.saroksarok.presentation.home

import androidx.lifecycle.ViewModel
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.data.Content

class HomeViewModel:ViewModel(){
    val contents = listOf(
        Content(
            image = R.drawable.image1,
            title = "한여름 무더위 이겨내기",
            id = "#2147",
            price = "0.0001ETH",
            type = "문서",
            seller = "김옥순",
            certified = true,
            description = "무더위 속 작물 관리를 위한 그늘 제공과 수분 공급 방법을 소개합니다."
        ),
        Content(
            image = R.drawable.image2,
            title = "새벽물 모심기의 법칙",
            id = "#4836",
            price = "0.0002ETH",
            type = "영상",
            seller = "박춘식",
            certified = true,
            description = "이른 새벽에 논에 물을 대고 모를 심는 전통 기술을 다룹니다."
        ),
        Content(
            image = R.drawable.image3,
            title = "절기별 밭갈이 깊이 조절 노하우",
            id = "#4700",
            price = "0.0003ETH",
            type = "영상",
            seller = "김영수",
            certified = false,
            description = "절기에 따라 적정한 깊이로 밭을 가는 방법을 상세히 설명합니다."
        ),
        Content(
            image = R.drawable.image4,
            title = "파치귤 처리법",
            id = "#4821",
            price = "0.0002ETH",
            type = "문서",
            seller = "이병호",
            certified = false,
            description = "상처난 귤의 유통과 보관 방법, 활용법에 대한 실용적인 팁입니다."
        ),
        Content(
            image = R.drawable.image5,
            title = "고구마 상처 없이 캐기",
            id = "#7634",
            price = "0.0001ETH",
            type = "이미지",
            seller = "최덕수",
            certified = false,
            description = "고구마를 상처 없이 수확하는 도구 사용법과 손기술을 소개합니다."
        ),
        Content(
            image = R.drawable.image6,
            title = "이렇게 따자 복숭아!",
            id = "#1957",
            price = "0.0005ETH",
            type = "문서",
            seller = "정동호",
            certified = true,
            description = "복숭아 수확 시 손으로 따는 각도와 압력의 중요성을 다룹니다."
        ),
        Content(
            image = R.drawable.image7,
            title = "사과하는 법",
            id = "#8290",
            price = "0.001ETH",
            type = "영상",
            seller = "김말순",
            certified = false,
            description = "사과 수확 후 보관 및 유통을 위한 가장 효율적인 방법을 소개합니다."
        ),
        Content(
            image = R.drawable.image8,
            title = "이것이 참외다.",
            id = "#6013",
            price = "0.00007ETH",
            type = "이미지",
            seller = "조재구",
            certified = false,
            description = "참외 고르는 법부터 직거래 노하우까지 소개하는 팁 모음입니다."
        ),
        Content(
            image = R.drawable.image9,
            title = "세상에서 가장 큰 호박을 키우는 법",
            id = "#3472",
            price = "0.0008ETH",
            type = "문서",
            seller = "윤말자",
            certified = false,
            description = "거대 호박을 키우기 위한 토양 관리와 물주기 전략을 소개합니다."
        ),
        Content(
            image = R.drawable.image10,
            title = "딸기가 좋아! 딸기가 좋아!",
            id = "#9186",
            price = "0.0006ETH",
            type = "문서",
            seller = "한옥분",
            certified = false,
            description = "당도 높은 딸기를 수확하기 위한 품종과 재배 비법을 알려드립니다."
        ),
    )

}