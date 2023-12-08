package technical_assesment.assesment.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technical_assesment.assesment.EmployeeType;
import technical_assesment.assesment.bean.Employee;
import technical_assesment.assesment.bean.EmployeeDTO;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByIsManagerTrue();

    Optional<Employee> findByIdAndIsManagerTrue(Long employeeId);
    Optional<Employee> findByEmployeeTypeTrue();
    Optional<Employee> findByIdAndEmployeeTypeTrue(Long id);


    Optional<Employee>findByEmployeeType(EmployeeType empType);
    List<Employee> findEmployeesByManagerId(Long managerId);
}
