package structuralpatterns.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeTest {

    public static void main(String[] args) {
        Employee developer = new Developer("John", 5000.0);
        Employee designer = new Designer("Bob", 4500.25);
        List<Employee> employees = new ArrayList<>();
        employees.add(designer);
        employees.add(developer);

        Organization organization = new Organization(employees);
        System.out.println(organization.getNetSalaries());
    }
}
