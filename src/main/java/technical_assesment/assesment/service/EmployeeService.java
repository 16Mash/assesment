package technical_assesment.assesment.service;

import technical_assesment.assesment.bean.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee create(Employee employee,Long managerId);
    public void delete(Long id);
    public Employee updateEmployee(Long id, Employee updatedEmployee);
    public List<Employee> read ();
    public Optional<Employee> viewEmployee(Long id);

    public Employee createManager(Employee manager);
    public List<Employee> managers ();
}
