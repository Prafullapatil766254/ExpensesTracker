package com.example.Service;

import com.example.Entity.Expense;
import com.example.Repository.IExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpensesService {
    @Autowired
    IExpensesRepo expensesRepo;

    public String addExpense(Expense expense) {
        expensesRepo.save(expense);
        return "Expense added successfully..";
    }

    public Expense getExpenseById(Integer id) {
        return expensesRepo.findById(id).orElse(null);
    }

    public String updatePriceOfExpense(Integer id , Integer price) {
        Expense expense = expensesRepo.findById(id).orElse(null);
        if(expense != null){
            expense.setPrice(price);
            expensesRepo.save(expense);
            return "Price updated..";
        }else{
            return "Expense not found..";
        }
    }

    public String removeExpense(Integer id) {
       if(expensesRepo.findById(id) != null) {
           expensesRepo.deleteById(id);
           return "Deleted successfully";
       }
       return "Id is invalid";
    }

    public List<Expense> getExpensesOnDate(LocalDate date) {
        return expensesRepo.findByDate(date);
    }
}
