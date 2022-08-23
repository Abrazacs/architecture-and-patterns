package structuralpatterns.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        Coffee coffeeWithMilk = new CoffeeWithMilk(simpleCoffee);
        Coffee coffeeWithMilkAndSugar = new CoffeeWithSugar(coffeeWithMilk);

        System.out.println(simpleCoffee.getDescription() + ". Price is:"+simpleCoffee.getCost());
        System.out.println(coffeeWithMilk.getDescription() + ". Price is:"+coffeeWithMilk.getCost());
        System.out.println(coffeeWithMilkAndSugar.getDescription() + ". Price is:"+coffeeWithMilkAndSugar.getCost());
    }
}
