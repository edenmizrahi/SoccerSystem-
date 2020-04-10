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
    User(){
        system = new MainSystem();
        permissions = new HashSet<>();
    }
}
