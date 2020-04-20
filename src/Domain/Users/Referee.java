package Domain.Users;

import Domain.Events.Event;
import Domain.MainSystem;
import Domain.LeagueManagment.Match;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

public class Referee extends Fan {

    private LinkedList<Match> matches;
    private LinkedList<Event> events;
    private String qualification;
    private static final Logger LOG = LogManager.getLogger();

    public Referee(Fan fan, MainSystem ms){
        super(ms, fan.getName(), fan.getPhoneNumber(), fan.getEmail(), fan.getUserName(), fan.getPassword(), fan.getDateOfBirth());
        matches = new LinkedList<>();
        events = new LinkedList<>();
    }

    public Referee(Fan fan, MainSystem ms, String qualification){
        super(ms, fan.getName(), fan.getPhoneNumber(), fan.getEmail(), fan.getUserName(), fan.getPassword(),fan.getDateOfBirth());
        matches = new LinkedList<>();
        events = new LinkedList<>();
        this.qualification = qualification;
        //TODO add permissions
        //this.permissions.add();
    }

    public Referee (MainSystem ms, String name, String phoneNumber, String email, String userName, String password, String qualification, Date date) {
        super(ms,name,phoneNumber,email,userName,password,date);
        matches = new LinkedList<>();
        events = new LinkedList<>();
        this.qualification=qualification;
        //TODO add permissions
        //this.permissions.add();
    }


    public LinkedList<Match> getMatches() {
        return matches;
    }

    public LinkedList<Event> getEvents() {
        return events;
    }

    public String getQualification() { return qualification; }

    /**Ysrden**/
    public void setQualification(String qualification) {
        this.qualification = qualification;
        LOG.info(String.format("%s - %s", this.getUserName(), "set qualification to referee"));
    }


    /**Yarden**/
    // TODO: 16/04/2020 finish implement
    public void addEventsDuringMatch(Match match, Player player, Event event) throws Exception {

       //checking whether this is a game the referee is judging
        if(this.getMatches().contains(match)){

        Date currentDate = new Date(System.currentTimeMillis());
        //check if the game is takes place right now
        if(currentDate.after(match.getStartDate()) && currentDate.before(DateUtils.addMinutes(match.getStartDate(),match.getNumOfMinutes()))) {

        }//referee's match
            else{
                 throw new Exception("You do not have a permission to add events to match you do not judging");
            }
        }//take place right now
        else{
            throw new Exception("You do not have a permission to add events after / before the match");
        }
    }

    /**Yarden**/
    // TODO: 18/04/2020 finisih implement
    public void editEventsSchedule(Match match) throws Exception {
        //just if you are a main referee
        if(match.getMainReferee().equals(this)) {
            HashSet<Event> events = match.getEvents();


            LOG.info(String.format("%s - %s", this.getUserName(), "edit events schedule by main referee"));
        }
        else{
            throw new Exception("You do not have a permission to edit events right now");
        }

    }

    /**Yarden**/
    /**
     *
     * @param match
     * @return
     * @throws Exception
     * @CodeBy Yarden
     */
    public HashSet<Event> createReport(Match match) throws Exception {
        //just if you are a main referee
        if(match.getMainReferee().equals(this)){
            if(this.getMatches().contains(match)){
                LOG.info(String.format("%s - %s", this.getUserName(), "create report by main referee"));
                return match.getEvents();
            }
            else{
                throw new Exception("You do not have a permission to create report to match you do not judging");
            }
        }
        else{
            throw new Exception("You do not have a permission to edit events right now");
        }
    }

    /**Yarden**/
    /**
     * This function show the matches that are still not take place
     * @return LinkedLis</Domain.LeagueManagment.Match>
     */
    public LinkedList<Match> showMatches(){

        LinkedList<Match> matchesToShow = new LinkedList<>();

        for (Match m: matches) {
            if (m.getStartDate().after(new Date(System.currentTimeMillis()))){
                //add just match that still not take place
                matchesToShow.add(m);
//                System.out.println("Date:"+m.getStartDate().toString()+""+"At field:"+m.getField().getNameOfField()+""+"Guest score:"+m.getGuestScore()
//                        +"Home score:"+""+ m.getHomeScore());
            }
        }
        LOG.info(String.format("%s - %s", this.getUserName(), "Show matches to referee"));
        return matchesToShow;
    }

    /**Yarden**/
    public void addMatchToList(Match match){
        this.getMatches().add(match);
    }

    /**Yarden**/
    //<editor-fold desc="events creation">
    public void createGoalEvent(Player p){

    }

    public void createYellowCardEvent(){

    }

    public void createRedCardEvent(){

    }

    public void createOffSideCardEvent(){

    }

    public void createOffenseEvent(){

    }

    public void createInjuryEvent(){

    }

    public void createExtraTimeEvent(){

    }

    //</editor-fold>


}
