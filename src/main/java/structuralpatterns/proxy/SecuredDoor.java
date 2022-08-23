package structuralpatterns.proxy;

public class SecuredDoor {

    private Door door;
    private String password;

    public SecuredDoor (Door door, String password){
        this.door = door;
        this.password = password;
    }

    public String open(String password){
        if(this.password == password) return  door.open();
        return "Authentication failed. Door can't be opened";
    }

    public String close(){
        return door.close();
    }
}
