package com.barmjz.productivityapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> getUserByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.emailVerified = TRUE WHERE a.email = ?1")
    int verifyUser(String email);
}
