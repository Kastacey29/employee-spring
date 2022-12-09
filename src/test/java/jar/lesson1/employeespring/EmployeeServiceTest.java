package jar.lesson1.employeespring;

import jar.lesson1.employeespring.exception.WrongDataException;
import jar.lesson1.employeespring.model.Employee;
import jar.lesson1.employeespring.record.EmployeeRequest;
import jar.lesson1.employeespring.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.el.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        employeeService.addEmployee(new EmployeeRequest("Ирина", "Семенова", 3, 2000));
        employeeService.addEmployee(new EmployeeRequest("Владимир", "Иванов", 2, 1500));
        employeeService.addEmployee(new EmployeeRequest("Илья", "Смирнов", 1, 4000));
        employeeService.addEmployee(new EmployeeRequest("Светлана", "Воронова", 2, 3000));
        employeeService.addEmployee(new EmployeeRequest("Михаил", "Макаров", 1, 3600));
    }

    @Test
    public void addEmployee() {
        EmployeeRequest request = new EmployeeRequest("testName", "testSurname", 3, 4200);
        Employee newEmployee = employeeService.addEmployee(request);
        String firstname = StringUtils.capitalize(request.getFirstName());
        String lastName = StringUtils.capitalize(request.getLastName());
        org.junit.jupiter.api.Assertions.assertEquals(newEmployee.getFirstName(), firstname);
        org.junit.jupiter.api.Assertions.assertEquals(newEmployee.getLastName(), lastName);
        Assertions.assertThat(employeeService.getAllEmployees()).contains(newEmployee);
    }

    @Test
    public void getSum() {
        int sum = employeeService.getSalarySum();
        org.junit.jupiter.api.Assertions.assertEquals(sum, 14100);
    }

    @Test
    public void getEmployeesWithSalaryMax() {
        Employee result = employeeService.getEmployeesWithSalaryMax();
        Assertions.assertThat(result).isNotNull().extracting(Employee::getFirstName).isEqualTo("Илья");
    }

    @Test
    public void getEmployeesWithSalaryMin() {
        Employee result = employeeService.getEmployeeWithSalaryMin();
        Assertions.assertThat(result).isNotNull().extracting(Employee::getFirstName).isEqualTo("Владимир");
    }

    @Test
    public void getEmployeesWithSalaryMoreAverage() {
        Collection<Employee> employees = employeeService.getEmployeesWithSalaryMoreAverage();

    }

    @Test
    public void removeEmployee() {
        employeeService.removeEmployee(employeeService.getAllEmployees().iterator().next().getId());
        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSize(4);
    }
}