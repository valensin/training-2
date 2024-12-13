package com.lab.training.dao;

import com.lab.training.model.Employee;
import com.lab.training.model.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> getAll() {
        String sql = "SELECT * FROM public.employee";
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }

    public Employee getById(Long id) {
        String sql = "SELECT * FROM public.employee WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeMapper());
    }

    public int create(Employee employee) {
        String sql = "INSERT INTO public.employee (first_name, last_name, salary, address, phone) " + "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getAddress(), employee.getPhone());
    }

    public int update(Employee employee) {
        String sql = "UPDATE public.employee SET first_name = ?, last_name = ?, salary = ?, address = ?, phone = ? " + "WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getAddress(), employee.getPhone(), employee.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM public.employee WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
