package com.example.expense.model;
import lombok.Data;
@Data
public class Expense {
 private String expenseId;
 private String title;
 private Double amount;
 private String date;
 private String category;
 private String receiptUrl;
}
