package com.mywebapp.springboot.myFirstWebApp.Todo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes ("name")                                      //To elongate a model , session is used in all classes where it has to sustain
public class TodoControllerJpa {
        private TodoRepository todoRepository;

        //To autowire it, the instance has to be passed to the constructor   (or) we can use @Autowired annotation also
        public TodoControllerJpa (TodoRepository todoRepository) {
                this.todoRepository= todoRepository;
        }

        @RequestMapping("todo-list")
        public String listAllTodos(ModelMap model){
                String userName = getUsernameFromLogin (model);
                List<Todo> todos = todoRepository.findByUserName (userName);
                model.addAttribute ("todos",todos);
                return "ListToDos";
        }

        @RequestMapping(value  = "add-todo", method = RequestMethod.GET)
        public String showNewTodo(ModelMap model){
                String userName = getUsernameFromLogin (model);
                Todo todo = new Todo (0,userName,"",LocalDate.now ().plusYears (1),false);
                model.put ("todo",todo);
                return "Todo";
        }


/*
        @RequestMapping(value  = "add-todo", method = RequestMethod.POST)
        public String addNewTodo(@RequestParam String description, ModelMap model){
                String userName = (String)model.get ("name");
                todoService.addTodo (userName,description, LocalDate.now ().plusYears (1),false);
                return "redirect:todo-list";
        }
        */

        // In order to provide the Two way binding between Todo.jsp and TodoController.java for "Description", we make use of Command bean (or) Form Backing
        // instead of passing the "Description" as a @RequestParam in the above method.

        @RequestMapping(value  = "add-todo", method = RequestMethod.POST)
        public String addNewTodo(ModelMap model, @Valid Todo todo , BindingResult error){               //Introducing validation using @Valid

                if(error.hasErrors ()){                                                                                                                      //if the validation is failed, it remains in the same page, showing the error
                        return "Todo";
                }
                String userName = getUsernameFromLogin (model);
                todo.setUserName (userName);
                todoRepository.save (todo);
//                todoService.addTodo (todo.getUserName (),todo.getDescription (), LocalDate.now ().plusYears (1),false);
                return "redirect:todo-list";
        }

        @RequestMapping("delete-todo")
        public String deleteTodo(@RequestParam int id){
//                todoService.deleteTodoById (id);
                todoRepository.deleteById (id);
                return "redirect:todo-list";
        }

        @RequestMapping( value = "update-todo", method = RequestMethod.GET)
        public String showUpdateTodoPage(@RequestParam int id,ModelMap model){
                Todo todo = todoRepository.findById (id).get ();
                model.addAttribute ("todo",todo);
                return "Todo";
        }

        @RequestMapping(value  = "update-todo", method = RequestMethod.POST)
        public String updateTodo(ModelMap model, @Valid Todo todo , BindingResult error){               //Introducing validation using @Valid

                if(error.hasErrors ()){                                                                                                                      //if the validation is failed, it remains in the same page, showing the error
                        return "Todo";
                }
                String userName = getUsernameFromLogin (model);
                List<Todo> todos = todoRepository.findByUserName (userName);
                todoRepository.save (todo);
                return "redirect:todo-list";
        }

        private  String getUsernameFromLogin (ModelMap model) {
                Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
                return authentication.getName ();
        }

}
