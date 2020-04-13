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
    public String getRecordsAsString(){
        String ans="";
        for (String s:records) {
            ans=ans+ s +",";
        }
        return ans;
    }

    /**Or**/
    public void addRecords(String record){
        records.add(record);
    }

    /**Or**/
    public void removeRecord(String record){
        records.remove(record);
    }

}
