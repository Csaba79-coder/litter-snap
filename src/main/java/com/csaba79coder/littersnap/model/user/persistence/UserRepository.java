package com.csaba79coder.littersnap.model.user.persistence;

import com.csaba79coder.littersnap.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * This class contains the user repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * This method finds a user by email.
     *
     * @param email the user email
     * @return the optional user
     */
    Optional<User> findUsersByEmail(String email);

    /**
     * This method finds a user by id.
     *
     * @param id the user id
     * @return the optional user
     */
    Optional<User> findUsersById(UUID id);
}