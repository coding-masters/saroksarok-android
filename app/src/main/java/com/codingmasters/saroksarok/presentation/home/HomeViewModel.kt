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