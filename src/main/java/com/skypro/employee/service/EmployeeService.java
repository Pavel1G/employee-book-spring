package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

// Пакет и класс, в котором хранятся сами объекты класса Employee.
// Здесь будет храниться коллекция объектов Employee, и методы работы с ними.

//Аннотация @Service - показывает что этот класс может использоваться только в единственном
// экземпляре и показывает нам, что тут хранится сервисы по обработке объектов.
@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployee() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
//        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
//            throw new IllegalArgumentException("Employee name should be set.");
//        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getEmployeeMinSalary() {
        return employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee getEmployeeMaxSalary() {
        return employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public List<Employee> getEmployeesHightSalary() {
        double avarageSalary = employees.values().stream()
                .mapToInt(employees -> employees.getSalary())
                .average().getAsDouble();
        return employees.values().stream()
                .filter(x -> x.getSalary() > avarageSalary)
                .collect(Collectors.toList());
    }
}
