package structuralpatterns.decorator;

public class CoffeeWithMilk implements Coffee{

    private Coffee coffee;

    public CoffeeWithMilk (Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 2.0;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription()+" with milk";
    }
}
