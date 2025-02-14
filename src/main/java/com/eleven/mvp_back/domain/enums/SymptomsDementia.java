package com.eleven.mvp_back.domain.enums;

public enum SymptomsDementia {
    NORMAL("정상 (치매 증상 없음)"),
    WANDERING_OUTSIDE("집 밖을 배회"),
    SHORT_TERM_MEMORY_IMPAIRMENT("했던 말을 반복하는 등 단기 기억 장애"),
    NOT_RECOGNIZING_FAMILY("가족을 알아보지 못함"),
    GETTING_LOST("길을 잃거나 자주 가던 곳을 헤맴"),
    PARANOID_DELUSIONS("사람을 의심하는 망상"),
    CHILDLIKE_BEHAVIOR("어린아이 같은 행동"),
    AGGRESSIVE_BEHAVIOR("때리거나 욕설 등 공격적인 행동");

    private final String displayName;

    SymptomsDementia(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
