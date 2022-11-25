package jar.lesson1.employeespring.controller;

import jar.lesson1.employeespring.model.Employee;
import jar.lesson1.employeespring.record.EmployeeRequest;
import jar.lesson1.employeespring.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

@RestController
public class EmployeeController {
    private  final EmployeeService employeeService;
public EmployeeController (EmployeeService employeeService) {
    this.employeeService=employeeService;
}
    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
    return  this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee create(@RequestBody EmployeeRequest employeeRequest) {
    return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum() {
    return this.employeeService.getSalarySum();
        }

    @GetMapping("/employees/salary/max")
    public Employee getEmployeeWithSalaryMax() {
        return this.employeeService.getEmployeesWithSalaryMax();
    }

    @GetMapping("/employees/salary/min")
    public Employee getEmployeeWithSalaryMin() {
        return this.employeeService.getEmployeeWithSalaryMin();
    }

    @GetMapping("/employees/high-salary")
    public Collection<Employee> getEmployeesWithSalaryMoreAverage() {
        return this.employeeService.getEmployeesWithSalaryMoreAverage();
    }
}
