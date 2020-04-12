import java.util.HashSet;

public class User {
    private MainSystem system;
    private HashSet<Permission> permissions;

    User(MainSystem ms){
        system = ms;
        permissions = new HashSet<>();
        system.addUser(this);
    }

    public void addPermission(Permission per){
        permissions.add(per);
    }
    public void addPermissions(HashSet<Permission> pers){
        permissions.addAll(pers);
    }
    //<editor-fold desc="getters and setters">
    public MainSystem getSystem() {
        return system;
    }

    public void setSystem(MainSystem system) {
        this.system = system;
    }

    public HashSet<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(HashSet<Permission> permissions) {
        this.permissions = permissions;
    }
    //</editor-fold>
}
