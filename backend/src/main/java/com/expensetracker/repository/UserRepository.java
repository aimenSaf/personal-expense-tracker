//Spring Data JPA repo interface for managing User entities in a db.

package com.expensetracker.repository;

import com.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;  //built in CURD operations

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //repo interface that provides data access logic for the user entity
    //extending JPARepo - gives access to many methods
    //Saves time with SQL
    Optional<User> findByEmail(String email); //SQL version: SELECT * FROM user WHERE email = ?
    //finds the user with the email in question.
}
