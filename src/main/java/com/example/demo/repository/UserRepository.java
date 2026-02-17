package com.example.demo.repository;

import com.example.demo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

import java.util.Optional;

=======
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
>>>>>>> main
public interface UserRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);
}
