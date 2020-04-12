import java.util.HashSet;

public class User {
    MainSystem system;
    HashSet<Permission> permissions;

    User(MainSystem ms){
        system = ms;
        permissions = new HashSet<>();
        system.addUser(this);
    }
}
