package structuralpatterns.facade;

public class FacadeTests {
    public static void main(String[] args) {
        Computer computer = new Computer();
        ComputerFacade computerFacade = new ComputerFacade(computer);

        System.out.println(computerFacade.turnOn());
        System.out.println("\n");
        System.out.println(computerFacade.turnOff());
    }
}
