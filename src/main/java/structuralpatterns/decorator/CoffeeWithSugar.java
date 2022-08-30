package structuralpatterns.decorator;

public class CoffeeWithSugar implements Coffee{

    private Coffee coffee;

    public CoffeeWithSugar(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost()+0.5;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription()+" with sugar";
    }
}
