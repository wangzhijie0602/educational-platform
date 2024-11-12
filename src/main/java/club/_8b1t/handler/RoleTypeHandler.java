package club._8b1t.handler;

import club._8b1t.pojo.User;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 8bit
 */
public class RoleTypeHandler extends BaseTypeHandler<User.Role> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, User.Role parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name().toLowerCase());
    }

    @Override
    public User.Role getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String role = rs.getString(columnName);
        return User.Role.valueOf(role.toUpperCase());
    }

    @Override
    public User.Role getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String role = rs.getString(columnIndex);
        return User.Role.valueOf(role.toUpperCase());
    }

    @Override
    public User.Role getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String role = cs.getString(columnIndex);
        return User.Role.valueOf(role.toUpperCase());
    }
}