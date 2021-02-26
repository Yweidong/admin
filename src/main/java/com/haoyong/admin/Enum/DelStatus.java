package com.haoyong.admin.Enum;

public enum DelStatus {
    Y("Y"),//已删除
    N("N");//未删除
    private String value;
    DelStatus(String value) {
         this.value = value;
    }
    public String getValue() {
        return value;
    }
}
