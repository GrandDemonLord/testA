/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
@Component
public class StringToListStringTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType)
            throws SQLException {
        // 将 List<String> 转换为字符串并设置到 PreparedStatement 中
        ps.setString(i, listToString(parameter));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从 ResultSet 中获取字符串，并将其转换为 List<String>
        return stringToList(rs.getString(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 从 ResultSet 中获取字符串，并将其转换为 List<String>
        return stringToList(rs.getString(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 从 CallableStatement 中获取字符串，并将其转换为 List<String>
        return stringToList(cs.getString(columnIndex));
    }

    // 将 List<String> 转换为字符串，格式如 "[网络安全保护法, 网络安全保护法]"
    private String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // 将字符串 "[网络安全保护法, 网络安全保护法]" 转换为 List<String>
    private List<String> stringToList(String str) {
        List<String> list = new ArrayList<>();
        if (str != null && str.length() > 2) {
            str = str.substring(1, str.length() - 1); // 去掉字符串两端的 "[ ]"
            String[] tokens = str.split(",");
            for (String token : tokens) {
                list.add(token.trim());
            }
        }
        return list;
    }
}
