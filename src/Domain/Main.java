package Domain;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Main {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String [] args) throws Exception {

        /**LOGGER FORAMT**/
//        LOG.info(String.format("%s - %s", "try", "action?"));

        MainSystem ms = MainSystem.getInstance();
        Fan yossi = new Fan(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        Team team1 = new Team();
        Team team2 = new Team();
        Field field1 = new Field("field1");
        Field field2 = new Field("field2");
        HashSet<Event> events = new HashSet<>();
        HashSet<Referee> referees = new HashSet<>();
        //Domain.TeamOwner tOYossi = new Domain.TeamOwner(yossi, ms, team1);
        Fan moshe = new Fan(ms, "Moshe Hamelech", "0549715678","moshe@gmail.com", "MosheHamelech", "Moshe123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        Fan david = new Fan(ms, "David Hamelech", "0541235678","david@gmail.com", "DavidHamelech", "David123" ,MainSystem.birthDateFormat.parse("02-11-1996"));
        Referee mos = new Referee(moshe,ms);
        Match m = new Match(5,5,team1,team2,field1,events,referees,mos,"15-04-2020 21:20:00");
        /**DATE**/
        Date d = m.getStartDate();
        Date noe = new Date(System.currentTimeMillis());
        if(m.getStartDate().after(new Date(System.currentTimeMillis()))){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
        Date d1 = new Date();
        /**Add minutes to specific date**/
        d1 = DateUtils.addMinutes(d,90);
        d1 = DateUtils.addDays(d,1);
        int x;

/**CREATE DATE BY FORMAT**/
        String pattern = "dd-M-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);


//        Subscription yossi = new Subscription(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
//        Domain.Team team = new Domain.Team();
//        Domain.TeamOwner tOYossi = new Domain.TeamOwner(yossi, ms, team);
//        Subscription moshe = new Subscription(ms, "Moshe Hamelech", "0549715678","moshe@gmail.com", "MosheHamelech", "Moshe123" );
//        Subscription david = new Subscription(ms, "David Hamelech", "0541235678","david@gmail.com", "DavidHamelech", "David123" );
        PrivatePage pp1=new PrivatePage();
        PrivatePage pp2= new PrivatePage();

        /**CREATE DATE BY FORMAT**/
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//        Date date;
//            try {
//                date = sdf.parse("09-12-1995");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
////    Domain.Player player1=new Domain.Player(ms, "Or Hamalcha", "0542150912","oralf@gmail.com", "OrHamalcha", "Or1234",date);

        /*
//
//        Domain.MainSystem ms = new Domain.MainSystem();
//        /**CREATE SEASON 2020 IN LEAGUE- curr season**/
//        Domain.League league1 = new Domain.League("LIGAT HAALUFUT", ms);
//        Domain.Season season2020 = new Domain.Season(ms,null,2020);
//        ms.setCurrSeason(season2020);
//        Domain.Team hapoelBeerSheva= new Domain.Team("Hapoel Beer Sheva");
//        Domain.Team hapoelKfarSaba= new Domain.Team("Hapoel Kfar Saba");
//        Domain.Team macabiHaifa= new Domain.Team("Macabi Haifa");
//
//        HashSet<Domain.Team> teamsForLeague1= new HashSet<Domain.Team>();
//        Collections.addAll(teamsForLeague1,hapoelBeerSheva,hapoelKfarSaba,macabiHaifa);
//        season2020.addLeagueWithTeams(league1,teamsForLeague1);
//
//        /**CREATE SEASON 2019 IN LEAGUE**/
//        Domain.Season season2019= new Domain.Season(ms,null,2019);
//        Domain.League league2 = new Domain.League("LIGAT HAAL",ms);
//        Domain.Team macabiTelAviv= new Domain.Team("Macabi Tel Aviv");
//        Domain.Team beitarYerushalaim= new Domain.Team("Beitar Yerushalaim");
//        Domain.Team hapoelRaanana= new Domain.Team ("Hapoel Raanana");
//
//        HashSet<Domain.Team> teamsForLeague2= new HashSet<Domain.Team>();
//        Collections.addAll(teamsForLeague2,beitarYerushalaim,macabiTelAviv,hapoelRaanana);
//        season2019.addLeagueWithTeams(league2,teamsForLeague1);


    }
}