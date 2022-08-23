package structuralpatterns.facade;

public class ComputerFacade {

    private Computer computer;

    public ComputerFacade (Computer computer){
        this.computer = computer;
    }

    public String turnOn(){
        return computer.getElectricShock() + "\n"
                + computer.makeSound() + "\n"
                + computer.showLoadingScreen() +"\n"
                +computer.bam();
    }

    public String turnOff(){
        return computer.closeEverything() + "\n"
                + computer.pullCurrent() + "\n"
                + computer.sooth();
    }
}
