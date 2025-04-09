package io.reflectoring.bbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.reflectoring.bbackend.dto.SalesStats;
import io.reflectoring.bbackend.dto.OccupancyStats;
import io.reflectoring.bbackend.dto.EmployeeRequest;
import io.reflectoring.bbackend.entity.Employee;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin(origins = "http://localhost:3000")
public class ManagerController {

    @GetMapping("/sales/daily")
    public ResponseEntity<SalesStats> getDailySales() {
        // Geçici olarak boş stats dönüyoruz
        return new ResponseEntity<>(new SalesStats(), HttpStatus.OK);
    }

    @GetMapping("/occupancy")
    public ResponseEntity<OccupancyStats> getOccupancyRates() {
        // Geçici olarak boş stats dönüyoruz
        return new ResponseEntity<>(new OccupancyStats(), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeRequest request) {
        // Geçici olarak null dönüyoruz
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/employees/recent")
    public ResponseEntity<List<Employee>> getRecentEmployees() {
        // Geçici olarak boş liste dönüyoruz
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
} 