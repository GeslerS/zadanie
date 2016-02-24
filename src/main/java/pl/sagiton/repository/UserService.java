package pl.sagiton.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import pl.sagiton.model.MyUser;
import pl.sagiton.model.UserJDBCTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by szymon on 22.02.16.
 */

@Service("userService")
public class UserService {


    public Map<String, String> getUserDetails(String username){

        Map<String, String> userMap = new HashMap<String, String>();

        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        UserJDBCTemplate userJDBCTemplate =
                (UserJDBCTemplate)context.getBean("userJDBCTemplate");

        MyUser user = userJDBCTemplate.getUser(username);
        if(user == null) return null;


        userMap.put("username", user.getUsername());
        userMap.put("password", user.getUser_password());


        return userMap;
    }
}
