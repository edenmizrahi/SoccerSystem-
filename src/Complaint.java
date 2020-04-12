import java.util.HashSet;
import java.util.List;
import java.util.Observable;

public class Complaint extends Observable {
int id;
/****WE NEED?*******/
Fan fan;
/**********/
MainSystem system;
String  content;
String answer;
List<SystemManager> systemManagers;

/**Eden*/
    public Complaint(Fan fan, MainSystem system) {
        this.fan = fan;
        this.system = system;
        answer=null;
        systemManagers= this.system.getSystemManagers();
        for (SystemManager s:systemManagers) {
            s.addComplaint(this);
        }
    }

    /**Eden*/
    public void send(){
        setChanged();
        notifyAll();
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

    public void setContent(String content) {
        this.content = content;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }





}

