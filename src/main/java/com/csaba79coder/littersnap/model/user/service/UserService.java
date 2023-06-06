package com.csaba79coder.littersnap.model.user.service;

import com.csaba79coder.littersnap.model.user.dto.UserModel;
import com.csaba79coder.littersnap.model.user.dto.UserModifyModel;
import com.csaba79coder.littersnap.model.user.dto.UserRegistrationModel;
import com.csaba79coder.littersnap.model.user.entity.User;
import com.csaba79coder.littersnap.model.user.persistence.UserRepository;
import com.csaba79coder.littersnap.util.Mapper;
import com.csaba79coder.littersnap.util.Validator;
import com.csaba79coder.littersnap.value.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This class contains the user service.
 * Also include logs errors and exceptions.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:application.properties")
public class UserService {

    /**
     * Dependency injection fields.
     * <p>
     *     userRepository: the user repository
     * </p>
     */
    private final UserRepository userRepository;


    /**
     * Fields injected from application.properties.
     * <p>
     *     emailValidator: the validator regex injected from application.properties (email.validator.regexp)
     *     and this property is hided to IntelliJ IDEA's environment variables.
     * </p>
     */
    @Value("${validator.regexp}")
    private String emailValidator;

    /**
     * Fields injected from application.properties.
     * <p>
     *     passwordValidator: the validator regex injected from application.properties (validator.regexp)
     *     and this property is hided to IntelliJ IDEA's environment variables.
     * </p>
     */
    @Value("${password.validator.regexp}")
    private String passwordValidator;

    /**
     * This method finds all users.
     * @return the list of users (model)
     */
    public List<UserModel> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(Mapper::mapUserEntityToModel)
                .sorted(Comparator.comparing(UserModel::getFirstName))
                .collect(Collectors.toList());
    }

    /**
     * This method add a new user
     * @param model the user registration model
     * @return the user model
     * @throws IllegalArgumentException if input is invalid
     * check the email and password if it is valid
     * if fields are not null set it to the entity and modify it
     */
    public UserModel addNewUser(UserRegistrationModel model) {
        String errorMessage;
        boolean isValidEmail = isValidEmail(model.getEmail());
        boolean isValidPassword = isValidPassword(model.getPassword());

        if (!isValidEmail) {
            errorMessage = "Invalid email format.";
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (!isValidPassword) {
            errorMessage = "Invalid password format.";
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (findUserByEmail(model.getEmail())) {
            errorMessage = "Email already in use: " + model.getEmail();
            log.error(errorMessage);
            throw new InputMismatchException(errorMessage);
        }

        if (!Objects.equals(model.getPassword(), model.getPasswordConfirmation())) {
            errorMessage = "Password and confirmation do not match.";
            log.error(errorMessage);
            throw new InputMismatchException(errorMessage);
        }

        return Mapper.mapUserEntityToModel(userRepository.save(Mapper.mapUserRegistrationModelToUserEntity(model)));
    }

    /**
     * This method modify an existing user
     * @param model the user registration model
     * @return the user model
     * @throws NoSuchElementException if user is not found
     * @throws IllegalArgumentException if input is invalid
     * @throws InputMismatchException if email is already in use
     * check the email and password if it is valid
     * if fields are not null set it to the entity and modify it
     */
    public UserModel modifyAnExistingUser(UUID id, UserModifyModel model) {
        String errorMessage;
        boolean isValidEmail = isValidEmail(model.getEmail());
        boolean isValidPassword = isValidPassword(model.getPassword());

        User currentUser = userRepository.findUsersById(id)
                .orElseThrow(() -> {
                    String message = "User not found with id: " + id;
                    log.error(message);
                    throw new NoSuchElementException(message);
                });

        // Check if the email is the same
        if (!currentUser.getEmail().equals(model.getEmail())) {
            if (findUserByEmail(model.getEmail())) {
                errorMessage = "Email already in use: " + model.getEmail();
                log.error(errorMessage);
                throw new InputMismatchException(errorMessage);
            }

            // Check if the new email is valid
            if (!isValidEmail) {
                errorMessage = "Invalid email format.";
                log.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }

            if (!isValidPassword) {
                errorMessage = "Invalid password format.";
                log.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }

            // Check if the new email and the repeating email match
            if (!Objects.equals(model.getPassword(), model.getPasswordConfirmation())) {
                errorMessage = "Password and confirmation do not match.";
                log.error(errorMessage);
                throw new InputMismatchException(errorMessage);
            }

            // Delete the current user
            userRepository.delete(currentUser);

            // Create a new user entity using modify model
            User newUser = new User();
            newUser.setEmail(model.getEmail());
            if (model.getEmail() != null) {
                newUser.setEmail(model.getEmail());
            } else {
                newUser.setEmail(currentUser.getEmail());
            }
            if (model.getPassword() != null) {
                newUser.setPassword(model.getPassword());
            } else {
                newUser.setPassword(currentUser.getPassword());
            }
            if (model.getFirstName() != null) {
                newUser.setFirstName(model.getFirstName());
            } else {
                newUser.setFirstName(currentUser.getFirstName());
            }
            if (model.getRole() != null) {
                newUser.setRole(Role.valueOf(model.getRole().name()));
            } else {
                newUser.setRole(Role.valueOf(currentUser.getRole().name()));
            }

            // Save entity & return the user model
            return Mapper.mapUserEntityToModel(userRepository.save(newUser));

        } else {
            // Update the necessary fields of the current user
            if (model.getEmail() != null) {
                currentUser.setEmail(model.getEmail());
            }
            if (model.getPassword() != null) {
                currentUser.setPassword(model.getPassword());
            }
            if (model.getFirstName() != null) {
                currentUser.setFirstName(model.getFirstName());
            }
            if (model.getRole() != null) {
                currentUser.setRole(Role.valueOf(model.getRole().name()));
            }
            currentUser.setUpdatedAt(LocalDateTime.now());

            // Save the current user & return the user model
            return Mapper.mapUserEntityToModel(userRepository.save(currentUser));
        }
    }

    /**
     * This method deletes a user by id.
     * @param id the id of the user
     */
    public void deleteUser(UUID id) {
        User currentUser = userRepository.findUsersById(id)
                .orElseThrow(() -> {
                    String message = "User not found with id: " + id;
                    log.error(message);
                    throw new IllegalArgumentException(message);
                });
        userRepository.delete(currentUser);
    }

    /**
     * This method finds a user by email.
     * @param email the id of the user
     * @return the user model
     */
    public boolean findUserByEmail(String email) {
        return userRepository.findUsersByEmail(email).isPresent();
    }

    /**
     * This validates email
     * @param email the email of the user
     * @return boolean true or false regarding is email valid or not
     */
    private boolean isValidEmail(String email) {
        return Validator.patternMatches(email, emailValidator);
    }

    /**
     * This validates email
     * @param password the password of the user
     * @return boolean true or false regarding is password (and confirmationPassword) valid or not
     */
    private boolean isValidPassword(String password) {
        return Validator.patternMatches(password, passwordValidator);
    }
}
