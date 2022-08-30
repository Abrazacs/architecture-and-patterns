package structuralpatterns.composite;

import java.util.List;

public class Organization {

    private List<Employee> employees;

    public Organization(List<Employee> employees){
        this.employees = employees;
    }

    public Double getNetSalaries(){
        Double salary = 0.0;
        for (Employee employee: employees) {
            salary = salary + employee.getSalary();
        }
        return salary;
    }
}
