package com.developer.patrolsimulator.repository;

import com.developer.patrolsimulator.db.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository(value = "UserRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserKey(UUID userKey);
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    List<Optional<UserEntity>> getAllByOrderByLastNameAsc();
}
