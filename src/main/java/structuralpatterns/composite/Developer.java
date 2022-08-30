package structuralpatterns.composite;

public class Developer implements Employee{

    private String name;
    private Double salary;

    public Developer(String name, Double salary){
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getSalary() {
        return salary;
    }

    @Override
    public Double setSalary(Double salary) {
        return this.salary = salary;
    }
}
