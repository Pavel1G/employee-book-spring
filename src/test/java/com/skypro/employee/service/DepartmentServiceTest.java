package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    private List<Employee> actualEmployees;

    @BeforeEach
    private void setUp() {
        Employee employee = new Employee("Pavel", "Pavlov", 1, 50000);
        Employee employee1 = new Employee("Vanya", "Ivanov", 1, 70000);
        Employee employee2 = new Employee("Petya", "Petrov", 2, 60000);
        Employee employee3 = new Employee("Fedor", "Fedorov", 3, 90000);
        Employee employee4 = new Employee("Marina", "Marinova", 2, 50000);
        Employee employee5 = new Employee("Katya", "Kotova", 3, 60000);
        Employee employee6 = new Employee("Name", "Neimov", 1, 150000);

        actualEmployees = List.of(employee, employee1, employee2, employee3, employee4, employee5, employee6);

        when(employeeService.getAllEmployee()).thenReturn(actualEmployees);
    }

    @Test
    void shouldEmployeesByDepartments() {
        final int department = 3;

        List<Employee> actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());

        List<Employee> expected = departmentService.getEmployeeByDepartment(department);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnSalarySumByDepartment() {
        final int department = 2;

        int actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(e -> e.getSalary()).sum();

        int expected = departmentService.getSalarySumByDepartment(department);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnSalaryMaxByDepartment() {
        final int department = 1;

        int actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == department)
                .map(e -> e.getSalary())
                .max(Integer::compareTo)
                .orElse(0);

        int expected = departmentService.getSalaryMaxByDepartment(department);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnSalaryMinByDepartment() {
        final int department = 3;

        int actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == department)
                .map(e -> e.getSalary())
                .min(Integer::compareTo)
                .orElse(0);

        int expected = departmentService.getSalaryMinByDepartment(department);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmployeesByDepartments() {
        Map<Integer, List<Employee>> actual = actualEmployees.stream()
                .map(Employee::getDepartment).collect(Collectors.toSet()).stream()
                .collect(Collectors.toMap(department -> department,
                        department -> actualEmployees.stream().filter(e -> e.getDepartment() == department)
                                .collect(Collectors.toList())));

        Map<Integer, List<Employee>> expected = departmentService.getDepartmentEmployees();

        assertEquals(expected, actual);
    }
}
