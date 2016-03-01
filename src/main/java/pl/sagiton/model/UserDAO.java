package pl.sagiton.model;


import javax.sql.DataSource;
/*
* Created by szymon on 24.02.16.
 */
public interface UserDAO {
   void setDataSource(DataSource ds);
   MyUser getUser(String username);
}
