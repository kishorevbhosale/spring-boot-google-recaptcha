package com.captcha.demo.service;

import com.captcha.demo.entity.Employee;
import com.captcha.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;

    public Integer createUser(Employee employee) {
        return repo.save(employee).getId();
    }

    public List<Employee> getAllUsers() {
        return repo.findAll();
    }

}
