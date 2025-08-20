package com.fishpond.fishpondapp.codegen.core;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Slf4j
public class JdbcUtil implements Closeable {
    /**
     * 通过数据源创建实例
     */
    @SneakyThrows
    public static JdbcUtil create(DataSource dataSource) {
        return new JdbcUtil(dataSource.getConnection());
    }

    /**
     * 通过数据库连接对象创建实例
     */
    public static JdbcUtil create(Connection connection) {
        return new JdbcUtil(connection);
    }

    /**
     * 通过数据库连接信息创建实例
     */
    @SneakyThrows
    public static JdbcUtil create(String driver, String url, String username, String password) {
        Class.forName(driver);
        return new JdbcUtil(DriverManager.getConnection(url, username, password));
    }


    /**
     * 查询单行记录
     */
    public Map<String, Object> selectOne(String sql, Object... params) {
        List<Map<String, Object>> list = selectList(sql, params);
        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 查询一个列的多行记录
     */
    public <T> List<T> selectOneColumnList(String sql, Object... params) {
        List<T> result = new ArrayList<>();
        List<Map<String, Object>> lst = selectList(sql, params);
        lst.forEach(m -> result.add((T) m.get(m.keySet().iterator().next())));
        return result;
    }

    /**
     * 查询多行记录
     */
    public List<Map<String, Object>> selectList(String sql, Object... params) {
        if (log.isDebugEnabled()) {
            log.debug("执行查询类SQL：{}", sql);
            log.debug("  参数：{}", Arrays.toString(params));
        }
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            addPreparedStatementParam(stat, params);
            List<Map<String, Object>> result = getResultSetList(stat.executeQuery());
            if (log.isDebugEnabled()) {
                log.debug("  查询结果： {} 行", result.size());
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("执行查询类SQL失败，" + e.getMessage(), e);
        }
    }

    /**
     * 修改
     */
    public void update(String sql, Object... params) {
        if (log.isDebugEnabled()) {
            log.debug("执行修改类SQL：{}", sql);
            log.debug("  参数：{}", Arrays.toString(params));
        }
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            addPreparedStatementParam(stat, params);
            int updated = stat.executeUpdate();
            if (log.isDebugEnabled()) {
                log.debug("  修改结果： {} 行受影响", updated);
            }
        } catch (Exception e) {
            throw new RuntimeException("执行修改类SQL失败，" + e.getMessage(), e);
        }
    }

    /**
     * 启用事务
     */
    @SneakyThrows
    public void startTransaction() {
        connection.setAutoCommit(false);
    }

    /**
     * 提交事务
     */
    @SneakyThrows
    public void commit() {
        connection.commit();
    }

    /**
     * 回滚事务
     */
    @SneakyThrows
    public void rollback() {
        connection.rollback();
    }

    @SneakyThrows
    @Override
    public void close() {
        connection.close();
    }

    private void addPreparedStatementParam(PreparedStatement statement, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
        }
    }

    private List<Map<String, Object>> getResultSetList(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        try (ResultSet rs = resultSet) {
            while (rs.next()) {
                list.add(getResultSetRow(rs));
            }
        }
        return list;
    }

    private Map<String, Object> getResultSetRow(ResultSet resultSet) throws SQLException {
        Map<String, Object> row = new LinkedHashMap<>();
        ResultSetMetaData meta = resultSet.getMetaData();
        for (int i = 0; i < meta.getColumnCount(); i++) {
            int idx = i + 1;
            row.put(meta.getColumnLabel(idx), convertValue(meta, idx, resultSet.getObject(idx)));
        }
        return row;
    }

    private Object convertValue(ResultSetMetaData meta, int idx, Object value) throws SQLException {
        if (value != null) {
            SimpleDateFormat sdf;
            int type = meta.getColumnType(idx);
            switch (type) {
                case Types.DATE:
                    Date date = (Date) value;
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    value = sdf.format(date);
                    break;
                case Types.TIME:
                    Time time = (Time) value;
                    sdf = new SimpleDateFormat("HH:mm:ss");
                    value = sdf.format(time);
                    break;
                case Types.TIMESTAMP:
                    if (value instanceof LocalDateTime) {
                        value = DateTimeFormatter.ofPattern(getDateTimeFormatPattern()).format((LocalDateTime) value);
                    } else {
                        sdf = new SimpleDateFormat(getDateTimeFormatPattern());
                        value = sdf.format((Timestamp) value);
                    }
                    break;
            }
        }
        return value;
    }

    @Getter
    @Setter
    private String dateTimeFormatPattern = "yyyy-MM-dd HH:mm:ss";
    private final Connection connection;

    private JdbcUtil(Connection connection) {
        this.connection = connection;
    }
}
