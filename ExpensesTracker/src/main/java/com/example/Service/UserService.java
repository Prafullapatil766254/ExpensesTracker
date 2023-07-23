package com.example.Service;

import com.example.Entity.AuthenticationToken;
import com.example.Entity.User;
import com.example.Repository.IAuthTokenRepo;
import com.example.Repository.IUserRepo;
import com.example.Service.Utility.EmailHandler;
import com.example.Service.Utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    public String signUpUser(User user) {

        String newEmail = user.getEmail();

        if(newEmail == null)
        {
            return "Invalid email";

        }


        User existingUser = userRepo.findFirstByEmail(newEmail);

        if(existingUser != null)
        {
            return  "Email already registered..Please signIn to use our service..";

        }


        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getPassword());


            user.setPassword(encryptedPassword);
            userRepo.save(user);

            return "User registered successfully!!!";
        }
        catch(Exception e)
        {
            return "Internal error occurred during sign up..Please try again later..";

        }
    }

    public String signInUser(String userEmail, String password) {
        if(userEmail == null)
        {
            return "Invalid email";

        }

        //check if user is exists or not
        User existingUser = userRepo.findFirstByEmail(userEmail);

        if(existingUser == null)
        {
            return  "Email not registered!!!";
        }

        // if we cant able to return then it means user exist so need to verify and create session for user

        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(password);

            if(existingUser.getPassword().equals(encryptedPassword))
            {

                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authenticationTokenService.save(authToken);

                EmailHandler.sendEmail(userEmail,"Authentication token received via signing in",authToken.getTokenValue());
                return "Token sent to your registered email";
            }
            else {
                return "Invalid credentials!!!";
            }
        }
        catch(Exception e)
        {
            return  "Internal error occurred during sign in";
        }
    }

    public String signOut(String email) {
        User user = userRepo.findFirstByEmail(email);
        AuthenticationToken authenticationToken = authenticationTokenService.findFirstByUser(user);
        authenticationTokenService.delete(authenticationToken);
        return "Sign out successfully..";
    }

    public User getExpensesOfUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }
}
