package com.example.expense.controller;

import com.example.expense.model.Expense;
import com.example.expense.service.ExpenseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseService svc;

    @PostMapping
    public ResponseEntity<Expense> create(@RequestBody Expense e) {
        return ResponseEntity.ok(svc.create(e));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAll() {
        return ResponseEntity.ok(svc.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        svc.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
