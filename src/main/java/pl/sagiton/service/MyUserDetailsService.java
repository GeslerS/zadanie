package pl.sagiton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import pl.sagiton.model.MyUser;
import pl.sagiton.model.UserDAOImpl;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by szymon on 22.02.16.
 */
@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDAOImpl userDaoImpl;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser user = userDaoImpl.getUser(username);

        if(user == null) throw new UsernameNotFoundException("User doesn't exist (" + username +")");

        List authList = new ArrayList();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getName(), user.getPassword(), authList);
    }
}
