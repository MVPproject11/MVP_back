package com.eleven.mvp_back.enums;

public enum Benefit {
    FOUR_INSURANCES("4대보험"),
    TRANSPORT_SUPPORT("교통비 지원"),
    RETIREMENT_PAY("퇴직금여"),
    CONGRATULATION_EXPENSE("경조사비"),
    HOLIDAY_GIFT("명절선물"),
    MEAL_SUPPORT("식사(비) 지원"),
    LONG_SERVICE_INCENTIVE("장기근속 장려금"),
    GOVERNMENT_SUBSIDY("정부지원금"),
    SEVERE_ADDITIONAL_ALLOWANCE("중증가산수당"),
    DRIVING_ALLOWANCE("운전 수당");

    private final String displayName;

    Benefit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
