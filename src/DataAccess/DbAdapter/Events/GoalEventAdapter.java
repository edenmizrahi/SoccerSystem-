package DataAccess.DbAdapter.Events;

import DataAccess.DbAdapter.DbObject;
import Domain.Events.Goal;

import javax.jnlp.IntegrationService;
import java.util.LinkedList;
import java.util.List;

public class GoalEventAdapter implements DbObject<Goal> {

    @Override
    public Goal ToObj(List<String> fields) throws Exception {
        return null;
    }

    @Override
    public List<String> ToDB(Goal obj) {

        LinkedList<String> res=new LinkedList<>();

        res.add(Integer.toString(obj.getId()));
        res.add(obj.getPlayer().getTeamRole().getUserName());

        return res;

    }
}
