package com.csaba79coder.littersnap.model.user.persistence;

import com.csaba79coder.littersnap.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUsersByEmail(String email);
    Optional<User> findUsersById(UUID id);
}