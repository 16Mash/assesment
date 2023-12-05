package technical_assesment.assesment.service;

import technical_assesment.assesment.bean.Employee;
import technical_assesment.assesment.bean.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
   public EmployeeDTO mapToDTO(Employee employee);
    public Employee saveEmployee(Employee employee);
    public void delete(Long id);
    public Employee updateEmployee(Long id, Employee updatedEmployee);
    public List<Employee> read ();
    public Employee viewEmployee(Long id);

    public Employee createManager(Employee manager);
    public List<Employee> managers ();

    public List<Employee> viewAllManagers();
    public Employee promoteToManager(Long id);
}
