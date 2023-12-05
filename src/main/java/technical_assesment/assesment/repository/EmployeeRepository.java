package technical_assesment.assesment.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technical_assesment.assesment.bean.Employee;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByIsManagerTrue();

    Optional<Employee> findByIdAndIsManagerTrue(Long employeeId);

}
