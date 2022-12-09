package jar.lesson1.employeespring.controller;

import jar.lesson1.employeespring.model.Employee;
import jar.lesson1.employeespring.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getEmployeesByDepartment(@PathVariable("id") int department) {
        return this.departmentService.getEmployeesByDepartment(department);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSumByDepartment(@PathVariable("id") int department) {
        return this.departmentService.getSumByDepartment(department);
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryByDepartment(@PathVariable("id") int department) {
        return this.departmentService.getMaxSalaryByDepartment(department);
    }

    @GetMapping("/{id}/salary/min")
    public int getMinSalaryByDepartment(@PathVariable("id") int department) {
        return this.departmentService.getMinSalaryByDepartment(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getGroupedEmployees() {
        return this.departmentService.getGroupedEmployees();
    }

}
