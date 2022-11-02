package com.mywebapp.springboot.myFirstWebApp.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TodoRepository extends JpaRepository<Todo, Integer> {

        //Customised methods
        public List<Todo> findByUserName (String userName);
}
