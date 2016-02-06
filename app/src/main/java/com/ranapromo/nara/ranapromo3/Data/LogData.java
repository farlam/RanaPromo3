package com.ranapromo.nara.ranapromo3.Data;

/**
 * Created by smati on 06/02/2016.
 */
public class LogData {
    private String action;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private int value;
    private String tableName;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
