package pl.sagiton.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public MyUser getUser(String username){
        String SQL = "select * from USERS where USERNAME = ?";
        MyUser myUser = jdbcTemplateObject.queryForObject(SQL, new Object[]{username}, new RowMapper<MyUser>() {

            public MyUser mapRow(ResultSet resultSet, int i) throws SQLException {
                MyUser user = new MyUser();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("user_password"));
                return user;
            }
        });

        return myUser;
    }

}