package jar.lesson1.employeespring.service;

import jar.lesson1.employeespring.exception.UncorrectValueException;
import jar.lesson1.employeespring.exception.WrongDataException;
import jar.lesson1.employeespring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Collection<Employee> getEmployeesByDepartment(int department) {
        return employeeService.getAllEmployees().stream().filter(employee -> employee.getDepartment() == department).
                collect(Collectors.toList());
    }

    public int getSumByDepartment(int department) {
        return employeeService.getAllEmployees().stream().filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary).sum();
    }

    public int getMaxSalaryByDepartment(int department) {
        return employeeService.getAllEmployees().stream().filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary).max().orElseThrow(UncorrectValueException::new);
    }

    public int getMinSalaryByDepartment(int department) {
        return employeeService.getAllEmployees().stream().filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary).min().orElseThrow(UncorrectValueException::new);
    }

    public Map<Integer, List<Employee>> getGroupedEmployees() {
        return employeeService.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
