package Service;
import Domain.*;

import java.util.LinkedList;

public class TeamManagementController {

    /**
     * adi
     * @param user
     * @param fanToSubscribe
     * @param team
     * @return
     * @throws Exception
     */
    public TeamRole subscribeTeamOwner(TeamRole user, Fan fanToSubscribe, Team team) throws Exception {
        if (user.isTeamOwner()){
            return user.getTeamOwner().subscribeTeamOwner(fanToSubscribe, team);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            return user.getTeamManager().subscribeTeamOwner(fanToSubscribe, team);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * @param user
     * @return
     */
    public LinkedList<Fan> getAllPossibleSubscribeTeamOwner(TeamRole user){
        LinkedList<Fan> allFans = user.getSystem().getAllFans();
        LinkedList<TeamRole> teamRoles = user.getSystem().getTeamRoles();
        LinkedList<Fan> ans = new LinkedList<>();
        // add all team roles
        for (TeamRole teamRole : teamRoles){
            ans.add(teamRole);
        }
        // add if just a fan
        for(Fan fan : allFans){
            if (!(fan instanceof SystemManager) && !(fan instanceof Rfa) && !(fan instanceof Referee)){
                ans.add(fan);
            }
        }
        return ans;
    }

}
