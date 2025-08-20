package com.fishpond.fishpondapp.codegen.core;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DataSourceConfig {
    private String url;
    private String driver;
    private String username;
    private String password;

    private String databaseName;
    private String tableName;
    private String[] excludeColumns;
}
