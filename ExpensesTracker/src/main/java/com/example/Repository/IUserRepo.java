package com.example.Repository;

import com.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    User findFirstByEmail(String newEmail);
}
