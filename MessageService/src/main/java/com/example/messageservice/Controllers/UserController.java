package com.example.messageservice.Controllers;

import com.example.messageservice.Models.Role;
import com.example.messageservice.Models.User;
import com.example.messageservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.GetUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.GetAllUsers();
        if(userList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userList);
    }
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.SaveUser(user);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if(updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam long id) {
        boolean isdeleted = userService.DeleteUser(id);
        if(isdeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/findbyemail")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("/practitioners")
    public ResponseEntity<List<User>> getPractitioners() {
        List<User> practitioners = userService.findAllByRole(Role.PRACTITIONER);
        if(practitioners == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(practitioners);
    }
    @GetMapping("/patients")
    public ResponseEntity<List<User>> getPatients() {
        List<User> patients = userService.findAllByRole(Role.PATIENT);
        if(patients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patients);
    }
    @GetMapping("/staffs")
    public ResponseEntity<List<User>> getStaffs() {
        List<User> staffs = userService.findAllByRole(Role.STAFF);
        if(staffs == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staffs);
    }



}
