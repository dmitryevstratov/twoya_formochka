package com.shepard1992.gmail.twoya_formochka.view.model.enums;

public enum Month {

    JAN(0, "Январь"),
    FEB(1, "Феварль"),
    MAR(2, "Март"),
    APR(3, "Апрель"),
    MAY(4, "Май"),
    JUN(5, "Июнь"),
    JUL(6, "Июль"),
    AUG(7, "Август"),
    SEP(8, "Сентябрь"),
    OKT(9, "Октябрь"),
    NOV(10, "Ноябрь"),
    DEC(11, "Декабрь");

    private final int num;
    private final String name;

    Month(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
}
