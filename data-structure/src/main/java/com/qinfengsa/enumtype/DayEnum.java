package com.qinfengsa.enumtype;

/**
 * @author: qinfengsa
 * @date: 2019/6/11 15:49
 */
public enum DayEnum {
    /**
     * 星期
     */
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");

    /**
     * 名称
     */
    private  String name;

    DayEnum(String name) {
        this.name = name;
    }
}
