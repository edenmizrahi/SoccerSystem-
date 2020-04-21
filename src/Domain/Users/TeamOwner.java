package Domain.Users;

import Domain.*;
import Domain.Enums.TeamManagerPermissions;
import Domain.LeagueManagment.Field;
import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Team;
import Domain.Notifications.Notification;
import Domain.Notifications.NotificationsUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TeamOwner implements Observer , NotificationsUser {
    private TeamRole teamRole;
    private LinkedList<Team> teams;
    private HashSet<TeamSubscription> mySubscriptions;
    private LinkedList<Team> requestedTeams;
    private LinkedList<Team> deletedTeams;
    private LinkedList<Team> approvedTeams;
    private static final Logger LOG = LogManager.getLogger();
    private HashSet<Notification> notifications;


    //team owner founder- with no team.
    public TeamOwner(TeamRole teamRole) {
        this.teams = new LinkedList<>();
        mySubscriptions = new HashSet<>();
        this.teamRole = teamRole;
        this.requestedTeams=new LinkedList<>();
        this.deletedTeams= new LinkedList<>();
        this.approvedTeams= new LinkedList<>();
        mySubscriptions = new HashSet<>();
        this.teamRole= teamRole;
        this.notifications= new HashSet<>();
    }


    /**OR
     * request the opening new team
     * @param name
     */
    //TODO test-V
    public void requestNewTeam(String name) throws Exception {
        if(teamRole.system.getTeamNames().contains(name)){
            throw new Exception("team name not unique, already exist in system");
        }
        Team t= new Team(name,this);
        //the request is sent in the Constructor
        requestedTeams.add(t);
    }

    /**Or
     *  check if the team can become active
     *  if can- send to become active function in team
     * @param team
     * @param players
     * @param coach
     * @param field
     * @throws Exception - one of the parameters is null, team was not approved by RFA
     */
    //TODO test-V
    public void makeTeamActive(Team team, HashSet<Player> players , Coach coach, Field field) throws Exception{
        if(team == null || players == null || coach == null || field==null){
            throw new NullPointerException();
        }
        if(! approvedTeams.contains(team)){
            throw  new Exception("this team is not approved by RFA");
        }
        team.becomeActive(players,coach,field);
        this.teams.add(team);
        this.approvedTeams.remove(team);
    }

    /**OR
     * this function checks if the team can be reopened- if can- send to team delete function
     * @param team
     * @throws Exception - if team is null,team is playing in current season, team has a future match
     */
    //TODO test-V
    public void deleteTeam(Team team) throws Exception {
        if(team == null){
            throw new NullPointerException();
        }
        if(team.getLeaguePerSeason().containsKey(teamRole.system.getCurrSeason())){
            throw new Exception("team is play in the current season ,you cannot delete the team until the end of the season");
        }
        Date currDate= new Date(System.currentTimeMillis());
        for (Match m:team.getHome()) {
            if(m.getStartDate().after(currDate)){
                throw  new Exception("cannot delete team with future matches");
            }
        }
        for( Match m:team.getAway()){
            if(m.getStartDate().after(currDate)){
                throw  new Exception("cannot delete team with future matches");
            }
        }
        team.deleteTeamByTeamOwner();
    }

    /**OR
     * reopen deleted team- this function checks if the team can be reopened
     * if yes- send to team reopen function
     * @param team
     * @param players
     * @param coach
     * @param field
     * @throws Exception
     */
    //TODO test- V
    public void reopenTeam(Team team, HashSet<Player> players, Coach coach, Field field) throws Exception {
        if(team ==null || players==null|| coach==null || field==null){
            throw new NullPointerException();
        }
        if(!deletedTeams.contains(team)){
            throw new Exception("the team was not deleted");
        }
        team.reopenTeam(players,coach,field,this);
        deletedTeams.remove(team);
        teams.add(team);
    }


    //<editor-fold desc="add remove and edit">

    /**
     * adi
     * this team owner subscribes a fan to become a team owner of a specific team
     * @param fan - the person you want to make a team owner
     * @param team - the team you want to add a team owner
     * @return the new team owner
     * @throws Exception
     */
    //TODO test-V
    public TeamRole subscribeTeamOwner(Fan fan, Team team) throws Exception{
        if (fan == null || team == null){
            throw new NullPointerException();
        }
        // check if already team owner of this team
        if (fan instanceof TeamRole && ((TeamRole) fan).isTeamOwner() && team.getTeamOwners().contains(((TeamRole) fan).getTeamOwner())){
            throw new Exception("Already Domain.LeagueManagment.Team Owner of this team");
        }
        TeamRole teamRole;
        // check if already team owner of different team
        if (fan instanceof TeamRole && ((TeamRole) fan).isTeamOwner()){
            ((TeamRole) fan).getTeamOwner().addNewTeam(team);
            team.addTeamOwner(((TeamRole) fan).getTeamOwner());
            teamRole = ((TeamRole) fan);
        }
        // check if already teamRole
        else if (fan instanceof TeamRole){
            ((TeamRole) fan).becomeTeamOwner();
            ((TeamRole) fan).getTeamOwner().addNewTeam(team);
            team.addTeamOwner(((TeamRole) fan).getTeamOwner());
            teamRole = ((TeamRole) fan);
        }
        // else just a fan
        else{
            teamRole = new TeamRole(fan);
            teamRole.becomeTeamOwner();
            teamRole.getTeamOwner().addNewTeam(team);
            team.addTeamOwner(teamRole.getTeamOwner());
        }
        TeamSubscription sub = new TeamSubscription(teamRole.getTeamOwner(), team, teamRole);
        mySubscriptions.add(sub);
        return teamRole;
    }

    /**
     * adi
     * this team owner removes a team owner that he subscribed in the past
     * @param tO - team owner to remove
     * @param team - the team that the team owner will be removed from
     * @throws Exception
     */
    //TODO test-V
    public void removeTeamOwner (TeamOwner tO, Team team)throws Exception{
        if (tO == null || team == null){
            throw new NullPointerException();
        }
        team.removeTeamOwner(tO);
        for(TeamSubscription sub : mySubscriptions){
            if (sub.user.equals(tO.getTeamRole()) && sub.role.equals(tO)){
                mySubscriptions.remove(sub);
            }
            break;
        }
        for (TeamSubscription sub : tO.mySubscriptions) {
            if (sub.team.equals(team)) {
                if (sub.role instanceof TeamOwner){
                    tO.removeTeamOwner((TeamOwner) sub.role, sub.team);
                }
                else{
                    tO.removeTeamManager((TeamManager) sub.role, sub.team);
                }
            }
        }
        tO.removeTeam(team);

    }

    /**
     * adi
     * this team owner subscribes a fan to become a team manager of a specific team
     * @param fan - the person you want to make a team manager
     * @param team - the team you want to add a team manager
     * @param per - the team managers allowed permissions
     * @return the new team manager
     * @throws Exception
     */
    //TODO test-V
    public TeamRole subscribeTeamManager(Fan fan, Team team, HashSet<TeamManagerPermissions> per) throws Exception{
        if (fan == null  || team == null || per == null){
            throw new NullPointerException();
        }
        // check if already team manager of this team
        if (fan instanceof TeamRole && ((TeamRole) fan).isTeamManager() && team.getTeamManager().equals(((TeamRole) fan).getTeamManager())){
            throw new Exception("Already Domain.LeagueManagment.Team Manager of this team");
        }
        TeamRole teamRole;
        // check if already teamRole
        if (fan instanceof TeamRole){
            ((TeamRole) fan).becomeTeamManager(team, per);
            ((TeamRole) fan).getTeamManager().setTeam(team);
            teamRole = ((TeamRole) fan);
        }
        // else just a fan
        else{
            teamRole = new TeamRole(fan);
            teamRole.becomeTeamManager(team, per);
            //teamRole.getTeamManager().setTeam(team);
        }
        TeamSubscription sub = new TeamSubscription(teamRole.getTeamManager(), team, teamRole);
        mySubscriptions.add(sub);
        return teamRole;

    }
    /**
     * adi
     * this team owner removes a team manager that he subscribed in the past
     * @param tM - team manager to remove
     * @param team - the team that the team manager will be removed from
     * @throws Exception
     */
    //TODO test-V
    public void removeTeamManager (TeamManager tM, Team team) throws Exception{
        if (tM == null || team == null || team == null){
            throw new NullPointerException();
        }
        team.removeTeamManager(tM);
        for(TeamSubscription sub : mySubscriptions){
            if (sub.user.equals(tM.getTeamRole()) && sub.role.equals(tM)){
                mySubscriptions.remove(sub);
            }
            break;
        }
        for (TeamSubscription sub : tM.getMySubscriptions()) {
            if (sub.team.equals(team)) {
                tM.removeTeamOwner((TeamOwner) sub.role, sub.team);

            }
        }
        tM.setTeam(null);
        tM.getTeamRole().deleteTeamManager();
    }

    /**
     * adi
     * remove the current coach and replace with a new one
     * @param coachToRemove - the coach to remove
     * @param coachToAdd - the coach to add
     * @param newCoachRoleAtTeam - the new coaches role
     * @param team - the team that will have the changes
     * @throws Exception
     */
    //TODO test-V
    public void removeAndReplaceCoach(Coach coachToRemove, TeamRole coachToAdd, String newCoachRoleAtTeam, Team team) throws Exception {
        if (coachToRemove == null || coachToAdd == null || team == null || newCoachRoleAtTeam==null){
            throw new NullPointerException();
        }
        if (team.getCoach().equals(coachToRemove)) {
            team.removeCoach(coachToRemove);
            coachToRemove.setCoachTeam(null);
            if (!coachToAdd.isCoach()){
                coachToAdd.becomeCoach();
            }
            team.addCoach(coachToAdd.getCoach());
            coachToAdd.getCoach().setCoachTeam(team);
            coachToAdd.getCoach().setRoleAtTeam(newCoachRoleAtTeam);
        }
        else {
            throw new Exception("This Domain.Users.Coach doesn't exist in this team");
        }
    }

    /**
     * adi
     * edit coach's role
     * @param coach - the coach to edit
     * @param role - the new role
     */
    //TODO test-V
    public void editCoachRole(Coach coach, String role){
        if (coach == null || role == null){
            throw new NullPointerException();
        }
        coach.setRoleAtTeam(role);
    }

    /**
     * adi
     * add a player to a team
     * @param player - player to add
     * @param role - the players role
     * @param team - the team to add the player
     */
    //TODO test-V
    public void addPlayer(TeamRole player, String role, Team team) throws Exception {
        if (player == null || role == null || team == null){
            throw new NullPointerException();
        }
        if (!player.isPlayer()){
            player.becomePlayer();
        }

        if(player.getPlayer().getTeam()!=null) {
            team.addPlayer(player.getPlayer());
            player.getPlayer().setPlayerTeam(team);
            player.getPlayer().setRoleAtField(role);
        }
        else{
            throw new Exception("This player is already in another team");
        }
    }

    /**
     * adi
     * remove a player from a team (only if team has more than 11 players)
     * @param player - player to remove
     * @param team - the team to remove the player
     * @throws Exception
     */
    //TODO test-V
    public void removePlayer (Player player, Team team) throws Exception {
        if (player == null || team == null){
            throw new NullPointerException();
        }
        team.removePlayer(player);
        player.setPlayerTeam(null);
    }

    /**
     * adi
     * edit the players role
     * @param player - the player to edit
     * @param role - the new role
     */
    //TODO test-V
    public void editPlayerRole(Player player, String role){
        if (player == null || role == null){
            throw new NullPointerException();
        }
        player.setRoleAtField(role);
    }

    /**
     * adi
     * remove the current field and replace with new field
     * @param fieldtoRemove - the field to remove
     * @param fieldToAdd - the new field to add
     * @param team - the team to change its field
     * @throws Exception
     */
    //TODO test-V
    public void removeAndReplaceField (Field fieldtoRemove, Field fieldToAdd, Team team) throws Exception {
        if (fieldtoRemove == null || fieldToAdd == null || team == null){
            throw new NullPointerException();
        }
        team.removeField(fieldtoRemove);
        fieldtoRemove.removeTeam(team);
        team.setField(fieldToAdd);
        fieldToAdd.addTeam(team);
    }

    /**
     * adi
     * edit the fields name
     * @param field - the field to edit
     * @param name - the new name
     */
    //TODO test-V
    public void editFieldName(Field field, String name){
        if (field == null || name == null){
            throw new NullPointerException();
        }
        field.setNameOfField(name);
    }
    //</editor-fold>

    //<editor-fold desc="setters and getters">
    public void addNewTeam(Team team) {
        this.teams.add(team);
    }
    public void removeTeam(Team team){
        if (teams.contains(team)){
            teams.remove(team);
        }
    }

    public LinkedList<Team> getTeams() {
        return teams;
    }

    public HashSet<TeamSubscription> getMySubscriptions() {
        return mySubscriptions;
    }


    public TeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }

    public void setTeams(LinkedList<Team> teams) {
        this.teams = teams;
    }

    public LinkedList<Team> getRequestedTeams() {
        return requestedTeams;
    }

    public void setRequestedTeams(LinkedList<Team> requestedTeams) {
        this.requestedTeams = requestedTeams;
    }

    public LinkedList<Team> getDeletedTeams() {
        return deletedTeams;
    }

    public void setDeletedTeams(LinkedList<Team> deletedTeams) {
        this.deletedTeams = deletedTeams;
    }

    public LinkedList<Team> getApprovedTeams() {
        return approvedTeams;
    }

    public void setApprovedTeams(LinkedList<Team> approvedTeams) {
        this.approvedTeams = approvedTeams;
    }

    //</editor-fold>

    /**OR
     * add income to certain team
     * @param team
     * @param typeOfIncome
     * @param amount
     * @throws Exception
     */
    //TODO test-V
    public void addIncomeToTeam(Team team,String typeOfIncome, long amount) throws Exception {
        if(team==null || typeOfIncome==null){
            throw  new NullPointerException();
        }
        team.addIncome(typeOfIncome,amount);
    }

    /**or
     * add income to certain team
     * @param team
     * @param typeOfExpense
     * @param amount
     * @throws Exception
     */
    //TODO test-V
    public void addExpenseToTeam(Team team,String typeOfExpense, long amount) throws Exception {
        if(team==null || typeOfExpense==null){
            throw  new NullPointerException();
        }
        team.addExpense(typeOfExpense,amount);
    }

    //<editor-fold desc="Notifications Handler" >
    /**
     * Notifications about:
     *      1. team can be open\not
     *      2.team removed
     *@codeBy OR and Eden
     * **/
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Team){
            if(arg.equals(true)){// the team can be open
               requestedTeams.remove(o);
               approvedTeams.add((Team)o);
               notifications.add(new Notification(o,"Domain.LeagueManagment.Team "+((Team)o).getName()+" can be open",false));
            }
            else if(arg.equals(false)){// the team cant be open
                ((Team)o).deleteObservers();
                requestedTeams.remove((Team)o);
                notifications.add(new Notification(o,"Sorry but team "+((Team)o).getName()+" cant be open",false));

            }
        }

        /**notification about close team forever*/
        if(o instanceof Team && arg instanceof  String && ((String)arg).contains("removed")){
            notifications.add(new Notification(o,arg,false));
        }
    }



    /**
     * mark notification as readen
     * @param not-unread notification to mark as read
     * @codeBy Eden
     */
    public void MarkAsReadNotification(Notification not){
        not.setRead(true);
    }

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
    //</editor-fold>
}
