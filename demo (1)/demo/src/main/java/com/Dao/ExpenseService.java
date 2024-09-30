package com.Dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Expense;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expenses by month
    public List<Expense> getExpensesByMonth(String month) {
        return expenseRepository.findByMonth(month);
    }

    // Save an expense
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Delete an expense and calculate the total remaining expenses
    public double deleteExpenseAndCalculateTotal(Long id) {
        // Delete the expense by ID
        expenseRepository.deleteById(id);

        // After deletion, calculate the total amount of the remaining expenses
        List<Expense> remainingExpenses = expenseRepository.findAll();
        return remainingExpenses.stream()
                .mapToDouble(Expense::getExpenseAmount) // Get the expenseAmount of each expense
                .sum(); // Sum all the remaining expense amounts
    }

    // Optionally keep the original delete method if needed
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
