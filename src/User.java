import java.util.HashSet;

public class User {
    enum Permission {
        DeleteUser,
        ShowLog,
        DeleteUserFromTeam,
        ManageComplaint,
        BuildRecSystem,
        EditUserPermission
    }
    MainSystem system;
    HashSet<Permission> permissions;

    User(MainSystem ms){
        system = ms;
        permissions = new HashSet<>();
        system.addUser(this);
    }
}
