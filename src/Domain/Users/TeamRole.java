package Domain.Users;

import Domain.MainSystem;
import Domain.Enums.TeamManagerPermissions;
import Domain.LeagueManagment.Team;

import java.util.Date;
import java.util.HashSet;

public class TeamRole extends Fan {

    private Player player;
    private Coach coach;
    private TeamOwner teamOwner;
    private TeamManager teamManager;

    /**OR**/
    public TeamRole(Fan fan) {
        super(fan.system,fan.getName(),fan.getPhoneNumber(),fan.getEmail(),fan.getUserName(),fan.getPassword(), fan.getDateOfBirth());
        this.system.removeUser(fan);
        this.player=null;
        this.coach=null;
        this.teamManager=null;
        this.teamOwner=null;
    }

    /**OR**/
    public TeamRole(MainSystem ms, String name, String phoneNumber, String email, String userName, String password, Date date){
        super(ms,name,phoneNumber,email,userName,password,date);
        this.player=null;
        this.coach=null;
        this.teamManager=null;
        this.teamOwner=null;
    }

    //<editor-fold desc="become functions">
    /**OR**/
    //TODO test - avital V
    public boolean becomePlayer(){
        if(this.player == null){
            player= new Player(this);
            return true;
        }
        return false;
    }
    /**OR**/
    //TODO test - avital V
    public boolean becomeCoach(){
        if (this.coach==null){
            coach= new Coach(this);
            return true;
        }
        return false;
    }

    /**OR**/
    //TODO test - avital V
    public boolean becomeTeamManager(Team team, HashSet<TeamManagerPermissions> pers){
        if(this.teamManager==null){
            teamManager= new TeamManager(this,team,pers);
            return true;
        }
        return false;
    }
    /**OR**/
    //TODO test - avital V
    public boolean becomeTeamOwner(){
        if(this.teamOwner == null){
            teamOwner= new TeamOwner(this);
            return true;
        }
        return false;
    }

    // adi
    //TODO test - avital V
    public boolean deletePlayer(){
        if (player != null){
            player = null;
            return true;
        }
        return false;
    }
    // adi
    //TODO test - avital V
    public boolean deleteCoach(){
        if (coach != null){
            coach = null;
            return true;
        }
        return false;
    }
    // adi
    //TODO test - avital V
    public boolean deleteTeamManager(){
        if (teamManager != null){
            teamManager = null;
            return true;
        }
        return false;
    }
    // adi
    //TODO test - avital V
    public boolean deleteTeamOwner(){
        if (teamOwner != null){
            teamOwner = null;
            return true;
        }
        return false;
    }
    //</editor-fold>


    //<editor-fold desc="check if type">
    /**OR**/
    //TODO test - avital V
    public boolean isPlayer(){
        if(this.player!=null){
            return true;
        }
        return false;
    }
    /**OR**/
    //TODO test - avital V
    public boolean isCoach(){
        if(this.coach!=null){
            return true;
        }
        return false;
    }
    /**OR**/
    //TODO test avital V
    public boolean isTeamManager(){
        if(this.teamManager!=null){
            return true;
        }
        return false;
    }
    /**OR**/
    //TODO test avital V
    public boolean isTeamOwner(){
        if(this.teamOwner!=null){
            return true;
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="getter only">

    /**OR**/
    public Player getPlayer() {
        return player;
    }

    public Coach getCoach() {
        return coach;
    }

    public TeamOwner getTeamOwner() {
        return teamOwner;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }



    //</editor-fold>

}
