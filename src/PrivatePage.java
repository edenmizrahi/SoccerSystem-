import java.util.HashSet;

public class PrivatePage {
    int id;
    String[] records;
    PageOwner pageOwner;
    HashSet <Fan> fans;

    public PrivatePage(int id, PageOwner pageOwner) {
        this.id = id;
        this.pageOwner = pageOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getRecords() {
        return records;
    }

    public void setRecords(String[] records) {
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
}
