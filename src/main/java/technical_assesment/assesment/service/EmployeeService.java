package technical_assesment.assesment.service;

import technical_assesment.assesment.EmployeeType;
import technical_assesment.assesment.bean.Employee;
import technical_assesment.assesment.bean.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
   public EmployeeDTO mapToDTO(Employee employee);
    public Employee saveEmployee(Employee employee);
    public void delete(Long id);
    public Employee updateEmployee(Long id, Employee updatedEmployee);
    public List<EmployeeDTO> read ();
    public Employee viewEmployee(Long id);
    public EmployeeDTO employee (Long id);

    public Employee createManager(Employee manager);

    public List<EmployeeDTO> viewAllManagers();
    public Employee promoteToManager(Long id);
    List<EmployeeDTO>getEmployeesByManagerId(Long managerId);
    List<EmployeeDTO> all();
    Optional<Employee> findEmployeeByType(EmployeeType emp);
}
