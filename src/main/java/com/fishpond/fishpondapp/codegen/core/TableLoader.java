package com.fishpond.fishpondapp.codegen.core;

import java.util.*;

public class TableLoader {

    public static TableInfo load(DataSourceConfig config) {
        List<String> excludeColumns = config.getExcludeColumns() == null ? new ArrayList<>() : Arrays.asList(config.getExcludeColumns());
        TableInfo table = selectTable(config);
        List<ColumnInfo> columns = selectColumns(config);

        for (ColumnInfo c : columns) {
            c.setName(c.getName().toLowerCase());
            c.setDataType(c.getDataType().toLowerCase());
            switch (c.getDataType()) {
                case "float":
                case "double":
                case "decimal":
                    c.setJavaType("Double");
                    break;
                case "tinyint":
                case "smallint":
                case "mediumint":
                case "int":
                    c.setJavaType("Integer");
                    break;
                case "bigint":
                    c.setJavaType("Long");
                    break;
                default:
                    c.setJavaType("String");
            }

            c.setJavaName(CodeUtil.camelCaseColumn(c.getName()));

            if ("PRI".equalsIgnoreCase(c.getKey())) {
                table.setIdColumn(c);
            } else {
                if (!excludeColumns.contains(c.getName())) {
                    table.getColumns().add(c);
                }
            }
        }

        Objects.requireNonNull(table.getIdColumn(), String.format("表[%s.%s]无PK字段", config.getDatabaseName(), config.getTableName()));
        return table;
    }


    static List<ColumnInfo> selectColumns(DataSourceConfig config) {
        try (JdbcUtil jdbc = JdbcUtil.create(config.getDriver(), config.getUrl(), config.getUsername(), config.getPassword())) {
            List<Map<String, Object>> maps = jdbc.selectList("select `COLUMN_KEY` as 'key',`COLUMN_NAME` as 'name',`DATA_TYPE` as 'dataType',`COLUMN_COMMENT` as 'comment',IS_NULLABLE as 'nullAble'" +
                    "from `information_schema`.columns\n" +
                    "where table_schema = ? and table_name = ?", config.getDatabaseName(), config.getTableName());
            maps = maps.size() == 0 ? null : maps;

            Objects.requireNonNull(maps, String.format("表[%s.%s]无字段", config.getDatabaseName(), config.getTableName()));

            return BeanUtil.populateListAs(maps, ColumnInfo.class);
        }

    }

    static TableInfo selectTable(DataSourceConfig config) {
        try (JdbcUtil jdbc = JdbcUtil.create(config.getDriver(), config.getUrl(), config.getUsername(), config.getPassword())) {
            Map<String, Object> map = jdbc.selectOne("select table_name as 'name',table_comment as 'comment' \n" +
                    "from `information_schema`.tables \n" +
                    "where table_schema = ? and table_name = ?", config.getDatabaseName(), config.getTableName());
            Objects.requireNonNull(map, String.format("表[%s.%s]未找到", config.getDatabaseName(), config.getTableName()));
            return BeanUtil.populate(TableInfo.class, map);
        }
    }
}
