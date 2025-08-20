package com.fishpond.fishpondapp.codegen.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TableInfo {
    private String name;
    private String comment;
    private ColumnInfo idColumn;
    private List<ColumnInfo> columns = new ArrayList<>();
}
