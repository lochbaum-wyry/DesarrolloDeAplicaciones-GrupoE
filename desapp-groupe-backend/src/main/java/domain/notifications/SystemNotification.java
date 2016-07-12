package domain.notifications;

import domain.Notification;
import domain.NotificationType;
import domain.User;



public class SystemNotification extends Notification {

    private String msg;
    public SystemNotification() {}
    public SystemNotification(User user, String msg) {
        super(user, NotificationType.System.toString());
        this.setMsg(msg);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
