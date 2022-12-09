package jar.lesson1.employeespring;

import jar.lesson1.employeespring.model.Employee;
import jar.lesson1.employeespring.record.EmployeeRequest;
import jar.lesson1.employeespring.service.DepartmentService;
import jar.lesson1.employeespring.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    private final List<Employee> employees = List.of(
            new Employee("Ирина", "Семенова", 3, 2000),
            new Employee("Владимир", "Иванов", 2, 1500),
            new Employee("Илья", "Смирнов", 1, 4000),
            new Employee("Светлана", "Воронова", 2, 3000),
            new Employee("Михаил", "Макаров", 1, 3600)
    );

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        when(employeeService.getAllEmployees()).thenReturn(employees);
    }

    @Test
    public void getEmployeesByDepartment() {
        Collection<Employee> employees1 = this.departmentService.getEmployeesByDepartment(2);
        Assertions.assertThat(employees1).hasSize(2).contains(employees.get(1), employees.get(3));
    }

    @Test
    public void getSumByDepartment() {
        int result = this.departmentService.getSumByDepartment(1);
        org.junit.jupiter.api.Assertions.assertEquals(result, 7600);
    }

    @Test
    public void getMaxSalaryByDepartment() {
        int result = this.departmentService.getMaxSalaryByDepartment(2);
        org.junit.jupiter.api.Assertions.assertEquals(result, 3000);
    }

    @Test
    public void getMinSalaryByDepartment() {
        int result = this.departmentService.getMinSalaryByDepartment(1);
        org.junit.jupiter.api.Assertions.assertEquals(result, 3600);
    }

    @Test
    public void getGroupedEmployees() {
        Map<Integer, List<Employee>> employeesByDepartments = this.departmentService.getGroupedEmployees();
        Assertions.assertThat(employeesByDepartments).hasSize(3)
                .containsEntry(1, List.of(employees.get(2), employees.get(4)))
                .containsEntry(2, List.of(employees.get(1), employees.get(3)))
                .containsEntry(3, List.of(employees.get(0)));
    }

}
