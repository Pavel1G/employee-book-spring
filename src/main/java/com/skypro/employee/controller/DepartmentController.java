package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("department/employees")
    public Collection<Employee> getEmployeesByDepartment(@RequestParam("department") int department) {
        return this.departmentService.getEmployeeByDepartment(department);
    }

    @GetMapping("department/{id}/salary/sum")
    public int getSalarySumByDepartment(@RequestParam("id") int department) {
        return departmentService.getSalarySumByDepartment(department);
    }

    @GetMapping("department/{id}/salary/max")
    public int getSalaryMaxByDepartment(@RequestParam("id") int department) {
        return departmentService.getSalaryMaxByDepartment(department);
    }

    @GetMapping("department/{id}/salary/min")
    public int getSalaryMinByDepartment(@RequestParam("id") int department) {
        return departmentService.getSalaryMinByDepartment(department);
    }

    @GetMapping("department/employees")
    public Map<Integer, List<Employee>> getDepartmentEmployees() {
        return departmentService.getDepartmentEmployees();
    }
}
