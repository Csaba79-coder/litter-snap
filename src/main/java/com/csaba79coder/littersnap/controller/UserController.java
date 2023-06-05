package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.user.dto.UserModel;
import com.csaba79coder.littersnap.model.user.dto.UserModifyModel;
import com.csaba79coder.littersnap.model.user.dto.UserRegistrationModel;
import com.csaba79coder.littersnap.model.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    // TODO: render user details for the logged in user only!
    // TODO: create findByUserId method in ReportRepository

    private final UserService userService;

    @GetMapping(value = "/admin/users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserModel>> renderAllUsers() {
        return ResponseEntity.status(200).body(userService.findAllUsers());
    }

    @PostMapping(value = "/admin/users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> addNewUser(@RequestBody UserRegistrationModel model) {
        return ResponseEntity.status(201).body(userService.addNewUser(model));
    }

    @PutMapping(value = "/admin/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> modifyExistingUser(@PathVariable UUID id, @RequestBody UserModifyModel model) {
        return ResponseEntity.status(200).body(userService.modifyAnExistingUser(id, model));
    }

    @DeleteMapping(value = "/admin/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteAnExistingUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.status(204).build();
    }
}
