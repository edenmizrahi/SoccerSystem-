import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Rfa extends Fan implements Observer{

    private BudgetControl budgetControl;//TODO: delete?!?!?!
    private static final Logger LOG = LogManager.getLogger();
    private static LinkedList<Team> teamRequests;

    public Rfa(Fan fan, MainSystem ms) {
        super(ms, fan.getName(), fan.getPhoneNumber(), fan.getEmail(), fan.getUserName(), fan.getPassword(), fan.getDateOfBirth());
        this.teamRequests= new LinkedList<>();
        //TODO add permissions

    }

    public Rfa(MainSystem ms, String name, String phoneNumber, String email, String userName, String password, Date date) {
        super(ms,name,phoneNumber,email,userName,password,date);
        this.teamRequests= new LinkedList<>();
        //TODO add permissions
        //this.permissions.add();
    }

    /**Yarden**/
    public void createNewLeague(String nameOfLeague, MainSystem ms) throws Exception {
        League newLeague = new League(nameOfLeague, ms);
        LOG.info(String.format("%s - %s", this.getUserName(), "Create new league"));
    }

    /**Yarden**/
    public void addReferee(String name, String phoneNumber, String email, String userName, String password, String qualification,Date birthDate) throws Exception {
        if (checkValidDetails(userName, password, phoneNumber,email) && name!=null && qualification!=null) {
            Referee newRef = new Referee(system, name, phoneNumber, email, userName, password, qualification,birthDate);
            system.addUser(newRef);
            LOG.info(String.format("%s - %s", this.getUserName(), "Add referee by Rfa"));
        }
        else {
            throw new Exception("Invalid details - You can not add this referee");
        }
    }

    /**Yarden**/
    public void deleteReferee(Referee ref) throws Exception {
        //check the all matches that the referee is refereeing

        if(ref!=null) {
            for (Match m : ref.getMatches()) {
                //can't delete, match still didn't take place
                if (m.getStartDate().after(new Date(System.currentTimeMillis()))) {
                    throw new Exception("You can not delete this referee");
                }
            }

            system.removeUser(ref);
            LOG.info(String.format("%s - %s", this.getUserName(), "Remove referee by Rfa"));
        }
    }

    /**Yarden**/
    public void defineSeasonToLeague(Policy p, int year, League l, HashSet<Team> teams){
        Season newSeason = new Season(this.system,p,year);
        this.system.setCurrSeason(newSeason);
        newSeason.addLeagueWithTeams(l,teams);
        LOG.info(String.format("%s - %s", this.getUserName(), "define season to "+l.getName()+ "by Rfa"));
    }

    /**Yarden**/
    public void addPolicy(){


    }

    public BudgetControl getBudgetControl() { return budgetControl; }

    public void setBudgetControl(BudgetControl budgetControl) { this.budgetControl = budgetControl; }

    public static LinkedList<Team> getTeamRequests() {
        return teamRequests;
    }

    public static void setTeamRequests(LinkedList<Team> teamRequests) {
        Rfa.teamRequests = teamRequests;
    }

    /**Or**/
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Team && arg instanceof Team){
            if(arg.equals("request to open new team")){//open new team
                this.teamRequests.add((Team)o);
            }
        }
    }

    /**Or**/
    public void answerRequest(Team team, boolean desicion) throws Exception {
        if( !teamRequests.contains(team)){
            throw new Exception("team not in request list");
        }
        team.notifyObservers(desicion);
        teamRequests.remove(team);


    }
}
