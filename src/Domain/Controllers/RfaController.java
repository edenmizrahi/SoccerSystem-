package Domain.Controllers;

import DataAccess.DaoSeasons;
import Domain.LeagueManagment.Calculation.CalculateOption1;
import Domain.LeagueManagment.Calculation.CalculationPolicy;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Scheduling.SchedualeOption1;
import Domain.LeagueManagment.Scheduling.SchedualeOption2;
import Domain.LeagueManagment.Scheduling.SchedulingPolicy;
import Domain.LeagueManagment.Season;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Notifications.Notification;
import Domain.Users.Referee;
import Domain.Users.Rfa;
import Domain.Users.SystemManager;
import org.reflections.Reflections;
import sun.awt.image.ImageWatched;

import java.util.*;

public class RfaController {
    /**
     * create new league
     * @param leagueName
     * @param user
     * @throws Exception
     * @codeBy Eden
     */
    public void createLeague(String leagueName , Rfa user) throws Exception {
        user.createNewLeague(leagueName,user.getSystem());
    }

//    /**
//     * show user his scheduling policies options
//     * @return  List of Scheduling Policies
//     *  @codeBy Eden
//     */
//    public List<String> watchSchedulingPolicies(){
//        List <String> list=new LinkedList<>();
//        list.add("ScheduleOption1");
//        list.add("ScheduleOption2");
//        return list;
//    }
//    /**
//     * show user his Calculation policies options
//     * @return  List of Calculation Policies
//     *  @codeBy Eden
//     */
//    public List<String> watchCalculationPolicies(){
//        List <String> list=new LinkedList<>();
//        list.add("CalculateOption1");
//        return list;
//    }


    public CalculationPolicy calculationPolicyByString(String name){
        CalculationPolicy c = null;
        if(name.equals("CalculateOption1")){
            c = new CalculateOption1();
        }

        return c;
    }

    public SchedulingPolicy schedulingPolicyByString(String name){
        SchedulingPolicy s = null;
        if(name.equals("SchedualeOption1")){
            s = new SchedualeOption1();
        }
        else{
            if(name.equals("SchedualeOption2")){
                s = new SchedualeOption2();
            }
        }

        return s;
    }

    /**
     *
     * @return linkedList of all subClasses that implement SchedulingPolicy interface
     */
    public String getAllschedulingString() {
        //LinkedList<String> schedulingList = new LinkedList<>();
        String schedulingList = new String();
        Reflections reflections = new Reflections("Domain");
        Set<Class<? extends SchedulingPolicy>> classes = reflections.getSubTypesOf(SchedulingPolicy.class);

        for (Class class1 : classes) {
            //schedulingList.add(class1.getSimpleName());
            schedulingList += class1.getSimpleName() + ";";
        }

        return schedulingList;
    }

    /**
     *
     * @return linkedList of all subClasses that implement Calculation interface
     */
    public String getAllCalculationPoliciesString() {
        //LinkedList<String> calculationList = new LinkedList<>();
        String calculationList = new String();
        Reflections reflections = new Reflections("Domain");
        Set<Class<? extends CalculationPolicy>> classes = reflections.getSubTypesOf(CalculationPolicy.class);

        for (Class class1 : classes) {
            //calculationList.add(class1.getSimpleName());
            calculationList += class1.getSimpleName() + ";";
        }

        return calculationList;
    }


    /**
     * Define calculate and schedule policies to specific season
     * if season exist, just update the policies, else create new season with those policies
     * @param year
     * @param calc
     * @param sched
     */
    public void DefinePoliciesToSeason(String year, String calc, String sched, String rfaUserName){
        DaoSeasons daoSeasons = new DaoSeasons();
        LinkedList<Season> allSeasons = MainSystem.getInstance().getSeasons();
        int yearOfSeason = Integer.parseInt(year);
        boolean seasonExist = false;
        for (Season s: allSeasons) {
            if(s.getYear()==yearOfSeason){
                seasonExist = true;
                s.setCalculationPolicy(this.calculationPolicyByString(calc) , rfaUserName);
                s.setSchedulingPolicy(this.schedulingPolicyByString(sched), rfaUserName);

                //update DB - table DaoSeason
                List<String> recordInSeasonTable = new LinkedList<>();
                recordInSeasonTable.add(0,year);
                recordInSeasonTable.add(1,sched);
                recordInSeasonTable.add(2,calc);
                if(MainSystem.getInstance().getCurrSeason().getYear() == s.getYear()){
                    recordInSeasonTable.add(3,"true");
                }
                else{
                    recordInSeasonTable.add(3,"false");
                }

                daoSeasons.update(String.valueOf(s.getYear()), recordInSeasonTable);
            }
        }

        if(!seasonExist){
            Season newSeason = new Season(MainSystem.getInstance(),this.schedulingPolicyByString(sched),this.calculationPolicyByString(calc),yearOfSeason);
            //save into DB - table DaoSeason

        }

    }


    /**
     * show user his scheduling policies options
     * @return  List of Scheduling Policies
     *  @codeBy Eden
     */
    public List<SchedulingPolicy> watchSchedulingPolicies(){
        List <SchedulingPolicy> list=new LinkedList<>();
        list.add(new SchedualeOption1());
        list.add(new SchedualeOption2());
        return list;
    }

//    /**
//     * Define Calculating policy to specific season
//     * @param season
//     * @param CalcPolicy
//     */
//     public void DefineClaculatingPolicyToSeason(String season, String CalcPolicy){
//
//     }
//
//    /**
//     * Define Scheduling policy to specific season
//     * @param season
//     * @param SchedualePolicy
//     */
//    public void DefineSchedualingPolicyToSeason(String season, String SchedualePolicy){
//
//    }

    /**
     * show user his Calculation policies options
     * @return  List of Calculation Policies
     *  @codeBy Eden
     */
    public List<CalculationPolicy> watchCalculationPolicies(){
        List <CalculationPolicy> list=new LinkedList<>();
        list.add(new CalculateOption1());
        return list;
    }

    /**
     * gets list of leagues and define a new season to them
     * @param leaguesToDefine
     * @param season
     * @param sp
     * @param cp
     * @param teams
     *  @codeBy Eden
     */
    public void defineSeasonToLeagues(Rfa user, List <League> leaguesToDefine, int season, SchedulingPolicy sp, CalculationPolicy cp, HashSet<Team> teams, LinkedHashSet<Referee> referees,boolean defineCurrSeason) throws Exception {
        for( League l:leaguesToDefine){
            user.defineSeasonToLeague(sp,cp,season,l,teams,referees,defineCurrSeason);
        }
    }

    /**
     * delete referee - before this function show all referee from systemOperationsController
     * @param re
     * @param user
     * @throws Exception
     *  @codeBy Eden
     */
    public void  deleteReferee(Referee re, Rfa user) throws Exception {
        user.deleteReferee(re);
    }

    /**
     * add new referee
     * @param user
     * @param name
     * @param phoneNumber
     * @param email
     * @param userName
     * @param password
     * @param qualification
     * @param birthDate
     * @throws Exception
     *  @codeBy Eden
     */
    public void addReferee(Rfa user,String name, String phoneNumber, String email, String userName, String password, String qualification, Date birthDate) throws Exception{
        user.addReferee(name,phoneNumber,email,userName,password,qualification,birthDate);
    }

    public void startSchedulingPolicy(Rfa user, Season season, HashSet<Referee> refs, Referee mainReferee) throws Exception {
        if(refs==null||mainReferee==null){
            throw new Exception("please choose referees");
        }
        if(season!=null) {
            for (Map.Entry<League, HashSet<Team>> entry : season.getTeamsInCurrentSeasonLeagues().entrySet()) {
                if (entry.getValue().size() < 0) {
                    throw new Exception("league " + entry.getKey().getName() + " without teams, add teams first");
                }
            }
            user.startSchedulingPolicy(season);
        }
        else{
            throw new Exception("first define the season");
        }
    }


    public void startCalculationPolicy(Rfa user, Season season) throws Exception {
        if(season!=null) {
            user.startCalculationPolicy(season);
        }
        else{
            throw new Exception("first define the season");
        }
    }

    /***Handling with team request:**/
    /**
     * show team request
     * @param user
     * @return
     *  @codeBy Eden
     */
    public HashSet<Team> getTeamRequest(Rfa user){
        return user.getTeamRequests();
    }

    /***
     * answer to team request
     * @param team
     * @param desicion
     *  @codeBy Eden
     */
    public void answerToRequest(Rfa user,Team team, boolean desicion) throws Exception {
        user.answerRequest(team,desicion);
    }

    /***
     * every role represent by unique string:
     *      1. income for each month bigger than 100 for firstRoleForBudget
     *      2. income for each quarterly bigger than 1000 -> for secondRoleForBudget
     * @param user
     * @param budgetRole
     *  @codeBy Eden
     */
    public void addBudgetRole (Rfa user ,String budgetRole){
        if(budgetRole.equals("income for each month bigger than 100")){
            user.firstRoleForBudget(MainSystem.getInstance().getCurrSeason().getYear());
        }
        if(budgetRole.equals("income for each quarterly bigger than 1000")){
            user.secondRoleForBudget(MainSystem.getInstance().getCurrSeason().getYear());
        }
    }

    /**
     * show Budget Role Options
     * @return
     *  @codeBy Eden
     */
    public List<String> showBudgetRoleOptions(){
        List<String> list=new LinkedList<>();
        list.add("income for each quarterly bigger than 1000");
        list.add("income for each month bigger than 100");
        return list;
    }

    /***
     * mark list of notifications as read.
     * @param rfa
     * @param read
     * @codeBy Eden
     */
    public void markAsReadNotification (Rfa rfa, HashSet<Notification> read){
        for(Notification n: read){
            if(rfa.getNotificationsList().contains(n)) {
                rfa.MarkAsReadNotification(n);
            }
        }
    }

    /**
     * get all the teams in the leagues in the season it got
     * @param season
     * @return
     */
    public List<Team> getAllTeamsInSeason(Season season){
        List<Team> AllTeamsInSeason=new ArrayList<>();
        HashMap<League, HashSet<Team>> teamsInCurrentSeasonLeagues=season.getTeamsInCurrentSeasonLeagues();
        for (Map.Entry<League, HashSet<Team>> entry : teamsInCurrentSeasonLeagues.entrySet()) {
            League currLeague= entry.getKey();
            HashSet<Team> teamsInLegue =entry.getValue();
            for (Team t:teamsInLegue) {
                AllTeamsInSeason.add(t);
            }
        }
        return AllTeamsInSeason;
    }

}

