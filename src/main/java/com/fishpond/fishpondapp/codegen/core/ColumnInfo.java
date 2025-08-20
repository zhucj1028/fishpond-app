package com.fishpond.fishpondapp.codegen.core;

import lombok.Data;


@Data
public class ColumnInfo {
    private String key;
    private String name;
    private String dataType;
    private String comment;
    private String nullAble;

    private String javaType;
    private String javaName;
}
