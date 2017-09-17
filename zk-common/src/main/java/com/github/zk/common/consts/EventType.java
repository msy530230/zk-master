package com.github.zk.common.consts;

/**
 * Created by l.zhao on 17-9-16.
 * Desp TODO.
 */
public enum EventType {
    /**
     * CURD
     */
    CREATE(0 , "新增"),
    UPDATE(1 , "更新"),
    RETRIVE(2 , "查询"),
    DELETE(3, "删除");

    /**
     * 类型
     */
    private int type;

    /**
     * 别名
     */
    private String alias;

    private EventType(int type, String alias) {
        this.type = type;
        this.alias = alias;
    }
}
