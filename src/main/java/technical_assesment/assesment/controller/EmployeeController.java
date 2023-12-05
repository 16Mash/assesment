package technical_assesment.assesment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import technical_assesment.assesment.bean.Employee;
import technical_assesment.assesment.repository.EmployeeRepository;
import technical_assesment.assesment.service.EmployeeService;

import javax.lang.model.util.Elements;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {

    @Autowired
   EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.read();
    }



    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PatchMapping("/{employeeId}/assign-manager/{managerId}")
    public ResponseEntity<Employee> assignManagerToEmployee(
            @PathVariable Long employeeId,
            @PathVariable Long managerId) {
        Employee employee = employeeService.viewEmployee(employeeId);
        Employee manager = employeeService.viewEmployee(managerId);

        if (employee != null && manager != null) {
            employee.setManager(manager);
            Employee updatedEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
         employeeService.delete(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id , @RequestBody Employee updatedEmployee){
    return employeeService.updateEmployee(id,updatedEmployee);
    }


    //create a manager
    @PostMapping("/managers")
    public ResponseEntity<Employee> createManager(@RequestBody Employee manager) {
        // Additional validation or manager-specific logic if needed
        manager.setIsManager(true);
        Employee savedManager = employeeService.saveEmployee(manager);
        return new ResponseEntity<>(savedManager, HttpStatus.CREATED);
    }

    //viewing all managers
    @GetMapping("/managers")
    public ResponseEntity<List<Employee>> getAllManagers() {
        List<Employee> managers = employeeService.viewAllManagers();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }


//promote employe to be a manager
@PatchMapping("/{employeeId}/promote")
public ResponseEntity<Employee> promoteToManager(@PathVariable Long employeeId) {
    Employee promotedEmployee = employeeService.promoteToManager(employeeId);
    return new ResponseEntity<>(promotedEmployee, HttpStatus.OK);
}



}



