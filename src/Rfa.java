import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashSet;

public class Rfa extends Subscription {

    private BudgetControl budgetControl;//1
    private static final Logger LOG = LogManager.getLogger();

    public Rfa(Subscription sub, MainSystem ms) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.budgetControl = new BudgetControl();
        //TODO add permissions

    }

    public Rfa(MainSystem ms, String name, String phoneNumber, String email, String userName, String password) {
        super(ms,name,phoneNumber,email,userName,password);
        this.budgetControl = new BudgetControl();
        //TODO add permissions
        //this.permissions.add();
    }

    /**Yarden**/
    public void createNewLeague(String nameOfLeague, MainSystem ms) throws Exception {
        League newLeague = new League(nameOfLeague, ms);
        LOG.info(String.format("%s - %s", this.getUserName(), "Create new league"));
    }

    /**Yarden**/
    public void addReferee(String name, String phoneNumber, String email, String userName, String password, String qualification) throws Exception {
        if (checkValidDetails(userName, password, phoneNumber,email) && name!=null && qualification!=null) {
            Referee newRef = new Referee(system, name, phoneNumber, email, userName, password, qualification);
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

}
