import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Notifications.Notification;
import Domain.Users.Rfa;
import Domain.Users.TeamRole;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashSet;

public class TeamTeamOwnerRfa {

    MainSystem ms = MainSystem.getInstance();
    Rfa nadav = new Rfa(ms,"nadav","052","nadav@","nadavS", "nadav123",MainSystem.birthDateFormat.parse("06-07-1992"));
    TeamRole teamOwner3 = new TeamRole(ms,"michael","0522150912","teamO3@gmail.com","owner444","owner123",MainSystem.birthDateFormat.parse("09-12-1995"));

    public TeamTeamOwnerRfa() throws ParseException {
    }

    @Test
    public void requestAndAnswerForOpenTeamTest(){

            teamOwner3.becomeTeamOwner();
            try {
                teamOwner3.getTeamOwner().requestNewTeam("beer sheva");
            }
            catch (Exception e){
                Assert.assertTrue(teamOwner3.getTeamOwner().checkIfTeamInRequestTeam("beer sheva"));
                Assert.assertTrue(teamOwner3.getTeamOwner().getRequestedTeams().size()==1);
            }
//            nadav.answerRequest(team,true);
//
//            Assert.assertTrue(teamOwner3.getTeamOwner().getApprovedTeams().contains(team));
//            Assert.assertTrue(!nadav.getTeamRequests().contains(team));
//
//            Team tApproved = teamOwner3.getTeamOwner().getApprovedTeams().get(0);
//            if(tApproved.equals(team)) {
//                HashSet<Notification> notifications = teamOwner3.getTeamOwner().getNotificationsList();
//                for (Notification n : notifications) {
//                    if (n.getSender() instanceof Team) {
//                        if (((Team) n.getSender()).getName().equals(tApproved.getName())) {
//                            teamOwner3.getTeamOwner().MarkAsReadNotification(n);
//                            teamOwner3.getTeamOwner().getNotificationsList().remove(n);
//                            break;
//                        }
//                    }
//                }
//            }

    }
}
