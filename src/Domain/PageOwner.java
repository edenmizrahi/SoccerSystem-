package Domain;

public interface PageOwner {


    public void addRecordToPage(String record) throws Exception;

    public void removeRecordFromPage(String record) throws Exception;

    public boolean createPrivatePage();// return true if the page was created. false if they already have a page

    public PrivatePage getPage();
}

