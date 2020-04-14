public interface PageOwner {


    public void addRecordToPage(String record);

    public void removeRecordFromPage(String record);

    public boolean createPrivatePage();// return true if the page was created. false if they already have a page

    public PrivatePage getPage();
}

