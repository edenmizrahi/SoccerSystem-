public class Complaint {
    private int id;
    private Fan fan;
    private MainSystem system;

    public Complaint(Fan fan, MainSystem system) {
        this.fan = fan;
        this.system = system;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }

    public MainSystem getSystem() {
        return system;
    }

    public void setSystem(MainSystem system) {
        this.system = system;
    }

    /** should hold SystemManager? */
}

