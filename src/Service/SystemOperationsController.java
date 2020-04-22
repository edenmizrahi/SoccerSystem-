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
            t1.setName("macabi");
            system.addTeamName("macabi");

            Team t2=new Team();
            t1.setName("hapoel");
            system.addTeamName("hapoel");


            /**add Ilan as Team Owner (founder) of t1 ***/
            Fan f1=new Fan(system, "Ilan", "0549716910","yossi@gmail.com", "YossiHamelech5", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            TeamRole ilanTeamOwner=new TeamRole(f1);
            ilanTeamOwner.becomeTeamOwner();
            ilanTeamOwner.getTeamOwner().addNewTeam(t1);
            t1.setFounder(ilanTeamOwner.getTeamOwner());
            t1.addTeamOwner(ilanTeamOwner.getTeamOwner());
            /*********************************************/


            /**add Avi as Team Owner (founder) of t2 ***/
            Fan f7=new Fan(system, "Avi", "0549716910","yossi@gmail.com", "frkjamelech5", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            TeamRole aviTeamOwner=new TeamRole(f7);
            aviTeamOwner.becomeTeamOwner();
            aviTeamOwner.getTeamOwner().addNewTeam(t2);
            t2.addTeamOwner(aviTeamOwner.getTeamOwner());
            t2.setFounder(aviTeamOwner.getTeamOwner());
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

            /**add 11 players to t1*/
            add11PlayersToTeam(t1,ilanTeamOwner.getTeamOwner(),"d");
            /*********************/

            /**add 22 players to t2*/
            add11PlayersToTeam(t2,ilanTeamOwner.getTeamOwner(),"d");
            add11PlayersToTeam(t2,ilanTeamOwner.getTeamOwner(),"a");
            /**********************/

            /**Tami to be player without team**/
            Fan f4= new Fan(system, "Tami", "0549716910","yossi@gmail.com", "YossiHamelech2", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            TeamRole tamiPlayer=new TeamRole(f4);
            tamiPlayer.becomePlayer();
            /**********************************/

            /**Haim to be a coach at t1 */
            Fan f5= new Fan(system, "Haim", "0549716910","yossi@gmail.com", "YossiHamelech1", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            TeamRole haimCoach=new TeamRole(f5);
            haimCoach.becomeCoach();
            haimCoach.getCoach().setCoachTeam(t1);
            t1.setCoach(haimCoach.getCoach());
            /****************************/

            /**ben to be a coach at t2 */
            Fan f8= new Fan(system, "ben", "0549716910","yossi@gmail.com", "ben", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            TeamRole benCoach=new TeamRole(f8);
            benCoach.becomeCoach();
            benCoach.getCoach().setCoachTeam(t2);
            t2.setCoach(benCoach.getCoach());
            /****************************/

            /**Dana to be RFA*/
            Fan f6= new Fan(system, "Dana", "0549716910","yossi@gmail.com", "DanaBandana", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
            Rfa danaRFA=new Rfa(f6,system);
            /*****************/

            System.out.println("lalala");

        }

        public static void add11PlayersToTeam( Team t1, TeamOwner tO,String uniqueStringForUserName) throws Exception {
            Fan f;
            TeamRole player;

            for(int i=0; i<11; i++){
                f= new Fan(MainSystem.getInstance(), "player:"+t1.getName()+i+uniqueStringForUserName, "0549716910","yossi@gmail.com", "player:"+t1.getName()+i+uniqueStringForUserName, "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
                player=new TeamRole(f);
                player.becomePlayer();
                tO.addPlayer(player,"FFF",t1);

            }


        }

}
