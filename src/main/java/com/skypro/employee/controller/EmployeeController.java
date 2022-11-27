package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

// Это контороллер, реализующий принцип REST. Этот принцип хорош тем, что он хранит и выполняет все команды в
// серверной части, а в пользовательский интерфейс отправляет только некоторые данные.
// С помощью этого принципа можно реализовать одно и то же приложение на разных клиентах - мобильное, десктопное и браузерное.

/**
 * HTTP методы
 * GET - получение ресурса или набора ресурса (например, вернуть всех сотрудников)
 * POST - создание ресурса (например, создать нового сотрудника)
 * PUT - модификация сотрудника (например, вернуть все поля сотрудника и изменить их)
 * PATCH - частичная модификация ресурса (например, изменить одно поле сотрудника)
 * DELETE - метод удаления ресурса.
 */

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    // Принцип иньекции зависимости - создаем контроллер, куда передаем класс EmployeeService
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployee();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public Employee getEmployeeMinSalary() {
        return this.employeeService.getEmployeeMinSalary();
    }

    @GetMapping("/employee/salary/max")
    public Employee getEmployeeMaxSalary(){
        return this.employeeService.getEmployeeMaxSalary();
    }

    @GetMapping("employee/high-salary")
    public List<Employee> getEmployeesHighSalary(){
        return this.employeeService.getEmployeesHightSalary();
    }

}
