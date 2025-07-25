package com.expensetracker.model;

import jakarta.persistence.*;  //part of JPA, used for object-relational mapping (ORM).
import lombok.*;  //helps reduce boilerplate code (like getters, setters, constructors).

import java.time.LocalDateTime;  //date-time object used to store timestamps.

//annotations that give the class speicla behaviors
@Data
@Entity  //Marks this class as a JPA entity — it will map to a database table.
@Getter @Setter //Lombok will automatically generate getter and setter methods for all fields.
@NoArgsConstructor @AllArgsConstructor //Generates a constructor with no arguments. Required by JPA. / Generates a constructor with all fields as arguments.
@Builder//Enables the Builder pattern, letting you create objects like User.builder().name("Aimen").email("...").build()
public class User {  //maps to a table in mysql db called "user" using JPA

    @Id  //marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID auto-generated by the db
    private Long id;

    private String name;  //store user's name

    @Column(unique = true)  //Tells JPA to enforce uniqueness at the database level — no two users can have the same email.
    private String email; //Maps to an email column in the table.

    private String password; //password of the user

    private LocalDateTime createdAt; //stores timestamp of when user acc was created.
}
