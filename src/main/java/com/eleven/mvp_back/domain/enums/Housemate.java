package com.eleven.mvp_back.domain.enums;

public enum Housemate {
    LIVING_ALONE("독거"),
    WITH_SPOUSE_AT_HOME("배우자동거, 집에 있음"),
    WITH_SPOUSE_AWAY("배우자동거, 자리 비움"),
    WITH_FAMILY_AT_HOME("가족동거, 집에 있음"),
    WITH_FAMILY_AWAY("가족동거, 자리 비움");

    private final String displayName;

    Housemate(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
