package technical_assesment.assesment.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import technical_assesment.assesment.EmployeeType;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDate birth;
    private Double salary;
    private String position;
    private boolean isManager;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id" , referencedColumnName = "id")


    private Employee manager;
    private EmployeeType employeeType;

    public Employee(String name, String surname, LocalDate birth, Double salary, String position,
                    boolean isManager, Employee manager, EmployeeType employeeType) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.salary = salary;
        this.position = position;
        this.isManager = isManager;
        this.manager = manager;
        this.employeeType = employeeType;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate  getBirth() {
        return birth;
    }

    public void setBirth(LocalDate  birth) {
        this.birth = birth;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {

        this.manager = manager;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
}
