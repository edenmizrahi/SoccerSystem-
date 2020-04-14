//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.config.Configurator;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashSet;
//
//public class Main {
//
//    private static final Logger LOG = LogManager.getLogger();
//
//    public static void main(String [] args){
//
//        /**LOGGER FORAMT**/
////        LOG.info(String.format("%s - %s", "try", "action?"));
//
//
//
////        Subscription yossi = new Subscription(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
////        Team team = new Team();
////        TeamOwner tOYossi = new TeamOwner(yossi, ms, team);
////        Subscription moshe = new Subscription(ms, "Moshe Hamelech", "0549715678","moshe@gmail.com", "MosheHamelech", "Moshe123" );
////        Subscription david = new Subscription(ms, "David Hamelech", "0541235678","david@gmail.com", "DavidHamelech", "David123" );
//        PrivatePage pp1=new PrivatePage();
//        PrivatePage pp2= new PrivatePage();
//
//        /**CREATE DATE BY FORMAT**/
////        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
////        Date date;
////            try {
////                date = sdf.parse("09-12-1995");
////            } catch (ParseException e) {
////                e.printStackTrace();
////            }
//////    Player player1=new Player(ms, "Or Hamalcha", "0542150912","oralf@gmail.com", "OrHamalcha", "Or1234",date);
//
//        MainSystem ms = new MainSystem();
//        /**CREATE SEASON 2020 IN LEAGUE- curr season**/
//        League league1 = new League("LIGAT HAALUFUT", ms);
//        Season season2020 = new Season(ms,null,2020);
//        ms.setCurrSeason(season2020);
//        Team hapoelBeerSheva= new Team("Hapoel Beer Sheva");
//        Team hapoelKfarSaba= new Team("Hapoel Kfar Saba");
//        Team macabiHaifa= new Team("Macabi Haifa");
//
//        HashSet<Team> teamsForLeague1= new HashSet<Team>();
//        Collections.addAll(teamsForLeague1,hapoelBeerSheva,hapoelKfarSaba,macabiHaifa);
//        season2020.addLeagueWithTeams(league1,teamsForLeague1);
//
//        /**CREATE SEASON 2019 IN LEAGUE**/
//        Season season2019= new Season(ms,null,2019);
//        League league2 = new League("LIGAT HAAL",ms);
//        Team macabiTelAviv= new Team("Macabi Tel Aviv");
//        Team beitarYerushalaim= new Team("Beitar Yerushalaim");
//        Team hapoelRaanana= new Team ("Hapoel Raanana");
//
//        HashSet<Team> teamsForLeague2= new HashSet<Team>();
//        Collections.addAll(teamsForLeague2,beitarYerushalaim,macabiTelAviv,hapoelRaanana);
//        season2019.addLeagueWithTeams(league2,teamsForLeague1);
//
//
//    }
//}