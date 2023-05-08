package com.sr.chatpanel.repositories;

import com.sr.chatpanel.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //@Query(value = "SELECT * FROM User u WHERE u.email = 1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
