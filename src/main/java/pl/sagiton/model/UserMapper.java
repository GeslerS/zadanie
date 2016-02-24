package pl.sagiton.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by szymon on 24.02.16.
 */

    public class UserMapper implements RowMapper<MyUser> {

        public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            MyUser user = new MyUser();
            user.setUser_id(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setUser_password(rs.getString("user_password"));
            return user;
        }
    }
