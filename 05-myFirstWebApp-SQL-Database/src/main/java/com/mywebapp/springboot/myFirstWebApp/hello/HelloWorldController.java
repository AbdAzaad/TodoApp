package com.mywebapp.springboot.myFirstWebApp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


// @RestController can be used in place of Controller , which can remove the @ResponseBody also, but that does not work in case of JSP pages
@Controller
public class HelloWorldController {

        @RequestMapping("say-hello")
        @ResponseBody
        public String sayHello(){
                return "This is my model web application using spring boot!!";
        }

        @RequestMapping("say-hello-htmlPage")
        @ResponseBody
        public StringBuffer sayHelloHtmlPage(){

                StringBuffer sb = new StringBuffer ();

                sb.append ("<html >");
                sb.append ("<head>");
                sb.append (" <title>Welcome-page</title>");
                sb.append ("</head>");
                sb.append ("<body>");
                sb.append ("This is my Html page for the web app using spring");
                sb.append ("</body>");
                sb.append ("</html>");
                return sb;
        }
        //     \src\main\resources\META-INF\resources\WEB-INF\jsp (This is the location of the jsp file)
        @RequestMapping("say-hello-JSP")
        public String sayHelloJsp(){
                return "sayHelloJsp";                           //File name of the jsp file to be redirected (inside the quotes).
        }


}
