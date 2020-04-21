package Domain;

public interface PageOwner {

    public void addRecordToPage(String record) throws Exception;

    public void removeRecordFromPage(String record) throws Exception;

    public boolean createPrivatePage();

    public boolean deletePrivatePage();

    public PrivatePage getPage();

    public String getOwnerName();
}

