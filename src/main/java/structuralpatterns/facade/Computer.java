package structuralpatterns.facade;

public class Computer {

    public String getElectricShock(){
        return "Ouch!";
    }

    public String makeSound(){
        return "Beep-beep";
    }

    public String showLoadingScreen(){
        return "Loading ...";
    }

    public String bam(){
        return "Ready to be used!";
    }

    public String closeEverything(){
        return "Bup bup bup buzzzz!";
    }

    public String sooth(){
        return "Zzzzz";
    }

    public String pullCurrent(){
        return "Haaah!";
    }
}

