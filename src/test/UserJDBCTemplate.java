import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.sagiton.model.MyUser;

import javax.sql.DataSource;

import static org.junit.Assert.*;

/**
 * Created by szymon on 28.02.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/Beans.xml"})
public class UserJDBCTemplate {

    @Autowired
    private DataSource dataSource;

    pl.sagiton.model.UserJDBCTemplate userJDBCTemplate = new pl.sagiton.model.UserJDBCTemplate();


    @Before
    public void jdbcTemplateInit(){
        userJDBCTemplate.setDataSource(dataSource);
    }

    @Test
    public void goodDataTest(){

        MyUser user = userJDBCTemplate.getUser("Szymon");
        assertEquals("Should be Szymon","Szymon",user.getUsername());
        assertEquals("Should be 4",4,user.getUser_id());
        assertEquals("Should be Szymon123","Szymon123",user.getUser_password());
    }


    @Test
    public void wrongDataTest(){

        MyUser user = userJDBCTemplate.getUser("Donna");
        assertNotEquals("Should be Szymon","Szymon",user.getUsername());
        assertNotEquals("Should be 4",4,user.getUser_id());
        assertNotEquals("Should be Szymon123","Szymon123",user.getUser_password());
    }
}