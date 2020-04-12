import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrivatePage {
    private List<String> records;
    private PageOwner pageOwner;
    private HashSet <Fan> fans;

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

    //OR
    public String getRecordsAsString(){
        String ans="";
        for (String s:records) {
            ans=ans+ s +",";
        }
        return ans;
    }
}
