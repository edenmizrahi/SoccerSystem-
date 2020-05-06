package Service;

import Domain.Complaint;
import Domain.Enums.TeamManagerPermissions;
import Domain.LeagueManagment.Calculation.CalculateOption1;
import Domain.LeagueManagment.Calculation.CalculationPolicy;
import Domain.LeagueManagment.Field;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Scheduling.SchedualeOption1;
import Domain.LeagueManagment.Scheduling.SchedulingPolicy;
import Domain.LeagueManagment.*;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Users.*;
import Stubs.StubExternalSystem;

import java.text.ParseException;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
     *   return all referee at system in LinkedHashSet for create season
     * @return
     */
    public LinkedHashSet<Referee> getAllREfereeInHasSet() {
        List<Referee> allRefs = showAllReferee();
        LinkedHashSet<Referee> allRefHashSet = new LinkedHashSet<Referee>(allRefs);
        return allRefHashSet;
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
    public Season getCurrSeason(){
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


    public static void initSystemObjectsAdi() throws Exception {
        MainSystem system=MainSystem.getInstance();
        system.startSystem();
        SystemManager marioSystemManager=system.getSystemManagers().get(0);//there is only one system manager now (the default)
        Team t1=new Team();
        t1.setName("macabi");
        system.addTeamName("macabi");
        t1.setActive(true);
        system.addActiveTeam(t1);
        Field field = new Field("field");
        t1.setField(field);

        Team t2=new Team();
        t2.setName("hapoel");
        system.addTeamName("hapoel");
        t2.setActive(true);
        system.addActiveTeam(t2);


        /**add Ilan as Team Owner (founder) of t1 ***/
        Fan f1=new Fan(system, "Ilan", "0549716910","yossi@gmail.com", "Ilan", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole ilanTeamOwner=new TeamRole(f1);
        ilanTeamOwner.becomeTeamOwner();
        ilanTeamOwner.getTeamOwner().addNewTeam(t1);
        t1.setFounder(ilanTeamOwner.getTeamOwner());
        t1.addTeamOwner(ilanTeamOwner.getTeamOwner());
        /*********************************************/

        /**add Arnold as another Team Owner of t1 ***/
        Fan arnold = new Fan(system, "Arnold", "0549716910","yossi@gmail.com", "Arnold", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole arnoldTeamOwner = new TeamRole(arnold);
        arnoldTeamOwner.becomeTeamOwner();
        arnoldTeamOwner.getTeamOwner().addNewTeam(t1);
        t1.addTeamOwner(arnoldTeamOwner.getTeamOwner());
        /*********************************************/

        /**add Avi as Team Owner (founder) of t2 ***/
        Fan f7=new Fan(system, "Avi", "0549716910","yossi@gmail.com", "Avi", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole aviTeamOwner=new TeamRole(f7);
        aviTeamOwner.becomeTeamOwner();
        aviTeamOwner.getTeamOwner().addNewTeam(t2);
        t2.addTeamOwner(aviTeamOwner.getTeamOwner());
        t2.setFounder(aviTeamOwner.getTeamOwner());
        /*********************************************/

        /**Arnold subscribe moshe to be team Manager with the all permissions**/
        Fan f2=new Fan(system, "Moshe", "0549716910","yossi@gmail.com", "Moshe", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        HashSet<TeamManagerPermissions> perMoshe = new HashSet<>();
        perMoshe.add(TeamManagerPermissions.addRemoveEditPlayer);
        perMoshe.add(TeamManagerPermissions.addRemoveEditTeamOwner);
        perMoshe.add(TeamManagerPermissions.addRemoveEditCoach);
        perMoshe.add(TeamManagerPermissions.addRemoveEditField);
        perMoshe.add(TeamManagerPermissions.addToBudgetControl);
        TeamRole mosheTeamManager = arnoldTeamOwner.getTeamOwner().subscribeTeamManager(f2,t1,perMoshe);
        /***moshe become a player as well***/
        mosheTeamManager.becomePlayer();

        /**Moshe subscribe armin to be team Owner with the all permissions**/
        Fan armin = new Fan(system, "Armin", "0549716910","yossi@gmail.com", "Armin", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole arminTeamOwner=new TeamRole(armin);
        arminTeamOwner.becomeTeamOwner();
        arminTeamOwner.getTeamOwner().addNewTeam(t1);
        t1.addTeamOwner(arminTeamOwner.getTeamOwner());

        /**avi subscribe david to be team Manager without any permissions**/
        Fan f10 = new Fan(system, "David", "0549716910","yossi@gmail.com", "David", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        HashSet<TeamManagerPermissions> perDavid = new HashSet<>();
        TeamRole davidTeamManager = aviTeamOwner.getTeamOwner().subscribeTeamManager(f10,t2,perDavid);

        /**add 11 players to t1*/
        add11PlayersToTeam(t1,ilanTeamOwner.getTeamOwner(),"d");
        /*********************/

        /**add 22 players to t2*/
        add11PlayersToTeam(t2,aviTeamOwner.getTeamOwner(),"d");
        add11PlayersToTeam(t2,aviTeamOwner.getTeamOwner(),"a");
        /**********************/

        /**Tami to be player without team**///##
        Fan f4= new Fan(system, "Tami", "0549716910","yossi@gmail.com", "Tami", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole tamiPlayer=new TeamRole(f4);
        tamiPlayer.becomePlayer();
        /**********************************/

        /**Haim to be a coach at t1 */
        Fan f5= new Fan(system, "Haim", "0549716910","yossi@gmail.com", "Haim", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole haimCoach=new TeamRole(f5);
        haimCoach.becomeCoach();
        haimCoach.getCoach().setCoachTeam(t1);
        t1.setCoach(haimCoach.getCoach());
        /****************************/

        /**ben to be a coach at t2 */
        Fan f8= new Fan(system, "ben", "0549716910","yossi@gmail.com", "Ben", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole benCoach=new TeamRole(f8);
        benCoach.becomeCoach();
        benCoach.getCoach().setCoachTeam(t2);
        t2.setCoach(benCoach.getCoach());
        /****************************/

        /**mark to be a coach without a team */
        Fan mark = new Fan(system, "Mark", "0549716910","yossi@gmail.com", "Mark", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole markCoach = new TeamRole(mark);
        markCoach.becomeCoach();
        /****************************/

        /**Dana to be RFA*/
        Fan f6= new Fan(system, "Dana", "0549716910","yossi@gmail.com", "DanaBandana", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        Rfa danaRFA=new Rfa(f6,system);
        /*****************/

        /**addRFA*/
        Fan f15= new Fan(system, "yarden", "0549716910","yossi@gmail.com", "yardi", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        Rfa yardenRfa=new Rfa(f15,system);
        /********/

        /**addSystemManager*/
        Fan f11= new Fan(system, "ofer", "0549716910","yossi@gmail.com", "ofer", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        marioSystemManager.addNewSystemManager(f11);
        /*******************/

        /**add Referee*/
        danaRFA.addReferee("s","0526621646","yossi@gmail.com","danaa","ds123456678","ds",MainSystem.birthDateFormat.parse("02-11-1996"));
        /**Tamar to be just a Fan*/
        Fan f9= new Fan(system, "Tamar", "0549716910","yossi@gmail.com", "Tamar", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        /*********************/

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

    /**
     * for input to delete user function
     * get user by name is not an unique field so return a list of
     * matches users.
     *
     * @param name
     * @return list of match users.
     * @codeBy Eden
     */
    public LinkedList<User> getUserByName(String name) {
        List<User> users = MainSystem.getInstance().getUsers();
        LinkedList<User> matches = new LinkedList<User>();
        for (User cur : users) {
            Fan curr = (Fan)cur;
            if (curr.getName().equals(name)) ;
            matches.add(cur);
        }
        return matches;
    }

    /**
     * for input to delete user function
     *User name is an unique field so this function return one user if there is user with
     *
     * @param userName
     * @return
     * @codeBy Eden
     */
    public User getUserByUserName(String userName) {
        List<User> users = MainSystem.getInstance().getUsers();
        LinkedList<User> matches = new LinkedList<User>();
        for (User cur : users) {
            Fan curr = (Fan)cur;
            if (curr.getUserName().equals(userName)) {
                return cur;
            }
        }
        return null;
    }

    public Team getTeambyTeamName(String teamName){
        HashSet<Team> teams = MainSystem.getInstance().getActiveTeams();
        for (Team t : teams) {
            if (t.getName().equals(teamName)) {
                return t;
            }
        }
        return null;
    }
    public List<SystemManager> showAllSystemManagers(){
        return MainSystem.getInstance().getSystemManagers();
    }


    public LinkedList<Rfa> getAllRFA() {
        return MainSystem.getInstance().getRfas();

    }

    public List<SystemManager> getAllSystemManager() {
        return MainSystem.getInstance().getSystemManagers();

    }

    public static void initSystemObjectsAvital() throws Exception {
        MainSystem system = MainSystem.getInstance();
        system.startSystem();
        SystemManager marioSystemManager = system.getSystemManagers().get(0);//there is only one system manager now (the default)
        Team t1 = new Team();
        t1.setName("macabi");
        system.addTeamName("macabi");
        t1.setActive(true);
        system.addActiveTeam(t1);
        /**add field **/
        Field field1=new Field("field1");
        t1.setField(field1);

        Team t2 = new Team();
        t2.setName("hapoel");
        system.addTeamName("hapoel");
        t2.setActive(true);
        system.addActiveTeam(t2);
        /**add field **/
        Field field2=new Field("field2");
        t2.setField(field2);

        /**add Ilan as Team Owner (founder) of t1 ***/
        Fan f1=new Fan(system, "Ilan", "0549716910","yossi@gmail.com", "Ilan", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole ilanTeamOwner=new TeamRole(f1);
        ilanTeamOwner.becomeTeamOwner();
        ilanTeamOwner.getTeamOwner().addNewTeam(t1);
        t1.setFounder(ilanTeamOwner.getTeamOwner());
        t1.addTeamOwner(ilanTeamOwner.getTeamOwner());
        /*********************************************/

        /**add Avi as Team Owner (founder) of t2 ***/
        Fan f7=new Fan(system, "Avi", "0549716910","yossi@gmail.com", "Avi", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole aviTeamOwner=new TeamRole(f7);
        aviTeamOwner.becomeTeamOwner();
        aviTeamOwner.getTeamOwner().addNewTeam(t2);
        t2.addTeamOwner(aviTeamOwner.getTeamOwner());
        t2.setFounder(aviTeamOwner.getTeamOwner());
        /*********************************************/

        /**Ilan subscribe Avi to be t1 team owner*/
        ilanTeamOwner.getTeamOwner().subscribeTeamOwner(aviTeamOwner,t1);
        /*****************************************/
        /**Ilan subscribe moshe to be team Manager with the all permissions**/
        Fan f2=new Fan(system, "Moshe", "0549716910","yossi@gmail.com", "Moshe", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        HashSet<TeamManagerPermissions> permissions=new HashSet<>();
        permissions.add(TeamManagerPermissions.addRemoveEditPlayer);
        permissions.add(TeamManagerPermissions.addRemoveEditTeamOwner);
        permissions.add(TeamManagerPermissions.addRemoveEditCoach);
        permissions.add(TeamManagerPermissions.addRemoveEditField);
        permissions.add(TeamManagerPermissions.addToBudgetControl);
        TeamRole mosheTeamManager = ilanTeamOwner.getTeamOwner().subscribeTeamManager(f2,t1,permissions);
        /**********************************************/


        /**avi subscribe moshe to be team Owner*/
        aviTeamOwner.getTeamOwner().subscribeTeamOwner(mosheTeamManager,t1);
        /***************************************/

        /**avi subscribe david to be team Manager without any permissions**/
        Fan f10 = new Fan(system, "David", "0549716910","yossi@gmail.com", "David", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        HashSet<TeamManagerPermissions> perDavid = new HashSet<>();
        TeamRole davidTeamManager = aviTeamOwner.getTeamOwner().subscribeTeamManager(f10,t2,perDavid);

        /**add 11 players to t1*/
        add11PlayersToTeam(t1,ilanTeamOwner.getTeamOwner(),"d");
        /*********************/

        /**add 22 players to t2*/
        add11PlayersToTeam(t2,aviTeamOwner.getTeamOwner(),"d");
        add11PlayersToTeam(t2,aviTeamOwner.getTeamOwner(),"a");
        /**********************/
        /**Dana to be RFA*/
        Fan f6= new Fan(system, "Dana", "0549716910","yossi@gmail.com", "DanaBandana", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        Rfa danaRFA=new Rfa(f6,system);
        /*****************/

        /**addRFA*/
        Fan f15= new Fan(system, "yarden", "0549716910","yossi@gmail.com", "yardi", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        Rfa yardenRfa=new Rfa(f15,system);
        /********/

        /**add Referee*/
        danaRFA.addReferee("rafi","0526621646","yossi@gmail.com","reffRafi","ds123456678","ds",MainSystem.birthDateFormat.parse("02-11-1996"));
        /**add Referee*/
        danaRFA.addReferee("chen","0526621646","yossi@gmail.com","chen","ds123456678","ds",MainSystem.birthDateFormat.parse("02-11-1996"));
        /**add Referee*/
        danaRFA.addReferee("ben","0526621646","yossi@gmail.com","ben","ds123456678","ds",MainSystem.birthDateFormat.parse("02-11-1996"));

        /**addSystemManager*/
        Fan f11= new Fan(system, "ofer", "0549716910","yossi@gmail.com", "ofer", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        marioSystemManager.addNewSystemManager(f11);
        /**add league*/
        danaRFA.createNewLeague("ligaA",system);

        /**add league*/
        int currSeasonYear=2020;
        RfaController rfaController=new RfaController();
        List<League> allLeagus=MainSystem.getInstance().getLeagues();
        List<SchedulingPolicy>SchedulingPolicies=rfaController.watchSchedulingPolicies();
        List<CalculationPolicy> CalculationPolicies=rfaController.watchCalculationPolicies();
        HashSet<Team> allTeams=MainSystem.getInstance().getActiveTeams();
        List<Referee> allRefsList = MainSystem.getInstance().getAllReferees();
        LinkedHashSet<Referee> allRefs = new LinkedHashSet<Referee>(allRefsList);

        danaRFA.defineSeasonToLeague(SchedulingPolicies.get(0),CalculationPolicies.get(0),currSeasonYear,
                allLeagus.get(0),allTeams,allRefs,true);

        //##

    }



        public static void initSystemObjectsEden() throws Exception {
        MainSystem system=MainSystem.getInstance();
        system.startSystem();
        SystemManager marioSystemManager=system.getSystemManagers().get(0);//there is only one system manager now (the default)
        Team t1=new Team();
        t1.setName("macabi");
        system.addTeamName("macabi");
        t1.setActive(true);
        system.addActiveTeam(t1);

        Team t2=new Team();
        t2.setName("hapoel");
        system.addTeamName("hapoel");
        t2.setActive(true);
        system.addActiveTeam(t2);


        /**add Ilan as Team Owner (founder) of t1 ***/
        Fan f1=new Fan(system, "Ilan", "0549716910","yossi@gmail.com", "Ilan", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole ilanTeamOwner=new TeamRole(f1);
        ilanTeamOwner.becomeTeamOwner();
        ilanTeamOwner.getTeamOwner().addNewTeam(t1);
        t1.setFounder(ilanTeamOwner.getTeamOwner());
        t1.addTeamOwner(ilanTeamOwner.getTeamOwner());
        /*********************************************/


        /**add avi  as team role ***/
        Fan f7=new Fan(system, "Avi", "0549716910","yossi@gmail.com", "Avi", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole aviTeamOwner=new TeamRole(f7);
        aviTeamOwner.becomeTeamOwner();

        /*********************************************/

        /**Ilan subscribe Avi to be t1 team owner*/
        ilanTeamOwner.getTeamOwner().subscribeTeamOwner(aviTeamOwner,t1);
        /*****************************************/

        /**Ilan subscribe moshe to be team Manager with the all permissions**/
        Fan f2=new Fan(system, "Moshe", "0549716910","yossi@gmail.com", "Moshe", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        HashSet<TeamManagerPermissions> permissions=new HashSet<>();
        permissions.add(TeamManagerPermissions.addRemoveEditPlayer);
        permissions.add(TeamManagerPermissions.addRemoveEditTeamOwner);
        permissions.add(TeamManagerPermissions.addRemoveEditCoach);
        permissions.add(TeamManagerPermissions.addRemoveEditField);
        permissions.add(TeamManagerPermissions.addToBudgetControl);
        TeamRole mosheTeamManager = ilanTeamOwner.getTeamOwner().subscribeTeamManager(f2,t1,permissions);
        /**********************************************/


        /**moshe become team owner of t2 (founder)**/
        mosheTeamManager.becomeTeamOwner();
        mosheTeamManager.getTeamOwner().addNewTeam(t2);
        t2.addTeamOwner(mosheTeamManager.getTeamOwner());
        t2.setFounder(mosheTeamManager.getTeamOwner());
        /********************************************/

        /**moshe subscribe avi to be team manager at t2*/
        mosheTeamManager.getTeamOwner().subscribeTeamManager(aviTeamOwner,t2,permissions);
        /******************************************/

        /**avi subscribe ilan to be team owner at t2*/
        aviTeamOwner.getTeamManager().subscribeTeamOwner(ilanTeamOwner,t2);
        /**************************************/

        /**ilan subscibe kobi team owner at t2*/
        Fan f20=new Fan(system, "kobi", "0549716910","yossi@gmail.com", "kobi", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole kobiTeamOwner=new TeamRole(f20);
        kobiTeamOwner.becomeTeamOwner();
        ilanTeamOwner.getTeamOwner().subscribeTeamOwner(kobiTeamOwner,t2);
        /************************************/
        /**avi subscribe moshe to be team Owner at t1 */
        aviTeamOwner.getTeamOwner().subscribeTeamOwner(mosheTeamManager,t1);
        /***************************************/


        /**add 11 players to t1*/
        add11PlayersToTeam(t1,ilanTeamOwner.getTeamOwner(),"d");
        /*********************/

        /**add 22 players to t2*/
        add11PlayersToTeam(t2,aviTeamOwner.getTeamOwner(),"d");
        add11PlayersToTeam(t2,aviTeamOwner.getTeamOwner(),"a");
        /**********************/

        /**Tami to be player without team**/
        Fan f4= new Fan(system, "Tami", "0549716910","yossi@gmail.com", "Tami", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole tamiPlayer=new TeamRole(f4);
        tamiPlayer.becomePlayer();
        /**********************************/

        /**Haim to be a coach at t1 */
        Fan f5= new Fan(system, "Haim", "0549716910","yossi@gmail.com", "Haim", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole haimCoach=new TeamRole(f5);
        haimCoach.becomeCoach();
        haimCoach.getCoach().setCoachTeam(t1);
        t1.setCoach(haimCoach.getCoach());
        /****************************/

        /**ben to be a coach at t2 */
        Fan f8= new Fan(system, "ben", "0549716910","yossi@gmail.com", "Ben", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole benCoach=new TeamRole(f8);
        benCoach.becomeCoach();
        benCoach.getCoach().setCoachTeam(t2);
        t2.setCoach(benCoach.getCoach());
        /****************************/

        /**Dana to be RFA*/
        Fan f6= new Fan(system, "Dana", "0549716910","yossi@gmail.com", "DanaBandana", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        Rfa danaRFA=new Rfa(f6,system);
        /*****************/

        /**addRFA*/
        Fan f10= new Fan(system, "yarden", "0549716910","yossi@gmail.com", "yardi", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        Rfa yardenRfa=new Rfa(f10,system);
        /********/

        /**addSystemManager*/
        Fan f11= new Fan(system, "ofer", "0549716910","yossi@gmail.com", "ofer", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        marioSystemManager.addNewSystemManager(f11);
        /*******************/

        /**add Referee*/
        danaRFA.addReferee("s","0526621646","yossi@gmail.com","sdfdd","ds123456678","ds",MainSystem.birthDateFormat.parse("02-11-1996"));
        /**Tamar to be just a Fan*/
        Fan f9= new Fan(system, "Tamar", "0549716910","yossi@gmail.com", "Tamar", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        /*********************/

        Fan f111= new Fan(MainSystem.getInstance(), "alon", "0549716910","yossi@gmail.com", "alon", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole coach=new TeamRole(f111);
        coach.becomeCoach();
        t1.setCoach(coach.getCoach());
        coach.getCoach().setCoachTeam(t1);

        Fan f= new Fan(MainSystem.getInstance(), "ali", "0549716910","yossi@gmail.com", "ali", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole coach2=new TeamRole(f);
        coach2.becomeCoach();

        Fan ffff= new Fan(MainSystem.getInstance(), "alona", "0549716910","yossi@gmail.com", "alona", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        t1.createPrivatePage();
        ffff.subToPage(t1.getPrivatePage());

        System.out.println("lalala");
    }


    public static void deleteSystem(){
        MainSystem system=MainSystem.getInstance();
        system.setLeagues(new LinkedList<>());
        system.setUsers(new LinkedList<>());
        system.setSeasons(new  LinkedList<>());
        system.setCurrSeason(null);
        system.setActiveTeams(new HashSet<>());
        system.setUserNames(new HashSet<>());
        system.setTeamNames(new HashSet<>());

    }
}
