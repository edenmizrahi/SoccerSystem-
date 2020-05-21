package DataAccess.DbAdapter;

import Domain.LeagueManagment.Calculation.CalculationPolicy;

import java.util.List;

public class CalculationAdapter implements DbObject<CalculationPolicy>{
    @Override
    public CalculationPolicy ToObj(List<String> fields) throws Exception {
        return null;
    }

    @Override
    public List<String> ToDB(CalculationPolicy obj) {
        return null;
    }
}
