package org.example.digital_library.repository;

import org.example.digital_library.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u.username FROM UserEntity u JOIN BookmarkEntity b ON u.id = b.user.id GROUP BY u.username ORDER BY COUNT(b) DESC")
    List<String> findMostActiveUsers();
    Optional<UserEntity> findByUsername(String username);

}
