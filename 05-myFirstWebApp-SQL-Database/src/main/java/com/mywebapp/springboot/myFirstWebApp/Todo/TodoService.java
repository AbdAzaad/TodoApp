package com.mywebapp.springboot.myFirstWebApp.Todo;

import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TodoService {
        private static int todosCount = 0;
        private static List<Todo> todos = new ArrayList<> ();

        static {
                todos.add (new Todo(++todosCount,"Kalam","Learn Spring",  LocalDate.now ().plusYears (1),false));
                todos.add (new Todo(++todosCount,"Abdul","Learn Java",  LocalDate.now ().plusYears (2),false));
                todos.add (new Todo(++todosCount,"Abdul","Learn Python",  LocalDate.now ().plusYears (3),false));
                todos.add (new Todo(++todosCount,"Azaad","Learn Python",  LocalDate.now ().plusYears (3),false));

        }
        public void addTodo(String userName,String description,LocalDate targetDate,boolean done){
                Todo todo = new Todo (++todosCount,userName,description,targetDate,done);
                todos.add(todo);
        }

        public void deleteTodoById(int id){
                Predicate<?super Todo> predicate = todo-> todo.getId() == id;                   //Lambda function to loop over all the objects in todos list
                //--todosCount;
                todos.removeIf (predicate);
        }
        public Todo findById (int id) {
                Predicate<?super Todo> predicate = todo-> todo.getId() == id;
                Todo todo = todos.stream ().filter (predicate).findFirst ().get ();
                return todo;
        }
        public List<Todo> findByUsername(String userName){
                Predicate<?super Todo> predicate = todo-> todo.getUserName ().equalsIgnoreCase (userName) ;
                return todos.stream ().filter (predicate).collect(Collectors.toList());
        }

        public void updateTodo (@Valid Todo todo) {
                deleteTodoById (todo.getId ());
                todos.add (todo);
        }
}
