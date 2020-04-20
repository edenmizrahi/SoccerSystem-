package Domain.Users;

import Domain.*;
import Domain.BudgetControl.BudgetControl;
import Domain.LeagueManagment.Calculation.CalculationPolicy;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Scheduling.SchedulingPolicy;
import Domain.LeagueManagment.Season;
import Domain.LeagueManagment.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Rfa extends Fan implements Observer{

    private BudgetControl budgetControl;//TODO: delete?!?!?!
    private static final Logger LOG = LogManager.getLogger();
    public static LinkedList<Team> teamRequests;

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

    /**
     * This function create new league
     * @param nameOfLeague
     * @param ms
     * @throws Exception
     */
    //TODO test
    public void createNewLeague(String nameOfLeague, MainSystem ms) throws Exception {
        if (nameOfLeague != null && ms != null) {
            League newLeague = new League(nameOfLeague, ms);
            LOG.info(String.format("%s - %s", this.getUserName(), "Create new league"));
        }
            else{
            throw new Exception("Please insert valid details in order to create the new Domain.LeagueManagment.League properly");
        }

    }

    /**
     * This function gets all the parameters in order to create new referee and add him to the list of all the users
     * @param name
     * @param phoneNumber
     * @param email
     * @param userName
     * @param password
     * @param qualification
     * @param birthDate
     * @throws Exception
     * @CodeBy Yarden
     */
    //TODO test - V
    public void addReferee(String name, String phoneNumber, String email, String userName, String password, String qualification,Date birthDate) throws Exception {
        if (checkValidDetails(userName, password, phoneNumber,email) && name!=null && qualification!=null) {
            Referee newRef = new Referee(system, name, phoneNumber, email, userName, password, qualification,birthDate);
            LOG.info(String.format("%s - %s", this.getUserName(), "Add referee by Domain.Users.Rfa"));
        }
        else {
            throw new Exception("Invalid details - You can not add this referee");
        }
    }

    /**
     * This function gets Domain.Users.Referee and remove him from the list of all the users
     * cannot remove if he has a match in the future
     * @param ref
     * @throws Exception
     * @CodeBy Yarden
     */
    //TODO test - V
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
            LOG.info(String.format("%s - %s", this.getUserName(), "Remove referee by Domain.Users.Rfa"));
        }else{
            throw new Exception("Domain.Users.Referee is null");
        }
    }

    /**
     * Initializing Domain.LeagueManagment.Season To Domain.LeagueManagment.League
     * @param schedule
     * @param calculate
     * @param year
     * @param l
     * @param teams
     * @CodeBy Yarden
     */
    //TODO test
    public void defineSeasonToLeague(SchedulingPolicy schedule, CalculationPolicy calculate, int year, League l, HashSet<Team> teams){
        Season newSeason = new Season(this.system,schedule,calculate,year);
        this.system.setCurrSeason(newSeason);
        newSeason.addLeagueWithTeams(l,teams);
        LOG.info(String.format("%s - %s", this.getUserName(), "define season to "+l.getName()+ "by Domain.Users.Rfa"));
    }

    /**Yarden**/
    //TODO test
    public void startSchedulingPolicy(Season season, HashSet<Referee> referees, Referee mainRef) throws Exception {
        season.getSchedulingPolicy().assign(season.getTeamsInCurrentSeasonLeagues(), referees, mainRef);
    }

    // TODO: 18/04/2020 sorted the hashSet of TeamsInCurrentSeasonleagues ?
    /**Yarden**/
    //TODO test
    public void startCalculationPolicy(Season season){
        season.getCalculationPolicy().calculate(season.getTeamsInCurrentSeasonLeagues());

    }


    public BudgetControl getBudgetControl() { return budgetControl; }

    public void setBudgetControl(BudgetControl budgetControl) { this.budgetControl = budgetControl; }

    public static LinkedList<Team> getTeamRequests() { return teamRequests; }

    public static void setTeamRequests(LinkedList<Team> teamRequests) { Rfa.teamRequests = teamRequests; }

    /**Or**/
    @Override
    //TODO test
    public void update(Observable o, Object arg) {
        if(o instanceof Team){
            if(arg.equals("request to open new team")){//open new team
                this.teamRequests.add((Team)o);
            }
        }
    }

    /**Or**/
    //TODO test- V
    public void answerRequest(Team team, boolean desicion) throws Exception {
        if( !teamRequests.contains(team)){
            throw new Exception("team not in request list");
        }
        team.sendDesicion(desicion);
        teamRequests.remove(team);
    }
}
