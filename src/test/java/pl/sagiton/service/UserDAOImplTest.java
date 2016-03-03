package pl.sagiton.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.sagiton.model.MyUser;
import pl.sagiton.model.UserDAOImpl;

import javax.servlet.Filter;
import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by szymon on 01.03.16.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml ")
@WebAppConfiguration
public class UserDAOImplTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private DataSource dataSource;

    private MockMvc mvc;

    private UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();

        userDAOImpl.setDataSource(dataSource);
    }

    @Test
    public void authorizationTest() throws Exception {
        mvc.perform(formLogin().user("Szymon").password("Szymon123")).andExpect(authenticated());
    }

    @Test
    public void invalidAuthorizationTest() throws Exception {
        mvc.perform(formLogin().user("Szymon").password("InvalidPassword")).andExpect(unauthenticated());
    }


    @Test
    public void noAuthorizationTest() throws Exception {
        mvc.perform(get("/logged")).andExpect(unauthenticated());
    }






    @Test
    public void goodDataTest(){
        MyUser user =userDAOImpl.getUser("Szymon");
        assertEquals("Should be Szymon","Szymon",user.getName());
        assertEquals("Should be 4",4,user.getId());
        assertEquals("Should be Szymon123","Szymon123",user.getPassword());
    }

    @Test
    public void wrongDataTest(){
        MyUser user = userDAOImpl.getUser("Donna");
        assertNotEquals("Should be Szymon","Szymon",user.getName());
        assertNotEquals("Should be 4",4,user.getId());
        assertNotEquals("Should be Szymon123","Szymon123",user.getPassword());
    }


}
