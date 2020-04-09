public class Policy {

    private League league;//1
    private Season season;//1

    public Policy(League league, Season season) {
        this.league = league;
        this.season = season;
    }

    private int caculate(){
        return 0;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
