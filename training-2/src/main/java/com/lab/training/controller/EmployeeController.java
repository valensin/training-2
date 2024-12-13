package com.lab.training.controller;

import com.lab.training.dao.EmployeeDAO;
import com.lab.training.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeDAO employeeDAO;

    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeDAO.getAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeDAO.getById(id);
    }

    @PostMapping
    public String addEmployee(@RequestBody Employee employee) {
        int result = employeeDAO.create(employee);
        if (result == 1) {
            return "Employee added successfully!";
        } else {
            return "Failed";
        }
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        int result = employeeDAO.update(employee);
        if (result == 1) {
            return "Employee updated successfully!";
        } else {
            return "Failed";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        int result = employeeDAO.delete(id);
        if (result == 1) {
            return "Employee deleted successfully!";
        } else {
            return "Failed";
        }
    }
}
