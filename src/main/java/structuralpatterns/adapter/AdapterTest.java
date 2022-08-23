package structuralpatterns.adapter;

import javax.sound.midi.Soundbank;

public class AdapterTest {

    public static void main(String[] args) {
        Hunter hunter = new Hunter();
        Lion lion1 = new AsianLion();
        Lion lion2 = new AfricanLion();
        WildDog dog = new WildDog();
        WildDogAdapter dogAdapter = new WildDogAdapter(dog);

        System.out.println(hunter.hunt(lion1));
        System.out.println(hunter.hunt(lion2));
        System.out.println(hunter.hunt(dogAdapter));
    }
}
