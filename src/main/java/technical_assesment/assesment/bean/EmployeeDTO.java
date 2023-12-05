package technical_assesment.assesment.bean;

import technical_assesment.assesment.EmployeeType;

import java.time.LocalDate;

public class EmployeeDTO {

        private Long id;
        private String name;
        private String surname;
        private LocalDate birth;
        private Double salary;
        private String position;
        private boolean isManager;
        private Long managerId;
        private String managerName;

        private EmployeeType employeeType;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, String surname, LocalDate birth, Double salary,
                       String position, boolean isManager, Long managerId, String managerName, EmployeeType employeeType) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.birth = birth;
            this.salary = salary;
            this.position = position;
            this.isManager = isManager;
            this.managerId = managerId;
        this.managerName = managerName;
        this.employeeType = employeeType;
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

        public LocalDate getBirth() {
            return birth;
        }

        public void setBirth(LocalDate birth) {
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

        public boolean isManager() {
            return isManager;
        }

        public void setManager(boolean manager) {
            isManager = manager;
        }

        public Long getManagerId() {
            return managerId;
        }

        public void setManagerId(Long managerId) {
            this.managerId = managerId;
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


