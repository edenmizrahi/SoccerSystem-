import java.util.HashSet;

public class User {
    MainSystem system;
    HashSet<Permission> permissions;

    User(){
        system = new MainSystem();
        permissions = new HashSet<>();
    }

}
