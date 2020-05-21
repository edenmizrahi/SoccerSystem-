package DataAccess.DbAdapter;

import Domain.LeagueManagment.Scheduling.SchedulingPolicy;

import java.util.List;

public class SchedulingAdapter implements DbObject<SchedulingPolicy>{
    @Override
    public SchedulingPolicy ToObj(List<String> fields) throws Exception {
        return null;
    }

    @Override
    public List<String> ToDB(SchedulingPolicy obj) {
        return null;
    }
}
