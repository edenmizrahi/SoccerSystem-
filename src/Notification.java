public class Notification {
    Object sender;
    Object content;
    boolean isRead;
    public Notification(Object sender, Object content, boolean isRead) {
        this.sender = sender;
        this.content = content;
        this.isRead = isRead;
    }
}
