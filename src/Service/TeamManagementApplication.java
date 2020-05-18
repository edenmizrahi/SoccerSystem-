package Service;
import Domain.Controllers.TeamManagementController;


public class TeamManagementApplication {
    TeamManagementController teamManagementController = new TeamManagementController();

    /**
     * adi
     * @param userName
     * @param name
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
     */
    public String makeTeamActive(String userName, String teamName, String playerNames , String coachUserName, String nameOfNewField){
       return teamManagementController.makeTeamActive(userName, teamName, playerNames, coachUserName, nameOfNewField);
    }

    public String getMyApprovedTeams(String userName){
        return teamManagementController.getMyApprovedTeams(userName);
    }

    public void becomeRole(String userName, String role){
        teamManagementController.becomeRole(userName, role);
    }

    //<editor-fold desc="getters">

    public String getAllTeamRolesThatArentCoachWithTeam(){
        return teamManagementController.getAllTeamRolesThatArentCoachWithTeam();
    }

    public String getAllTeamRolesThatArentPlayerWithTeam(){
        return teamManagementController.getAllTeamRolesThatArentPlayerWithTeam();
    }

    public String getMyRoles (String userName){
       return teamManagementController.getMyRoles(userName);
    }


    public String getWhatICanBecome (String userName){
        return teamManagementController.getWhatICanBecome(userName);
    }
    //</editor-fold>

}
