package com.omni.webapp.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
