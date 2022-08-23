package structuralpatterns.adapter;

public class AfricanLion implements Lion{

    @Override
    public String roar() {
        return "I'm roaring as african lion";
    }
}
