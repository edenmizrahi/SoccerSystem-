package Domain;

import Domain.Users.Fan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrivatePage {
    private List<String> records;
    private PageOwner pageOwner;
    private HashSet <Fan> fans;
    private static final Logger LOG = LogManager.getLogger();

    public PrivatePage( PageOwner pageOwner) {
        this.pageOwner = pageOwner;
        this.fans=new HashSet<>();
        this.records= new ArrayList<>();
    }

    public PrivatePage() {
        this.pageOwner=null;
        this.fans=new HashSet<>();
        this.records= new ArrayList<>();
    }


    public List<String> getRecords() {
        return records;
    }

    public void setRecords(List<String> records) {
        this.records = records;
    }

    public PageOwner getPageOwner() {
        return pageOwner;
    }

    public void setPageOwner(PageOwner pageOwner) {
        this.pageOwner = pageOwner;
    }

    public HashSet<Fan> getFans() {
        return fans;
    }

    public void setFans(HashSet<Fan> fans) {
        this.fans = fans;
    }

    /**Or**/
    //TODO test
    public String getRecordsAsString(){
        String ans="";
        for (String s:records) {
            ans=ans+ s +",";
        }
        return ans;
    }

    /**Or**/
    //TODO test
    public void addRecords(String record) throws Exception {
        if(record==null){
            throw new Exception("record is null");
        }
        if(record.length()==0){
            throw new Exception("record empty");
        }
        records.add(record);
        LOG.info(String.format("%s - %s", pageOwner, "added record to page"));
    }

    /**Or**/
    //TODO test
    public void removeRecord(String record) throws Exception {
        if(record==null){
            throw new Exception("record in null");
        }
        if(record.length()==0){
            throw new Exception("record empty");
        }
        records.remove(record);
        LOG.info(String.format("%s - %s", pageOwner, "added record to page"));
    }

    /**Or**/
    //TODO test
    public void addFan(Fan fan) throws Exception {
        if(fan==null){
            throw new Exception("fan null");
        }
        fans.add(fan);
        LOG.info(String.format("%s - %s", pageOwner, "add fan to page, fane username: "+fan.getUserName()));
    }

    /**Or**/
    public void deletePage(){

    }

}
