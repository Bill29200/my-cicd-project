package com.tacosmanager.controller;

import com.tacosmanager.dto.UserDTO;
import com.tacosmanager.entity.Role;
import com.tacosmanager.entity.User;
import com.tacosmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //.................................................................................ADD..............................
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO dto) {
        User user = userService.createUser(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    //.................................................................................READ.............................
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/nom")
    public ResponseEntity<List<User>> searchByNom(@RequestParam String nom) {
        List<User> users = userService.searchByNom(nom);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/filter/role")
    public ResponseEntity<List<User>> filterByRole(@RequestParam Role role) {
        List<User> users = userService.filterByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/fastfood/{fastfoodId}")
    public ResponseEntity<List<User>> findByFastfoodId(@PathVariable Long fastfoodId) {
        List<User> users = userService.findByFastfoodId(fastfoodId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/search/keyword")
    public ResponseEntity<List<User>> searchByKeyword(@RequestParam String keyword) {
        List<User> users = userService.searchByKeyword(keyword);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/with-telephone")
    public ResponseEntity<List<User>> findUsersWithTelephone() {
        List<User> users = userService.findUsersWithTelephone();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/role-and-fastfood")
    public ResponseEntity<List<User>> findByRoleAndFastfoodId(@RequestParam Role role, @RequestParam Long fastfoodId) {
        List<User> users = userService.findByRoleAndFastfoodId(role, fastfoodId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    //.................................................................................UPDATE...........................
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        User updatedUser = userService.updateUser(id, dto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    //.................................................................................DELETE...........................
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}