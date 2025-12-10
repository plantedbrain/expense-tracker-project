package com.example.expense.controller;
import com.example.expense.model.Expense;
import com.example.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/expenses") @RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ExpenseController {
 private final ExpenseService svc;
 @PostMapping public ResponseEntity<Expense> create(@RequestBody Expense e){
   return ResponseEntity.ok(svc.create(e));
 }
 @GetMapping("/health") public ResponseEntity<String> health(){ return ResponseEntity.ok("OK"); }
}
