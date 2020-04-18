import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SystemManager extends Fan implements Observer {

    private HashSet<Complaint> complaints;//*
    private static final Logger LOG = LogManager.getLogger();

    public SystemManager(Fan fan, MainSystem ms) {
        super(ms, fan.getName(), fan.getPhoneNumber(), fan.getEmail(), fan.getUserName(), fan.getPassword());
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


    /**
     * Eden
     */
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
    public void addSystemManager(Fan user){
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
    public List<Object> removeUser(Fan userToDelete) throws Exception {
        List<Object> objectsDeleted=new LinkedList<>();


        if(userToDelete instanceof Rfa) {
            /***must have at least one RFA at system**/
            if(system.numOfRfa()==1){
                throw new Exception("There is only one RFA left, you cannot delete it ");
            }
            objectsDeleted=deleteRfa(((Rfa)userToDelete));
        }
        if(userToDelete instanceof SystemManager) {
            if(userToDelete==this){
                throw  new Exception("You Cannot Delete Yourself!!!");
            }
            /***must have at least one system Manager at system**/
            if(system.getSystemManagers().size()==1){
                throw new Exception("There is only one System Manager left, you cannot delete it ");
            }
            objectsDeleted=deleteSystemManager(((SystemManager)userToDelete));
        }
        if(userToDelete instanceof Referee) {
            objectsDeleted=deleteReferee(((Referee)userToDelete));
        }


        if(userToDelete instanceof TeamRole){
            objectsDeleted= teamRoleRemove(((TeamRole)userToDelete));
        }

        return objectsDeleted;
    }

    /**
     * delete Team Manager and delete all his subscribe .
     * @param userToDelete
     * @codeBy Eden
     */
    private List<Object> deleteTeamManager(TeamManager userToDelete) throws Exception {
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        if(userToDelete.getTeam()!=null) {
            userToDelete.getTeam().setTeamManager(null);
        }
        HashMap<Fan,Team> allSub= userToDelete.getMySubscriptions();
        for(Map.Entry<Subscription,Team> sub: allSub.entrySet()){
            ((TeamOwner)sub).removeTeamOwner(((TeamOwner)sub.getKey()),system, sub.getValue());
        }
        return res;

    }

    /**
     * Switch between team owner which is founder to another team owner.
     * @param toReplaceUser- the team owner to delete
     * @param toAddUser- the team owner to subscribe as founder
     * @param team
     * @return the removed objects
     * @throws Exception
     * @codeBy Eden
     */
    public void switchTeamOwnerFounder(TeamRole toReplaceUser, TeamRole toAddUser, Team team) throws Exception {
        if(toReplaceUser.getTeamOwner()==null){
            throw new Exception("team role is not a team owner");
        }
        if(toAddUser.getTeamOwner()==null){
            toAddUser.becomeTeamOwner();
        }
        TeamOwner toDelete=toReplaceUser.getTeamOwner();
        TeamOwner toAdd=toAddUser.getTeamOwner();

        if(team.getFounder()==toDelete){
            team.setFounder(toAdd);
            team.getTeamOwners().add(toAdd);
            toAdd.setTeam(team);
        }
        else{
            throw new Exception("wrong team owner and team");
        }
    }

    /**
     * remove team from system ,
     * if team does not have future Matches(not belong to current season).
     * doesnt delete all team matches.
     * disconnect the team owners and managers.
     * remove the team manager sub-> remove his subscriptions .
     * disconnect the coach
     * disconnect the players
     * disconnect from user.
     * @param teamToRemove
     * @codeBy Eden
     */
    public void removeTeamFromSystem(Team teamToRemove) throws Exception {
       if(teamToRemove.getLeaguePerSeason().containsKey(system.getCurrSeason())){
           throw new Exception("team is play in the current season ,you cannot delete the team untill the end of the season");
       }
       system.getAllTeams().remove(teamToRemove);
        for (TeamOwner curTeamOwner:teamToRemove.getTeamOwners()) {
//            curTeamOwner.getTeams().
        }
    }


    /****
     * TeamRole handling:
     *  1.check if remove is valid ->(coach\TeamOwner\TeamManager\Player)
     *  2.if coach!=null -> handlingCoachRemove
     *  3.if TeamOwner!=null->handlingTeamOwnerRemove
     *  4.if TeamManager!=null->handlingTeamManagerRemove
     *  5.if player!=null->handlingPlayerRemove
     *  6.disconnect user from system.
     *
     * @return lost of removed objects.
     * @exception if the Deletion isn't valid
     * @codeBy Eden
     */

    public List<Object> teamRoleRemove(TeamRole userToRemove) throws Exception {
        isValidRemove(userToRemove);
        /*******if not valid - exception thrown*******/
        List<Object> removedObjects=new LinkedList<>();
        /**if coach!=null -> handlingCoachRemove*/
        if(userToRemove.getCoach()!=null)  {
            removedObjects.addAll(deleteCoach(userToRemove.getCoach()));
        }
        /**if player!=null->handlingPlayerRemove*/
        if(userToRemove.getPlayer()!=null)  {
            removedObjects.addAll(deletePlayer(userToRemove.getPlayer()));
        }
        /**if TeamManager!=null->handlingTeamManagerRemove*/
        if(userToRemove.getTeamManager()!=null)  {
            removedObjects.addAll(deleteTeamManager(userToRemove.getTeamManager()));
        }
        /**if TeamOwner!=null->handlingTeamOwnerRemove*/
        if(userToRemove.getTeamOwner()!=null)  {
            removedObjects.addAll(deleteTeamOwner(userToRemove.getTeamOwner()));
        }

        /**disconnect user from system*/
        system.removeUser(userToRemove);

        return removedObjects;
    }

    /**
     * check if SystemManager can remove the TeamRole:
     *      1.if is coach and he connect to any team, throws Exception.
     *      2.if is player belong to any Team which hasn't more than 11 player throws Exception.
     *      3.if is TeamManager->always valid
     *      4.if is TeamOwner and he is a founder of any Team from Team list which he hold->throws Exception
     * @param userToRemove
     * @throws Exception
     */
    private void isValidRemove(TeamRole userToRemove) throws Exception{
        /**if is coach-> check if coach is not connected to any team ->
        if connect throws Exception("you have to replace team coach")*/
        if(userToRemove.getCoach()!=null) {
            if(userToRemove.getCoach().getCoachTeam()!=null){
                throw new Exception("you have to subscribe another Coach to "+(userToRemove.getCoach()).getCoachTeam().getName()+" Team first");
            }
        }
        /**if is player-> check if the player belong to any Team , if so , check if team has more than 11 player ->
        if not throws Exception ("you have to add more players to team before delete ")*/
        if(userToRemove.getPlayer()!=null) {
            if (userToRemove.getPlayer().getTeam() != null) {
                if (userToRemove.getPlayer().getTeam().getPlayers().size() <= 11) {
                    throw new Exception("You Cannot Delete Player From " + userToRemove.getPlayer().getTeam().getName() + " Team ,any team have to be at least 11 Players!");
                }
            }
        }

        /**if is TeamOwner-> if he is a founder of any Team from Team list which he hold->
         *throws Exception("you have to replace team founder")*/
        if(userToRemove.getTeamOwner()!=null){
            LinkedList<Team> OwnerTeams =userToRemove.getTeamOwner().getTeams();
            for(Team t: OwnerTeams){
                if(t.getFounder()==userToRemove.getTeamOwner()){
                    throw new Exception(userToRemove.getName()+" is founder of:"+ " "+t.getName() +"please replace the team's fonder");
                }
            }
        }


    }



    /**
     * delete Team Owner only if he isn't a Founder of any team and delete all his subscribe .
     * @param userToDelete
     * @throws Exception if team owner is founder of any team.
     * @codeBy Eden
     */
    private List<Object> deleteTeamOwner(TeamOwner userToDelete) throws Exception {
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        //userToDelete.getBudgetControl().removeTeamOwner(userToDelete);
        userToDelete.getSystem().removeUser(userToDelete);
        /***remove all the subscription**/
        LinkedList<Team> OwnerTeams =userToDelete.getTeams();
        for(Team t: OwnerTeams){
            if(t.getFounder()==userToDelete){
                throw new Exception(userToDelete.getName()+" is founder if:"+ " "+t.getName() +"please change the fonder");
            }
        }

        HashMap<Subscription, Team> subscriptions= userToDelete.getMySubscriptions();
        for(Map.Entry<Subscription,Team> sub: subscriptions.entrySet()){
            userToDelete.removeTeamOwner(((TeamOwner)sub.getKey()),system,sub.getValue());
        }
        return res;
    }
    /**
     * delete Player only if the team has at least 11 other players
     * @param userToDelete
     * @throws Exception
     * @codeBy Eden
     */
    private List<Object> deletePlayer(Player userToDelete) throws Exception {
        if( userToDelete.getTeam()!=null) {
            userToDelete.getTeam().removePlayer(userToDelete);
        }
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }

    /**
     * delete Coach .
     * @param userToDelete
     * @codeBy Eden
     */
    private List<Object> deleteCoach(Coach userToDelete) {
        List<Object> re=new LinkedList<>();
        re.add(userToDelete);
        return  re;
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
            if (m.getStartDate().after(new Date(System.currentTimeMillis()))) {
                throw new Exception("You cannot remove referee , he has matches in this season");
            }
        }
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }

    /**
     * delete System Manager.
     * @param userToDelete
     * @codeBy Eden
     */
    private List<Object> deleteSystemManager(SystemManager userToDelete) {
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }
    /**
     * delete RFA , disconnect from budget control and from system
     * @param userToDelete
     * @codeBy Eden
     */
    private List<Object> deleteRfa(Rfa userToDelete){
        //userToDelete.getBudgetControl().removeRfa(userToDelete);
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }

}

