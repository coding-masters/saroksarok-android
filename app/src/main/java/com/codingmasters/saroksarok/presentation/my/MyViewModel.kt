package com.codingmasters.saroksarok.presentation.my

import androidx.lifecycle.ViewModel
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.data.Content

class MyViewModel:ViewModel() {
    val myList = listOf(
        Content(
            image = R.drawable.image11,
            title = "쥐 퇴치의 정석",
            id = "#2543",
            price = "0.012ETH",
            type = "영상",
            seller = "한병철",
            certified = true,
            description = "친환경적으로 농장 내 쥐를 퇴치하는 방법을 영상으로 배워보세요."
        ),
        Content(
            image = R.drawable.image12,
            title = "나만의 작은 텃밭",
            id = "#7301",
            price = "0.0006ETH",
            type = "문서",
            seller = "임춘자",
            certified = false,
            description = "한 평 공간을 활용한 효율적인 텃밭 구성과 작물 추천 가이드입니다."
        ),
        Content(
            image = R.drawable.image13,
            title = "밀밭 마스터가 되기 위한 365일 24시간 시간표",
            id = "#6495",
            price = "0.003ETH",
            type = "문서",
            seller = "양점순",
            certified = false,
            description = "밀 재배의 연간 관리 루틴을 시간표 형식으로 구성했습니다."
        ),
        Content(
            image = R.drawable.image14,
            title = "수박서리쟁이.. 잡는법.",
            id = "#1327",
            price = "0.0002ETH",
            type = "영상",
            seller = "문정임",
            certified = false,
            description = "농촌의 전통 지혜로 수박 도둑을 예방하고 대처하는 법을 다룹니다."
        ),
        Content(
            image = R.drawable.image15,
            title = "1년 농사를 망치는 최악의 습관",
            id = "#5780",
            price = "0.0001ETH",
            type = "문서",
            seller = "오정숙",
            certified = false,
            description = "수확 실패로 이어지는 대표적 습관들을 짚고 개선 방법을 제시합니다."
        ),
        Content(
            image = R.drawable.image16,
            title = "새참 못 참새",
            id = "#4698",
            price = "0.0002ETH",
            type = "이미지",
            seller = "장정남",
            certified = false,
            description = "참새 등 새로 인한 작물 피해를 줄이는 아이디어를 소개합니다."
        ),
        Content(
            image = R.drawable.image17,
            title = "한국 지역별 기후와 농사",
            id = "#8216",
            price = "0.0002ETH",
            type = "이미지",
            seller = "이복례",
            certified = false,
            description = "기후별 작물 재배 전략을 인포그래픽으로 쉽게 이해할 수 있습니다."
        ),
        Content(
            image = R.drawable.image18,
            title = "내 상추 싱싱하게 직거래하자",
            id = "#3902",
            price = "0.0008ETH",
            type = "문서",
            seller = "신숙례",
            certified = false,
            description = "상추를 신선하게 유지하며 거래하는 포장법과 직거래 요령을 담았습니다."
        ),
        Content(
            image = R.drawable.image19,
            title = "맛있는 메론 자랑",
            id = "#7451",
            price = "0.00000001ETH",
            type = "이미지",
            seller = "배금순",
            certified = false,
            description = "고당도 메론을 자랑하는 법과 잘 익은 메론 고르는 팁을 알려드립니다."
        ),
        Content(
            image = R.drawable.image20,
            title = "남은 작물 버리지 말고 비료로 만드는 법",
            id = "#2068",
            price = "0.0001ETH",
            type = "영상",
            seller = "임만수",
            certified = false,
            description = "작물 잔재를 활용해 천연 비료를 만드는 친환경 농법을 배워봅니다."
        )
    )
}