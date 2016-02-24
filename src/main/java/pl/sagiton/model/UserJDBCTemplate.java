package pl.sagiton.model;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by szymon on 24.02.16.
 */
public class UserJDBCTemplate implements UserDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
     public MyUser getUser(String username){
         String SQL = "select * from USERS where USERNAME = ?";
         MyUser myUser = jdbcTemplateObject.queryForObject(SQL, new Object[]{username}, new UserMapper());

         return myUser;
     }

}
