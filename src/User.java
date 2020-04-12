import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class User {
    private MainSystem system;
    private HashSet<Permission> permissions;

    public User(MainSystem ms){
        system = ms;
        permissions = new HashSet<>();
    }


    //or
    //the user chooses to search by name and enters the name in the text box
    public List<PrivatePage> searchByName(String name){
        List<PrivatePage> ans= new LinkedList<>();
        for (User user:system.getUsers()) {
            if(user instanceof PageOwner){
                if(((PageOwner)user).getPage() != null){
                    if(user instanceof Subscription && ((Subscription)user).getName().contains(name)){
                        if(!ans.contains(((PageOwner)user).getPage())){// no duplicates
                            ans.add(((PageOwner)user).getPage());
                        }

                    }
                }
            }
        }
        return ans;
    }

    //or
    //the user chooses to search by key word and enters the words in the text box
    public List<PrivatePage> searchByKeyWord(String keyWord){
        List<PrivatePage> ans= new LinkedList<>();
        PageOwner curr;
        for (User user:system.getUsers()) {
            if(user instanceof PageOwner){// get only the page owners
                curr=(PageOwner)user;
                if( curr.getPage() != null){// check if they have a page
                    if(curr.getPage().getRecordsAsString().contains(keyWord)){
                        if(! ans.contains(curr.getPage())){// no duplicates
                            ans.add(curr.getPage());
                        }

                    }
                }
            }
        }
        return ans;
    }

    //or
    //the user chooses to search by league and chooses the league name from the options
    public List<PrivatePage> searchByLeague(String leagueName){
        List<PrivatePage> ans= new LinkedList<>();
        for (League l:system.getLeagues()) {
            if(l.getName().equals(leagueName)){
                ans.addAll(getPrivatePagefromLeague(l));
            }
        }
        return ans;
    }

    //or
    //the user chooses to search by season and chooses the season year from the options
    public List<PrivatePage> searchBySeason(int seasonYear){
        List<PrivatePage> ans= new LinkedList<>();
        for (Season s:system.getSeasons()) {
            if(s.getYear()== seasonYear){
                for (League l:s.getLeagueWithPolicy().keySet()) {
                    ans.addAll(getPrivatePagefromLeague(l));
                }
            }

        }
        return ans;
    }

    //or
    //the user chooses to search by team and chooses the team name from the options
    public List<PrivatePage> searchByTeamName(String teamName){
        List<PrivatePage> ans= new LinkedList<>();
            for (League l:system.getLeagues()) {
                for (Team t:l.getTeams()) {
                    if(t.getName().equals(teamName)){
                        ans.addAll(getPrivatePagefromTeam(t));
                    }
                }
            }
        return ans;
    }

    //or - private func helps with search
    private List<PrivatePage> getPrivatePagefromLeague(League l){
        List<PrivatePage> ans= new LinkedList<>();
        for (Team t:l.getTeams()) {
            ans.addAll(getPrivatePagefromTeam(t));
        }
        return ans;
    }

    //or - private func helps with search
    private List<PrivatePage> getPrivatePagefromTeam(Team t){
        List<PrivatePage> ans= new LinkedList<>();
            //go through all the players
            for(Player p:t.getPlayers()){
                if(p.getPage() != null){
                    if(! ans .contains(p.getPage())){// no duplicates
                        ans.add(p.getPage());
                    }
                }
            }
            // coach page
            if(t.getCoach().getPage() != null && !ans.contains(t.getCoach().getPage())){
                ans.add(t.getCoach().getPage());
            }
            //team's page
            if(t.getPage() != null && !ans.contains(t.getPage())){
                ans.add(t.getPage());
            }
        return ans;

    }

    //or
    //the user chooses to search all players , returns all the player that have pages
    public List<PrivatePage> searchByPlayer(){
        List<PrivatePage> ans= new LinkedList<>();
        for (User user:system.getUsers()) {
            if(user instanceof  Player){
                if(((Player)user).getPage() != null){
                    if( ! ans.contains(((Player)user).getPage())){
                        ans.add(((Player)user).getPage());
                    }
                }
            }
        }
        return ans;
    }

    //or
    //the user chooses to search all coaches , returns all the coaches who have pages
    public List<PrivatePage> searchByCoach(){
        List<PrivatePage> ans= new LinkedList<>();
        for (User user:system.getUsers()) {
            if(user instanceof  Coach){
                if(((Coach)user).getPage() != null){
                    if( ! ans.contains(((Coach)user).getPage())){
                        ans.add(((Coach)user).getPage());
                    }
                }
            }
        }
        return ans;
    }

    //or
    //the user chooses to search all teams , returns all the teams who have pages
    public List<PrivatePage> searchByTeam(){
        List<PrivatePage> ans= new LinkedList<>();
        for (League l:system.getLeagues()) {
            for (Team t:l.getTeams()) {
                if( t.getPage() != null && ! ans.contains(t.getPage())){
                    ans.add(t.getPage());
                }
            }
        }
        return ans;
    }


}
