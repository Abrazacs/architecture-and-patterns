package structuralpatterns.bridge;

public class BridgeTest {

    public static void main(String[] args) {
        Theme dark = new DarkTheme();
        Theme light = new LightTheme();

        WebPage aboutDark = new About(dark);
        WebPage aboutLight = new About(light);

        System.out.println(aboutDark.getContent());
        System.out.println(aboutLight.getContent());
    }
}
