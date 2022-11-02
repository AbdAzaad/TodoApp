package com.mywebapp.springboot.myFirstWebApp.loginPage;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")                              //To elongate a model , session is used in all classes where it has to sustain
public class WelcomeController {
        /*
        @RequestMapping ("login")
        public String loginPage(@RequestParam String name, ModelMap model){
               model.put ("name",name);
                return "loginPage";                           //File name of the jsp file to be redirected (inside the quotes).
        }
        */
        @RequestMapping (value = "/", method = RequestMethod.GET)
        public String gotoWelcomePage(ModelMap model){
                model.put ("name",getUsername ());                                              //Getting username from Spring Security
                return "WelcomePage";
        }

        private String getUsername(){
                Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
                return authentication.getName ();
        }
}
