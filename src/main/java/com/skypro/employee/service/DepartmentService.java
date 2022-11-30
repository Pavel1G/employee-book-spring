package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeeByDepartment(int department) {
        return employeeService.getAllEmployee().stream().
                filter(e -> e.getDepartment() == department).
                collect(Collectors.toList());
    }


    public int getSalarySumByDepartment(int department) {
        return employeeService.getAllEmployee().stream().
                filter(e -> e.getDepartment() == department).
                mapToInt(e -> e.getSalary()).sum();
    }

    public int getSalaryMaxByDepartment(int department) {
        return getEmployeeByDepartment(department).stream().
                map(e -> e.getSalary())
                .max(Integer::compareTo)
                .orElse(0);
    }


    public int getSalaryMinByDepartment(int department) {
        return getEmployeeByDepartment(department).stream().
                map(e -> e.getSalary())
                .min(Integer::compareTo)
                .orElse(0);
    }

    public Map<Integer, List<Employee>> getDepartmentEmployees() {
        int countDepartment = employeeService.getAllEmployee().stream()
                .map(e -> e.getDepartment())
                .max(Integer::compareTo).orElse(0);

        Map<Integer, List<Employee>> result = new HashMap<>();
        for (int i = 1; i <= countDepartment; i++) {
            result.put(i, getEmployeeByDepartment(i));
        }

        return result;
    }
}
