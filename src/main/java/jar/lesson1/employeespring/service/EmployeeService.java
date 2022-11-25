package jar.lesson1.employeespring.service;

import jar.lesson1.employeespring.model.Employee;
import jar.lesson1.employeespring.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class EmployeeService {
private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return  this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw  new IllegalArgumentException("Name not found!");
        }
        Employee employee=new Employee(employeeRequest.getFirstName(),employeeRequest.getLastName(),
                employeeRequest.getDepartment(),employeeRequest.getSalary());

        this.employees.put(employee.getId(),employee);
        return  employee;
    }

    public int getSalarySum() {
        return this.employees.values().stream()
                .mapToInt(Employee::getSalary).sum();
    }

    public Employee getEmployeesWithSalaryMax() {
        return this.employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }
    public Employee getEmployeeWithSalaryMin() {
        return this.employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }
    public Collection<Employee> getEmployeesWithSalaryMoreAverage() {
        double average=  this.employees.values().stream().
                mapToInt(Employee::getSalary).average().orElseThrow();
return this.employees.values().stream()
        .filter(employee -> employee.getSalary()>average).collect(Collectors.toList());
    }
}
