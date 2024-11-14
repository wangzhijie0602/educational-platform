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
 * @version 1.0
 * @since 1.0
 */
public class StatusTypeHandler extends BaseTypeHandler<User.Status> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, User.Status parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name().toLowerCase());
    }

    @Override
    public User.Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String status = rs.getString(columnName);
        return User.Status.valueOf(status.toUpperCase());
    }

    @Override
    public User.Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String status = rs.getString(columnIndex);
        return User.Status.valueOf(status.toUpperCase());
    }

    @Override
    public User.Status getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String status = cs.getString(columnIndex);
        return User.Status.valueOf(status.toUpperCase());
    }
}
