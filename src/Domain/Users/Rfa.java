package Domain.Users;

import Domain.*;
import Domain.BudgetControl.BudgetControl;
import Domain.BudgetControl.BudgetReport;
import Domain.LeagueManagment.Calculation.CalculationPolicy;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Scheduling.SchedulingPolicy;
import Domain.LeagueManagment.Season;
import Domain.LeagueManagment.Team;
import Domain.Notifications.Notification;
import Domain.Notifications.NotificationsUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.*;
import java.util.*;

public class Rfa extends Fan implements Observer , NotificationsUser {

    private BudgetControl budgetControl;//TODO: delete?!?!?!
    private static final Logger LOG = LogManager.getLogger();
    public static LinkedList<Team> teamRequests;
    public static HashSet<Notification> notifications;

    public Rfa(Fan fan, MainSystem ms) {
        super(ms, fan.getName(), fan.getPhoneNumber(), fan.getEmail(), fan.getUserName(), fan.getPassword(), fan.getDateOfBirth());
        this.system.removeUser(fan);
        this.teamRequests= new LinkedList<>();
        this.notifications=new HashSet<>();
        //TODO add permissions
    }

    public Rfa(MainSystem ms, String name, String phoneNumber, String email, String userName, String password, Date date) {
        super(ms,name,phoneNumber,email,userName,password,date);
        this.teamRequests= new LinkedList<>();
        notifications=new HashSet<>();
        //TODO add permissions
        //this.permissions.add();
    }

    //<editor-fold desc="rolls to budget control on teams">

    // TODO: 21/04/2020 TEST - V
    /**
     * This role - search by year and check if income for each month of the year bigger than 100
     * gets year to search on, It this year is the current year, look up to the current month excluding
     * @return HashSet<Team> of teams that do not fulfill the Rfa's role
     */
    public HashSet<Team> firstRoleForBudget(int year){

        HashSet<Team> budgetExceptionTeams = new HashSet<>();
        HashSet<Team> activeTeams = this.getSystem().getActiveTeams();
        boolean currentYear = false;

        //check if given year is the current year
        if(YearMonth.now().getYear() == year){
            currentYear = true;
        }

        int numOfMonthToFill = 0; // represent the number of months that we need to check
        if(currentYear){
            numOfMonthToFill = YearMonth.now().getMonthValue()-1;
        }
        else{
            numOfMonthToFill = 12;
        }

        //for each active team
        for (Team t: activeTeams) {

           HashMap<Integer,Integer> MonthAndAmountPerIncome = new HashMap<Integer, Integer>();//key=month, value=amount of income
            /**create number of nodes like number of months**/

            for(int i=1; i<=numOfMonthToFill; i++){
                MonthAndAmountPerIncome.put(i,0);
            }

            LinkedList<BudgetReport> incomeAndExpansePerTeam = t.getBudgetControl().getIncomeAndExpenses();
            //for each report - if it's income - add it to the relevant month
            for (BudgetReport r: incomeAndExpansePerTeam) {

                if (r.getAmount() > 0) {
                    LocalDate date = r.getNow().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    //always check if the year of report equal to the year that given
                    if(date.getYear()==year) {
                        int index = date.getMonthValue();//convert month to quarter
                        //if year is current year, check that report.month not in future months
                        //else, non filter
                        if(currentYear){
                            if( index < YearMonth.now().getMonthValue()) {
                                int currentAmount = MonthAndAmountPerIncome.get(index);
                                int newValue = (int) (currentAmount + r.getAmount());
                                MonthAndAmountPerIncome.put(index, newValue);
                            }
                        }
                        else{
                            int currentAmount = MonthAndAmountPerIncome.get(index);
                            int newValue = (int) (currentAmount + r.getAmount());
                            MonthAndAmountPerIncome.put(index, newValue);
                        }
                    }
                }
            }

            //for each month, check if income bigger than 100
            for (Map.Entry monthAndAmount: MonthAndAmountPerIncome.entrySet()) {
                if((int)monthAndAmount.getValue() < 100){
                    budgetExceptionTeams.add(t);
                    break;
                }
            }

        }

        return budgetExceptionTeams;
    }

    // TODO: 21/04/2020 TEST - V
    /**
     * This role - search by year and check if income for each quarterly bigger than 1000
     * @return HashSet<Team> of teams that do not fulfill the Rfa's role
     */
    public HashSet<Team> secondRoleForBudget(int year){

        HashSet<Team> budgetExceptionTeams = new HashSet<>();
        HashSet<Team> activeTeams = this.getSystem().getActiveTeams();
        boolean currentYear = false;

        //check if given year is the current year
        if(YearMonth.now().getYear() == year){
            currentYear = true;
        }

        int numOfqurterToFill = 0; // represent the number of months that we need to check
        if(currentYear){
            numOfqurterToFill = ((YearMonth.now().getMonthValue()-1) / 3) + 1;
        }
        else{
            numOfqurterToFill = 4;
        }

        //for each active team
        for (Team t: activeTeams) {

            HashMap<Integer,Integer> quarterAndAmountPerIncome = new HashMap<Integer, Integer>();//key=quarter, value=amount of income

            /**create number of nodes -> represents number od quarters**/
            for(int i=1; i<=numOfqurterToFill; i++){
                quarterAndAmountPerIncome.put(i,0);
            }

            LinkedList<BudgetReport> incomeAndExpansePerTeam = t.getBudgetControl().getIncomeAndExpenses();
            //for each report - if it's income - add it to the relevant quarter
            for (BudgetReport r: incomeAndExpansePerTeam) {

                if (r.getAmount() > 0) {
                    LocalDate date = r.getNow().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    //always check if the year of report equal to the year that given
                    if(date.getYear()==year) {
                        int index = ((date.getMonthValue()-1)/3)+1;//convert month to quarter
                        //if year is current year, check that report.month not in future quarters
                        //else, non filter
                        if(currentYear){
                            if( index <= numOfqurterToFill && date.getMonthValue()<=YearMonth.now().getMonthValue()) {
                                int currentAmount = quarterAndAmountPerIncome.get(index);
                                int newValue = (int) (currentAmount + r.getAmount());
                                quarterAndAmountPerIncome.put(index, newValue);
                            }
                        }
                        else{
                            int currentAmount = quarterAndAmountPerIncome.get(index);
                            int newValue = (int) (currentAmount + r.getAmount());
                            quarterAndAmountPerIncome.put(index, newValue);
                        }
                    }
                }
            }

            //for each quarter, check if income bigger than 1000
            for (Map.Entry monthAndAmount: quarterAndAmountPerIncome.entrySet()) {
                if((int)monthAndAmount.getValue() < 1000){
                    budgetExceptionTeams.add(t);
                    break;
                }
            }
        }

        return budgetExceptionTeams;
    }

    //</editor-fold>



    /**
     * This function gets name of league and create new league, if there isn't already
     * league with the same name in the system
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
            throw new Exception("Invalid parameters");
        }

    }

    /**
     * This function gets all the parameters
     * in order to create new referee
     * and add him to the list of all the users
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
        if( qualification == null){
            throw new NullPointerException();
        }
        checkValidDetails(name, userName, password, phoneNumber,email);
        Referee newRef = new Referee(system, name, phoneNumber, email, userName, password, qualification,birthDate);
        LOG.info(String.format("%s - %s", this.getUserName(), "Add referee by Domain.Users.Rfa"));
    }

    /**
     * This function gets Referee and remove him from the list of all the users
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
            LOG.info(String.format("%s - %s", this.getUserName(), "Remove referee by Rfa"));
        }else{
            throw new Exception("Referee is null");
        }
    }

    /**
     * Initializing Season To League by giving the policies, year and teams that will be in the league in this season
     * @param schedule
     * @param calculate
     * @param year
     * @param l
     * @param teams
     * @CodeBy Yarden
     */
    //TODO test - V
    public void defineSeasonToLeague(SchedulingPolicy schedule, CalculationPolicy calculate, int year, League l, HashSet<Team> teams) throws Exception {
        if(schedule==null || calculate==null || year < Year.now().getValue()){
            throw new Exception("Invalid details");
        }

        Season newSeason = new Season(this.system,schedule,calculate,year);
        this.system.setCurrSeason(newSeason);
        newSeason.addLeagueWithTeams(l,teams);
        LOG.info(String.format("%s - %s", this.getUserName(), "define season to "+l.getName()+ "by Rfa"));
    }

    /**Yarden**/
    //TODO test
    public void startSchedulingPolicy(Season season, HashSet<Referee> referees, Referee mainRef) throws Exception {
        season.getSchedulingPolicy().assign(season.getTeamsInCurrentSeasonLeagues(), referees, mainRef);
    }

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
                this.notifications.add(new Notification(o,arg,false));
            }
        }
    }

    /**Or**/
    //TODO test- V
    public void answerRequest(Team team, boolean desicion) throws Exception {
        if( !teamRequests.contains(team)){
            throw new Exception("team not in request list");
        }
        team.sendDecision(desicion);
        Notification cur=null;
        /**get the current notification**/
        for(Notification n: notifications){
            if(n.getSender()==team){
                cur=n;
                break;
            }
        }
        if(cur!=null) {
            cur.setRead(true);
        }
        teamRequests.remove(team);

    }


    /**
     * mark notification as readen
     * @param not-unread notification to mark as read
     * @codeBy Eden
     */
    public void MarkAsReadNotification(Notification not){
        not.setRead(true);
    }

    /**
     * get all notifications read and unread
     * @return
     */
    public HashSet<Notification> getNotificationsList() {
        return notifications;
    }

    /***
     * @return only the unread notifications . if not have return null - notify when user connect
     * @codeBy Eden
     */
    @Override
    public HashSet<Notification> genUnReadNotifications(){
        HashSet<Notification> unRead=new HashSet<>();
        for(Notification n: notifications){
            if(n.isRead()==false){
                unRead.add(n);
            }
        }
        return unRead;
    }
}
