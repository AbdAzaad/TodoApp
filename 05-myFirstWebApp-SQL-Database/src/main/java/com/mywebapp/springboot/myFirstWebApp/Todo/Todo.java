package com.mywebapp.springboot.myFirstWebApp.Todo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Todo {
        @Id
        @GeneratedValue
        private int id;
        private String userName;
        @Size(min = 1, message = "Enter atleast 10 characters")                                        //Providing validation for the variable description
        private String description;
        private LocalDate targetDate;
        private boolean done;

        //Create a default constructor for the @Entity, in order to map the variables to the database
        public Todo () {
        }

        public Todo (int id, String userName, String description, LocalDate targetDate, boolean done) {
                this.id = id;
                this.userName = userName;
                this.description = description;
                this.targetDate = targetDate;
                this.done = done;
        }

        public int getId () {
                return id;
        }

        public void setId (int id) {
                this.id = id;
        }

        public String getUserName () {
                return userName;
        }

        public void setUserName (String userName) {
                this.userName = userName;
        }

        public String getDescription () {
                return description;
        }

        public void setDescription (String description) {
                this.description = description;
        }

        public LocalDate getTargetDate () {
                return targetDate;
        }

        public void setTargetDate (LocalDate targetDate) {
                this.targetDate = targetDate;
        }

        public boolean isDone () {
                return done;
        }

        public void setDone (boolean done) {
                this.done = done;
        }

        @Override
        public String toString () {
                return "Todo{" +
                        "id=" + id +
                        ", userName='" + userName + '\'' +
                        ", description='" + description + '\'' +
                        ", targetDate=" + targetDate +
                        ", done=" + done +
                        '}';
        }
}
