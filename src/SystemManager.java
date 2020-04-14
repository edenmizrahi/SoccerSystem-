import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SystemManager extends Subscription implements Observer {

    private HashSet<Complaint> complaints;//*
    private static final Logger LOG = LogManager.getLogger();

    public SystemManager(Subscription sub, MainSystem ms) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.complaints = new HashSet<>();
        //TODO add permissions
        //this.permissions.add();
    }

    public SystemManager (MainSystem ms, String name, String phoneNumber, String email, String userName, String password) {
        super(ms,name,phoneNumber,email,userName,password);
        this.complaints = new HashSet<>();
        //TODO add permissions
        //this.permissions.add();
    }

    public void buildRecomandationSystem(){

    }

    //<editor-fold desc="getters and setters">

    public HashSet<Complaint> getComplaints() {
        return complaints;
    }


    /**Eden*/
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  Complaint){

            if(arg instanceof Complaint){
                /**if it's not an answer notify*/
                if(((Complaint)arg).getAnswer()==null){
                    // TODO: 11/04/2020 Notifies about new Complaint
                }

            }
        }
    }

    /**
     * create new SystemManager , connect to him all the complaints
     * @param user
     * @codeBy Eden
     */
    public void addSystemManager(Subscription user){
        SystemManager newSystemManager=new SystemManager(user,system);
        newSystemManager.complaints=this.complaints;
    }
    /**Eden*/
    public void answerToComplaint (Complaint com, String ans){
        if(complaints.contains(com)){
            com.setAnswer(ans);
            com.send();
        }
    }
    /**Eden*/
    public void addComplaint(Complaint complaint) {
        complaints.add(complaint);
        complaint.addObserver(this);
    }

    /**
     * Show details about the system events.
     * @return FileReader to the Log file
     * @throws FileNotFoundException
     * @codeby Eden
     */
    public FileReader showSystemInfo() throws FileNotFoundException {
        File file =new File("LOG.text");
        return new FileReader(file);
    }

    /**
     * remove user if it a valid system operation.
     * @param userToDelete
     * @return list of removed object
     * @throws Exception if the remove isnt valid
     * @codeBy Eden
     */
    public List<Object> removeUser(User userToDelete) throws Exception {
        List<Object> objectsDeleted=new LinkedList<>();

        /**delete with no other D**/
        if(userToDelete instanceof Rfa) {
            if(system.numOfRfa()==1){
                throw new Exception("Only 1 RFA left, you cannot delete it ");
            }
            objectsDeleted=deleteRfa(((Rfa)userToDelete));
        }
        if(userToDelete instanceof SystemManager) {
            if(userToDelete==this){
                throw  new Exception("You Cannot Delete Yourself!!!");
            }
            if(system.getSystemManagers().size()==1){
                throw new Exception("Only 1 System Manager left, you cannot delete it ");
            }
            objectsDeleted=deleteSystemManager(((SystemManager)userToDelete));
        }
        if(userToDelete instanceof Referee) {
            objectsDeleted=deleteReferee(((Referee)userToDelete));
        }
        if(userToDelete instanceof Coach) {
            objectsDeleted=deleteCoach(((Coach)userToDelete));
        }
        if(userToDelete instanceof Player) {
            if(((Player)userToDelete).getTeam().getPlayers().size()<=11){
                throw new Exception("You Cannot Delete Player From "+((Player)userToDelete).getTeam().getPlayers()+" Team , at team have to be at least 11 Players!");
            }
            objectsDeleted=deletePlayer(((Player)userToDelete));
        }

        if(userToDelete instanceof TeamOwner) {
            objectsDeleted=deleteTeamOwner(((TeamOwner)userToDelete));
        }
        if(userToDelete instanceof TeamManager) {
            objectsDeleted=deleteTeamManager(((TeamManager)userToDelete));
        }

        return objectsDeleted;
    }

    private List<Object> deleteTeamManager(TeamManager userToDelete) {
        return null;
    }

    private List<Object> deleteTeamOwner(TeamOwner userToDelete) {
        return null;
    }

    private List<Object> deletePlayer(Player userToDelete) {
       userToDelete.getTeam().removePlayer(userToDelete);
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;


    }

    private List<Object> deleteCoach(Coach userToDelete) {
        return null;

    }

    /**
     * delete Referee only if he hasn't any future match .
     * @param userToDelete
     * @return list with the referee object.
     * @throws Exception if referee has future matches.
     * @codeBy Eden
     */
    private List<Object> deleteReferee(Referee userToDelete) throws Exception {
        //check the all matches that the referee is refereeing
        for (Match m : userToDelete.getMatches()) {
            if (m.getDate().after(new Date(System.currentTimeMillis()))) {
                throw new Exception("You cannot remove referee , he has matches in this season");
            }
        }
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }

    private List<Object> deleteSystemManager(SystemManager userToDelete) {
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }

    private List<Object> deleteRfa(Rfa rfa){
        rfa.getBudgetControl().removeRfa(rfa);
        system.removeUser(rfa);
        List<Object> res=new LinkedList<>();
        res.add(rfa);
        return res;
    }

    //</editor-fold>
}

