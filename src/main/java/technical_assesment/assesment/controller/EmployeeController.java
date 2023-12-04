package technical_assesment.assesment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import technical_assesment.assesment.bean.Employee;
import technical_assesment.assesment.repository.EmployeeRepository;
import technical_assesment.assesment.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
   EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.read();
    }
    @PostMapping("/create/{id}")
    public Employee create(@RequestBody Employee employee ,@PathVariable Long managerId){

        return employeeService.create(employee  ,managerId);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
         employeeService.delete(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id , @RequestBody Employee updatedEmployee){
    return employeeService.updateEmployee(id,updatedEmployee);
    }

    @PostMapping("/manager")
    public ResponseEntity<Employee> createManager(@RequestBody Employee manager) {
        Employee result = employeeService.createManager(manager);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @GetMapping("/managers")
    public List<Employee> allManagers(){
      return  employeeService.managers();
    }

}



