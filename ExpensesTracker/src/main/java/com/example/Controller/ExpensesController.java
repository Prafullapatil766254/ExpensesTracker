package com.example.Controller;

import com.example.Entity.Expense;
import com.example.Entity.User;
import com.example.Service.AuthenticationTokenService;
import com.example.Service.ExpensesService;
import com.example.Service.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ExpensesController {
    @Autowired
    ExpensesService expensesService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Autowired
    UserService userService;


    @PostMapping("expense")
    public String addExpense(@RequestBody Expense expense , @RequestParam String email , @RequestParam String token){
       if(authenticationTokenService.authenticate(email , token)){
           return expensesService.addExpense(expense);
       }else{
           return "Not authenticated user";
       }
    }

    @GetMapping("expenses")
    public User getExpensesOfUserById( @RequestParam Integer id , @RequestParam String email , @RequestParam String token){
        if(authenticationTokenService.authenticate(email , token)){
            return userService.getExpensesOfUserById(id);
        }
        return null;
    }

    @GetMapping("expense")
    public Expense getExpenseById(@RequestParam Integer id , @RequestParam String email , @RequestParam String token){
        if(authenticationTokenService.authenticate(email , token)){
            return expensesService.getExpenseById(id);
        }
        return null;
    }

    @PutMapping("expense")
    public String updatePriceOfExpense(@RequestParam Integer id , @RequestParam String email , @RequestParam String token , @RequestParam Integer price)
    {
        if(authenticationTokenService.authenticate(email , token)){
            return expensesService.updatePriceOfExpense(id , price);
        }
        return "Not authenticated user";
    }

    @DeleteMapping("expense")
    public String removeExpense(@RequestParam Integer id , @RequestParam String email , @RequestParam String token ){
        {
            if(authenticationTokenService.authenticate(email , token)){
                return expensesService.removeExpense(id);
            }
            return "Not authenticated user";
        }
    }

    @GetMapping("expense/id/{id}/date/{date}")
    public List<Expense> getExpensesOnDate(@PathVariable LocalDate date , @PathVariable String email , @PathVariable String token){
        {
            if(authenticationTokenService.authenticate(email , token)){
                return expensesService.getExpensesOnDate(date);
            }
            return null;
        }
    }

}
