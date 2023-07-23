package com.example.Repository;

import com.example.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IExpensesRepo extends JpaRepository<Expense, Integer> {
    List<Expense> findByDate(LocalDate date);
}
