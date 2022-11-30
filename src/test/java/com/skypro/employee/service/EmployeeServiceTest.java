package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.BindException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {
    private EmployeeService employeeService = new EmployeeService();

    private final HashMap<Integer, Employee> actualEmployees = new HashMap<>();
    private Employee employee;
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;


    @BeforeEach
    public void setUp() {
        employee = new Employee("Pavel", "Pavlov", 1, 50000);
        employee1 = new Employee("Petya", "Petrov", 2, 60000);
        employee2 = new Employee("Fedor", "Fedorov", 3, 90000);
        employee3 = new Employee("Katya", "Kotova", 3, 60000);

        actualEmployees.put(employee.getId(), employee);
        actualEmployees.put(employee1.getId(), employee1);
        actualEmployees.put(employee2.getId(), employee2);
        actualEmployees.put(employee3.getId(), employee3);
    }

    @Test
    void shouldReturnAllEmployee() {
        List<Employee> actualEmployees = List.of(employee, employee1, employee2, employee3);

        assertEquals(4, actualEmployees.size());
    }

    @Test
    void shouldComparingSalarySum() {
        int actualSalarySum = actualEmployees.values().stream().mapToInt(Employee::getSalary).sum();

        assertEquals(260000, actualSalarySum);
    }


    @Test
    void shouldComparingMinSalary() {
        int minSalary = actualEmployees.values().stream()
                .map(e -> e.getSalary()).min(Integer::compareTo)
                .orElse(0);
        assertEquals(50000, minSalary);
    }

    @Test
    void shouldComparingMaxSalary() {
        int maxSalary = actualEmployees.values().stream()
                .map(e -> e.getSalary()).max(Integer::compareTo)
                .orElse(0);
        assertEquals(90000, maxSalary);
    }

    @Test
    void shouldGetAverageSalary() {
        int averageSalary = (int) actualEmployees.values().stream()
                .mapToInt(e -> e.getSalary()).average()
                .orElse(0);

        assertEquals(65000, averageSalary);
    }
}
