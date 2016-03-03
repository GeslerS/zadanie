package pl.sagiton.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by szymon on 22.02.16.
 */
@Controller
public class HelloController {


    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public String homePage(){
        return "logged";
    }

    @RequestMapping(value = "/wrong", method = RequestMethod.GET)
    public String wrongPasswordPage(){
        return "wrong";
    }

}
