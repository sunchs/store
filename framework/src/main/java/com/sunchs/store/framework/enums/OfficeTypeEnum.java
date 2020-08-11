package com.sunchs.store.framework.enums;

public enum OfficeTypeEnum {

    /**
     * 门诊
     */
    Outpatient(1, "门诊"),

    /**
     * 住院d
     */
    Inpatient(2, "住院"),

    /**
     * 员工
     */
    Employee(3, "员工"),

    /**
     * 特殊
     */
    Special(4, "特殊");

    public int status;
    public String title;

    OfficeTypeEnum(int status, String title) {
        this.status = status;
        this.title = title;
    }

    public static String get(int status) {
        for (OfficeTypeEnum statusEnum : OfficeTypeEnum.values()) {
            if (status == statusEnum.status) {
                return statusEnum.title;
            }
        }
        return "";
    }
}
