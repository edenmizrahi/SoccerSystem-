package Domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TeamManager implements Observer,NotificationsUser {
    private TeamRole teamRole;
    private Team team;
    private HashSet<TeamSubscription> mySubscriptions;
    private static final Logger LOG = LogManager.getLogger();
    private HashSet<Permission> permissions;
    private HashSet<Notification> notificationsList;


    public TeamManager(TeamRole teamRole,Team team, HashSet<Permission> pers) {
        this.team = team;
        this.team.setTeamManager(this);
        mySubscriptions = new HashSet<>();
        permissions = new HashSet<>();
        this.permissions.addAll(pers);
        this.teamRole= teamRole;
        notificationsList=new HashSet<>();
    }

    //<editor-fold desc="getters and setters">

    public HashSet<TeamSubscription> getMySubscriptions() {
        return mySubscriptions;
    }

    public void setMySubscriptions(HashSet<TeamSubscription> mySubscriptions) {
        this.mySubscriptions = mySubscriptions;
    }

    public Team getTeam() { return team; }

    public void setTeam(Team team) { this.team = team; }

    public TeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }
    //</editor-fold>

    //<editor-fold desc="add remove and edit">
    /**
     * adi
     * this team manager subscribes a fan to become a team owner of a specific team
     * @param fan - the person you want to make a team owner
     * @param ms - main system
     * @param team - the team you want to add a team owner
     * @return the new team owner
     * @throws Exception
     */
    //TODO test
    public TeamOwner subscribeTeamOwner(Fan fan, MainSystem ms, Team team) throws Exception{
        if (fan == null || ms == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditTeamOwner)) {
            // check if already team owner of this team
            if (fan instanceof TeamRole && ((TeamRole) fan).isTeamOwner() && team.getTeamOwners().contains(((TeamRole) fan).getTeamOwner())){
                throw new Exception("Already Domain.Team Owner of this team");
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
            return teamRole.getTeamOwner();
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    /**
     * adi
     * this team manager removes a team owner that he subscribed in the past
     * @param tO - team owner to remove
     * @param ms - main system
     * @param team - the team that the team owner will be removed from
     * @throws Exception
     */
    //TODO test
    public void removeTeamOwner (TeamOwner tO, MainSystem ms, Team team)throws Exception{
        if (tO == null || ms == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditTeamOwner)) {
            team.removeTeamOwner(tO);
            for(TeamSubscription sub : mySubscriptions){
                if (sub.user.equals(tO.getTeamRole()) && sub.role.equals(tO)){
                    mySubscriptions.remove(sub);
                }
                break;
            }
            for (TeamSubscription sub : tO.getMySubscriptions()) {
                if (sub.team.equals(team)) {
                    if (sub.role instanceof TeamOwner){
                        tO.removeTeamOwner((TeamOwner) sub.role, ms, sub.team);
                    }
                    else{
                        tO.removeTeamManager((TeamManager) sub.role, ms, sub.team);
                    }
                }
            }
            tO.removeTeam(team);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
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
    //TODO test
    public void removeAndReplaceCoach(Coach coachToRemove, TeamRole coachToAdd, String newCoachRoleAtTeam, Team team) throws Exception {
        if (coachToRemove == null || coachToAdd == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditCoach)) {
            if (team.getCoach().equals(coachToRemove)) {
                team.removeCoach(coachToRemove);
                coachToRemove.setCoachTeam(null);
                if (!coachToAdd.isCoach()) {
                    coachToAdd.becomeCoach();
                }
                team.addCoach(coachToAdd.getCoach());
                coachToAdd.getCoach().setCoachTeam(team);
                coachToAdd.getCoach().setRoleAtTeam(newCoachRoleAtTeam);
            } else {
                throw new Exception("This Domain.Coach doesn't exist in this team");
            }
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * edit coach's role
     * @param coach - the coach to edit
     * @param role - the new role
     */
    //TODO test
    public void editCoachRole(Coach coach, String role)throws Exception {
        if (coach == null || role == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditCoach)) {
            coach.setRoleAtTeam(role);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    /**
     * adi
     * add a player to a team
     * @param player - player to add
     * @param role - the players role
     * @param team - the team to add the player
     */
    //TODO test
    public void addPlayer(TeamRole player, String role, Team team) throws Exception{
        if (player == null || role == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditPlayer)) {
            if (!player.isPlayer()) {
                player.becomePlayer();
            }
            team.addPlayer(player.getPlayer());
            player.getPlayer().setPlayerTeam(team);
            player.getPlayer().setRoleAtField(role);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * remove a player from a team (only if team has more than 11 players)
     * @param player - player to remove
     * @param team - the team to remove the player
     * @throws Exception
     */
    //TODO test
    public void removePlayer (Player player, Team team) throws Exception {
        if (player == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditPlayer)) {
            team.removePlayer(player);
            player.setPlayerTeam(null);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * edit the players role
     * @param player - the player to edit
     * @param role - the new role
     */
    //TODO test
    public void editPlayerRole(Player player, String role)throws Exception {
        if (player == null || role == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditPlayer)) {
            player.setRoleAtField(role);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * remove the current field and replace with new field
     * @param fieldtoRemove - the field to remove
     * @param fieldToAdd - the new field to add
     * @param team - the team to change its field
     * @throws Exception
     */
    //TODO test
    public void removeAndReplaceField (Field fieldtoRemove, Field fieldToAdd, Team team) throws Exception {
        if (fieldtoRemove == null || fieldToAdd == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditField)) {
            team.removeField(fieldtoRemove);
            fieldtoRemove.removeTeam(team);
            team.setField(fieldToAdd);
            fieldToAdd.addTeam(team);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * edit the fields name
     * @param field - the field to edit
     * @param name - the new name
     */
    //TODO test
    public void editFieldName(Field field, String name) throws Exception {
        if (field == null || name == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditField)) {
            field.setNameOfField(name);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //</editor-fold>

    /**or**/
    //TODO test
    public void addIncomeToTeam(Team team,String typeOfIncome, long amount) throws Exception {
        if(this.permissions.contains(Permission.addToBudgetControl)){
            team.addIncome(typeOfIncome,amount);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }

    }

    /**Or
     *
     * @param team
     * @param typeOfExpense
     * @param amount- positive!!! in budget conrtol in becomes negative
     * @throws Exception
     */
    //TODO test
    public void addExpenseToTeam(Team team,String typeOfExpense, long amount) throws Exception {
        if(this.permissions.contains(Permission.addToBudgetControl)){
            team.addExpense(typeOfExpense,amount);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }

    //<editor-fold desc="Notifications Handler">
    /**
     * get notifications about close Domain.Team
     * @param o
     * @param arg
     * @codeBy Eden
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Team && arg instanceof  String && ((String)arg).contains("removed")){
            notificationsList.add(new Notification(o,arg,false));
        }
    }

    /**
     * mark notification as readen
     * @param not-unread notification to mark as read
     * @codeBy Eden
     */
    @Override
    public void MarkAsReadNotification(Notification not){
        not.isRead=true;
    }
    @Override
    public HashSet<Notification> getNotificationsList() {
        return notificationsList;
    }

    /***
     * @return only the unread notifications . if not have return null
     * @codeBy Eden
     */
    @Override
    public HashSet<Notification> genUnReadNotifications(){
        HashSet<Notification> unRead=new HashSet<>();
        for(Notification n: notificationsList){
            if(n.isRead==false){
                unRead.add(n);
            }
        }
        return unRead;
    }
    //</editor-fold>
}
