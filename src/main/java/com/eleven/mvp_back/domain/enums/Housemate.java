package com.eleven.mvp_back.domain.enums;

public enum Housemate {
    LIVING_ALONE("독거"),
    WITH_SPOUSE_AT_HOME("배우자와 동거, 돌봄 시간 중 집에 있음"),
    WITH_SPOUSE_AWAY("배우자와 동거, 돌봄 시간 중 자리 비움"),
    WITH_FAMILY_AT_HOME("다른 가족과 동거, 돌봄 시간 중 집에 있음"),
    WITH_FAMILY_AWAY("다른 가족과 동거, 돌봄 시간 중 자리 비움");

    private final String displayName;

    Housemate(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
