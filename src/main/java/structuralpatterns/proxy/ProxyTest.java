package structuralpatterns.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        SecuredDoor securedDoor = new SecuredDoor(new LabDoor(),"Sim-salabim");

        System.out.println(securedDoor.open("password"));

        System.out.println(securedDoor.open("Sim-salabim"));

        System.out.println(securedDoor.close());
    }


}
