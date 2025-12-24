package com.example.expense.model;

public class Expense {

 private String expenseId;
 private String title;
 private Double amount;
 private String date;

 public Expense() {
 }

 public String getExpenseId() {
  return expenseId;
 }

 public void setExpenseId(String expenseId) {
  this.expenseId = expenseId;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public Double getAmount() {
  return amount;
 }

 public void setAmount(Double amount) {
  this.amount = amount;
 }

 public String getDate() {
  return date;
 }

 public void setDate(String date) {
  this.date = date;
 }
}
