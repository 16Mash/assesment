package technical_assesment.assesment.bean;

import jakarta.persistence.*;
import technical_assesment.assesment.EmployeeType;

import java.time.LocalDate;

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
    private String managerName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;


    private EmployeeType employeeType;

    public Employee(String name, String surname, LocalDate birth, Double salary, String position,
                    boolean isManager, String managerName, Employee manager, EmployeeType employeeType) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.salary = salary;
        this.position = position;
        this.isManager = isManager;
        this.managerName = managerName;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                ", isManager=" + isManager +
                ", manager=" + manager +
                ", employeeType=" + employeeType +
                '}';
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
