package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private User sender;
    private List<User> seenBy;
    private DateTime timestamp;
    private String content;

    public Message(User user, String content, DateTime timestamp){
        this.sender = user;
        this.content = content;
        this.timestamp = timestamp;
        this.seenBy = new ArrayList<User>();
    }

    public void markSeenBy(User user){
        seenBy.add(user);
    }

    public Boolean isSeenBy(User user){
        return seenBy.stream().anyMatch(user1 -> user1.equals(user));
    }

    public String getContent() {
        return content;
    }

    public List<User> getSeenBy() {
        return seenBy;
    }

    public User getSender() {
        return sender;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }



}
