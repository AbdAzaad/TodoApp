package com.mywebapp.springboot.myFirstWebApp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SpringSecurityConfiguration {

        @Bean
        public InMemoryUserDetailsManager configureUserDetails(){

                Function<String,String>  passwordEncoder =
                        input -> passwordEncoder ().encode (input);

                UserDetails userDetails1 = createNewUser ("Abdul", "pass1", passwordEncoder);
                UserDetails userDetails2 = createNewUser ("Kalam", "pass2", passwordEncoder);
                UserDetails userDetails3 = createNewUser ("Azaad", "pass3", passwordEncoder);

                return new InMemoryUserDetailsManager (userDetails1,userDetails2,userDetails3);                //can take any no. of users as parameters
        }

        private UserDetails createNewUser (String username, String password, Function<String, String> passwordEncoder) {
                UserDetails userDetails = User.builder ()
                                                .passwordEncoder(passwordEncoder)
                                                .username(username)
                                                .password(password)
                                                .roles("USER","ADMIN")
                                                .build();
                return userDetails;
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
                return  new BCryptPasswordEncoder ();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                //All URLs are protected
                http.authorizeHttpRequests (
                        auth -> auth.anyRequest ().authenticated ());

                //A login form is shown for unauthorized requests
                http.formLogin (withDefaults ());

                http.csrf ().disable ();

                //h2 database uses frames, so disable the option to enable it
                http.headers ().frameOptions ().disable ();

                return http.build ();
        }
}
