package structuralpatterns.proxy;

public class LabDoor implements Door{



    @Override
    public String open() {
        return "The lab door is opening";
    }

    @Override
    public String close() {
        return "The lab door is closing";
    }
}
