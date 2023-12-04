package technical_assesment.assesment.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technical_assesment.assesment.bean.Employee;
import technical_assesment.assesment.repository.EmployeeRepository;
import technical_assesment.assesment.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;




    @Override
    public Employee create(Employee employee ,Long managerId) {
        managerId = employee.getId();
        Employee manager = employeeRepository.findByIdAndIsManagerTrue(managerId).orElse(null);

        if (employee != null && manager != null) {
            employee.setManager(manager);
            return employeeRepository.save(employee);
        }else{
         return null;
        }

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
    public List<Employee> read() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> viewEmployee(Long id) {
        return employeeRepository.findById(id);
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


}
