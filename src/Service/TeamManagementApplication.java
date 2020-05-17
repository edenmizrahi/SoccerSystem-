package Service;
import Domain.Controllers.TeamManagementController;
import java.util.HashSet;
import java.util.LinkedList;


public class TeamManagementApplication {
    TeamManagementController teamManagementController = new TeamManagementController();

    /**
     * adi
     * @param userName
     * @param name
     * @throws Exception
     */
    public String requestNewTeam(String userName, String name) {
        return teamManagementController.requestNewTeam(userName, name);
    }

    /**
     * adi
     * @param userName
     * @param teamName
     * @param playerNames
     * @param coachUserName
     * @param nameOfNewField
     * @throws Exception
     */
    public String makeTeamActive(String userName, String teamName, HashSet<String> playerNames , String coachUserName, String nameOfNewField){
       return teamManagementController.makeTeamActive(userName, teamName, playerNames, coachUserName, nameOfNewField);
    }

    public LinkedList<String> getMyApprovedTeams(String userName){
        return teamManagementController.getMyApprovedTeams(userName);
    }

    public void becomeRole(String userName, String role){
        teamManagementController.becomeRole(userName, role);
    }

    //<editor-fold desc="getters">

    public LinkedList<String> getAllTeamRolesThatArentCoachWithTeam(){
        return teamManagementController.getAllTeamRolesThatArentCoachWithTeam();
    }

    public LinkedList<String> getAllTeamRolesThatArentPlayerWithTeam(){
        return teamManagementController.getAllTeamRolesThatArentPlayerWithTeam();
    }

    public LinkedList<String> getMyRoles (String userName){
       return teamManagementController.getMyRoles(userName);
    }


    public LinkedList<String> getWhatICanBecome (String userName){
        return teamManagementController.getWhatICanBecome(userName);
    }
    //</editor-fold>

}
