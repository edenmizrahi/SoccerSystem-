package Domain.Notifications;

public class Notification {
    private Object sender;
    private Object content;
    private boolean isRead;

     public Notification(Object sender, Object content, boolean isRead) {
        this.sender = sender;
        this.content = content;
        this.isRead = isRead;
    }

    public Object getSender() {
        return sender;
    }

    public void setSender(Object sender) {
        this.sender = sender;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
