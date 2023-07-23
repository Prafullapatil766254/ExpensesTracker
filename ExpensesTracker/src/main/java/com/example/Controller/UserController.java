package com.example.Controller;

import com.example.Entity.User;
import com.example.Service.AuthenticationTokenService;
import com.example.Service.ExpensesService;
import com.example.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ExpensesService expensesService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @PostMapping("user/signUp")
    public String signUpUser(@RequestBody User user){
        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String signInUser(@RequestParam String userEmail , @RequestParam String password ){
        return userService.signInUser(userEmail , password);
    }

    @DeleteMapping("user/signOut")
    public String signOut(@RequestParam String email , @RequestParam String authToken){
        if(authenticationTokenService.authenticate(email , authToken)){
            return userService.signOut(email);
        }else{
            return "You are not valid user sign in first to signOut";
        }
    }



}
