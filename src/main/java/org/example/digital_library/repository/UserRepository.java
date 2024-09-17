package org.example.digital_library.repository;

import org.example.digital_library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.username FROM User u JOIN Bookmark bm ON bm.user.id = u.id GROUP BY u.username ORDER BY COUNT(bm) DESC")
    String findMostActiveUser();
}
