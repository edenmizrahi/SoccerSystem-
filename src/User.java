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
    int[] permissions;
    User(){
        system = new MainSystem();
        permissions = new int[6];
    }
}
