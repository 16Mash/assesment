package technical_assesment.assesment.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technical_assesment.assesment.EmployeeType;
import technical_assesment.assesment.bean.Employee;
import technical_assesment.assesment.bean.EmployeeDTO;
import technical_assesment.assesment.exception.EmployeeNotFoundException;
import technical_assesment.assesment.repository.EmployeeRepository;
import technical_assesment.assesment.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO mapToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(employee.getName());
        dto.setSurname(employee.getSurname());
        dto.setBirth(employee.getBirth());
        dto.setEmployeeType(employee.getEmployeeType());
        dto.setId(employee.getId());
        dto.setManager(employee.isManager());
        dto.setPosition(employee.getPosition());
        dto.setSalary(employee.getSalary());
        Employee man = employee.getManager();
        if(man!=null){
            dto.setManagerId(man.getId());
            Employee manager = employeeRepository.findById(employee.getManager().getId()).orElse(null);
            if (manager != null) {
                dto.setManagerName(manager.getName());

            }
        }
   return dto;
    }
    @Override
    public Employee saveEmployee(Employee employee) {


        switch (employee.getEmployeeType()) {
            case EMPLOYEE:

                employee.setEmployeeType(EmployeeType.EMPLOYEE);
                break;

            case CEO:

                employee.setEmployeeType(EmployeeType.CEO);
                employee.setIsManager(false);
                break;

            case MANAGER:
                employee.setEmployeeType(EmployeeType.MANAGER);
                employee.setIsManager(true);
                break;

            default:
                break;
        }
        return employeeRepository.save(employee);
    }


    @Override
    public void delete(Long id) {
         employeeRepository.deleteById(id);
    }

    @Override
    @Transactional

    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()){

                Employee employee = existingEmployee.get();
                employee.setName(updatedEmployee.getName());
                employee.setPosition(updatedEmployee.getPosition());
                employee.setSurname(updatedEmployee.getSurname());
                employee.setSalary(updatedEmployee.getSalary());
                employee.setManager(updatedEmployee.getManager());

                return employeeRepository.save(employee);

        }else {
             throw new EntityNotFoundException("Employee with ID " + id + " not found");

        }
    }


    @Override
    public List<EmployeeDTO> read() {

        List<EmployeeDTO> emps = employeeRepository.findAll().stream().map(this::mapToDTO).toList();


        return emps;
    }

    @Override
    public Employee viewEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee createManager(Employee manager) {
        manager.setIsManager(true);
        return employeeRepository.save(manager);
    }

    @Override
    public List<Employee> managers() {
        return employeeRepository.findByIsManagerTrue();
    }

    @Override
    public List<Employee> viewAllManagers() {
        return employeeRepository.findByIsManagerTrue();
    }

    @Override
    public Employee promoteToManager(Long id) {Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setIsManager(true); // Set the employee as a manager
            return employeeRepository.save(employee);
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
    }


}
