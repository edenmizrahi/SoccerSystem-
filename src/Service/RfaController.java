package Service;
import Domain.LeagueManagment.Calculation.CalculateOption1;
import Domain.LeagueManagment.Calculation.CalculationPolicy;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Scheduling.SchedualeOption1;
import Domain.LeagueManagment.Scheduling.SchedualeOption2;
import Domain.LeagueManagment.Scheduling.SchedulingPolicy;
import Domain.LeagueManagment.Season;
import Domain.LeagueManagment.Team;
import Domain.Notifications.Notification;
import Domain.Users.Referee;
import Domain.Users.Rfa;
import Domain.Users.SystemManager;

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
     public void defineSeasonToLeagues(Rfa user, List <League> leaguesToDefine, int season, SchedulingPolicy sp, CalculationPolicy cp, HashSet<Team> teams) throws Exception {
        for( League l:leaguesToDefine){
            user.defineSeasonToLeague(sp,cp,season,l,teams);
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
             user.startSchedulingPolicy(season, refs, mainReferee);
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
     public LinkedList<Team> getTeamRequest(Rfa user){
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
           // user.firstRoleForBudget();
        }
        if(budgetRole.equals("income for each quarterly bigger than 1000")){
            //user.secondRoleForBudget();
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

}
