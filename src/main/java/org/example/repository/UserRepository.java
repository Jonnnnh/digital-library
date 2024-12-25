package org.example.repository;

import org.example.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.username FROM User u JOIN Bookmark b ON u.id = b.user.id GROUP BY u.username ORDER BY COUNT(b) DESC")
    List<String> findMostActiveUsers();
    Optional<User> findByUsername(String username);

}
