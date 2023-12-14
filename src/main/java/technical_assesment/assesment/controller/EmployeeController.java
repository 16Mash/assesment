package technical_assesment.assesment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import technical_assesment.assesment.EmployeeType;
import technical_assesment.assesment.bean.Employee;
import technical_assesment.assesment.bean.EmployeeDTO;
import technical_assesment.assesment.exception.EmployeeNotFoundException;
import technical_assesment.assesment.repository.EmployeeRepository;
import technical_assesment.assesment.service.EmployeeService;

import javax.lang.model.util.Elements;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
   EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.read();
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        Enum t = employee.getEmployeeType();
        if (t.equals(EmployeeType.CEO) && employeeRepository.findByEmployeeType(EmployeeType.CEO).isPresent()){
            System.out.println(t);
            throw new IllegalStateException( "CEO Already Exists: Company can have only one CEO");

        }
        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println(t);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
 @GetMapping("/view-employee/{id}")
         public EmployeeDTO viewEmployee(@PathVariable Long id){
        if (employeeRepository.findById(id).isPresent()){
            return employeeService.employee(id);
        }
        throw new EmployeeNotFoundException("Employee with id :" + id + " Not Found");

         }
    @PatchMapping("/{employeeId}/assign-manager/{managerId}")
    public ResponseEntity<Employee> assignManagerToEmployee(
            @PathVariable Long employeeId,
            @PathVariable Long managerId) {
        Employee employee = employeeService.viewEmployee(employeeId);
        Employee manager = employeeService.viewEmployee(managerId);

        if (employee != null && manager != null && employeeRepository.findByIdAndIsManagerTrue(managerId).isPresent()) {
            employee.setManager(manager);
            Employee updatedEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            throw new IllegalStateException("Employee/Manager Not Found/Assigning employee with a none manager employee");
        }
    }
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
         employeeService.delete(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id , @RequestBody Employee updatedEmployee){
    return employeeService.updateEmployee(id,updatedEmployee);
    }



    //create a manager
    @PostMapping("/add-manager")
    public ResponseEntity<Employee> createManager(@RequestBody Employee manager) {

        manager.setIsManager(true);
        Employee savedManager = employeeService.saveEmployee(manager);
        return new ResponseEntity<>(savedManager, HttpStatus.CREATED);
    }

    //viewing all managers
    @GetMapping("/view-managers")
    public ResponseEntity<List<EmployeeDTO>> getAllManagers() {
        List<EmployeeDTO> managers = employeeService.viewAllManagers();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }
    @GetMapping("/manager-employees/{managerId}")
    public List<EmployeeDTO> getEmployeesManagers(@PathVariable Long managerId){

    return employeeService.getEmployeesByManagerId(managerId);
    }

    @GetMapping("/type/{emp}")
    public Optional<Employee> getEmpWithType(@RequestBody @PathVariable EmployeeType emp){
         return employeeService.findEmployeeByType(emp);
    }


        //promote employe to be a manager
        @PatchMapping("/promote/{employeeId}")
        public ResponseEntity<Employee> promoteToManager(@PathVariable Long employeeId) {
            Employee promotedEmployee = employeeService.promoteToManager(employeeId);
            return new ResponseEntity<>(promotedEmployee, HttpStatus.OK);
        }
        @GetMapping("/all")

        public ResponseEntity<List<EmployeeDTO>> all() {
            List<EmployeeDTO> managersWithEMp = employeeService.all();
            return new ResponseEntity<>(managersWithEMp, HttpStatus.OK);
        }

}



