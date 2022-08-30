package structuralpatterns.decorator;

public class SimpleCoffee implements Coffee{

    @Override
    public double getCost() {
        return 10.0;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}
