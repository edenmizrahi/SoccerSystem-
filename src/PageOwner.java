public interface PageOwner {

    public void openPage();

    public void managePage();

    public boolean createPrivatePage();// return true if the page was created. false if they already have a page

    public PrivatePage getPage();
}

