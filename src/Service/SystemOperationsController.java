package Service;

import Domain.Enums.TeamManagerPermissions;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Season;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Users.*;

import java.text.ParseException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SystemOperationsController {

    public HashSet<Team> showAllTeams(){
        return MainSystem.getInstance().getActiveTeams();
    }

    /**
     * show to use all leagues at system
     * @return
     *  @codeBy Eden
     */
    public List<League> showLeagus(){
        return MainSystem.getInstance().getLeagues();
    }

    /**
     * show all TeamOwnerUsers in order to replace one of them.
     * @return all TeamOwners
     *  @codeBy Eden
     */
    public List<TeamOwner> showAllTeamOwner()
    {
        return MainSystem.getInstance().getAllTeamOwners();
    }

    /**
     * return all referee at system
     * @return
     *  @codeBy Eden
     */
    public  List<Referee> showAllReferee(){
        return MainSystem.getInstance().getAllReferees();

    }

    /**
     * return all seasons
     * @return
     *  @codeBy Eden
     */
    public LinkedList<Season> getAllSeasons(){
        return MainSystem.getInstance().getSeasons();

    }

    /**
     * return current season
     * @return
     *  @codeBy Eden
     */
    public Season getSeason(){
        return MainSystem.getInstance().getCurrSeason();

    }

     public  List<Player> getAllPlayers(){
        return MainSystem.getInstance().getAllPlayer();
     }

    /**
     * start system - if its first start
     * return the default user name and password
     * otherwise return null.
     * @return List of default user name and password or null
     * @throws ParseException
     * @codeBy Eden
     */
     public List<String> startSystem() throws ParseException {
        List <String > defaultDetails=new LinkedList<>();
         if(MainSystem.getInstance().getUsers().size()==0){
             defaultDetails.add("systemManager");
             defaultDetails.add("systemManager101");
             MainSystem.getInstance().startSystem();
             return defaultDetails;
         }
         else{
             MainSystem.getInstance().startSystem();
             return null;
         }

     }




        public static void initSystemObjects() throws Exception {
            MainSystem system=MainSystem.getInstance();
            system.startSystem();
            SystemManager marioSystemManager=system.getSystemManagers().get(0);//there is only one system manager now (the default)
            Team t1=new Team();
            Team t2=new Team();

            /**add Ilan as Team Owner (founder) of t1 ***/
            Fan f1=new Fan(system, "Ilan", "0549716910","yossi@gmail.com", "YossiHamelech5", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            TeamRole ilanTeamOwner=new TeamRole(f1);
            ilanTeamOwner.becomeTeamOwner();
            ilanTeamOwner.getTeamOwner().addNewTeam(t1);
            t1.setFounder(ilanTeamOwner.getTeamOwner());
            t1.addTeamOwner(ilanTeamOwner.getTeamOwner());
            /*********************************************/

            /**Ilan subscribe moshe to be team Manager with the all permissions**/
            Fan f2=new Fan(system, "Moshe", "0549716910","yossi@gmail.com", "YossiHamelech4", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            HashSet<TeamManagerPermissions> permissions=new HashSet<>();
            permissions.add(TeamManagerPermissions.addRemoveEditPlayer);
            permissions.add(TeamManagerPermissions.addRemoveEditTeamOwner);
            permissions.add(TeamManagerPermissions.addRemoveEditCoach);
            permissions.add(TeamManagerPermissions.addRemoveEditField);
            permissions.add(TeamManagerPermissions.addToBudgetControl);
            ilanTeamOwner.getTeamOwner().subscribeTeamManager(f2,t1,permissions);
            /******************************************************************/

            /**Gabi to be a player at t1 */
            Fan f3= new Fan(system, "Gabi", "0549716910","yossi@gmail.com", "YossiHamelech3", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            TeamRole gabiPlayer=new TeamRole(f3);
            gabiPlayer.becomePlayer();
            ilanTeamOwner.getTeamOwner().addPlayer(gabiPlayer,"FFF",t1);
            /****************************/

            /**Tami to be player without team**/
            Fan f4= new Fan(system, "Tami", "0549716910","yossi@gmail.com", "YossiHamelech2", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            TeamRole tamiPlayer=new TeamRole(f4);
            tamiPlayer.becomePlayer();
            /**********************************/

            /**Haim to be a coach at t1 */
            Fan f5= new Fan(system, "Haim", "0549716910","yossi@gmail.com", "YossiHamelech1", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            TeamRole haimCoach=new TeamRole(f5);
            gabiPlayer.becomeCoach();
            t1.setCoach(haimCoach.getCoach());
            /****************************/

            /**Dana to be RFA*/
            Fan f6= new Fan(system, "Dana", "0549716910","yossi@gmail.com", "DanaBandana", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            Rfa danaRFA=new Rfa(f6,system);
            /*****************/

            System.out.println("lalala");

        }

}
