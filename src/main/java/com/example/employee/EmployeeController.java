package com.example.employee;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    // POST - Add Employee
    @PostMapping
    public ResponseEntity<String> addEmployee(
            @RequestBody Employee emp) {

        employees.add(emp);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Employee added successfully");
    }

    // GET - All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // GET - Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable int id) {

        for (Employee emp : employees) {

            if (emp.getId() == id) {
                return ResponseEntity.ok(emp);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE - Employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id) {

        for (Employee emp : employees) {

            if (emp.getId() == id) {

                employees.remove(emp);

                return ResponseEntity.ok(
                        "Employee deleted successfully");
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Employee not found");
    }

}
