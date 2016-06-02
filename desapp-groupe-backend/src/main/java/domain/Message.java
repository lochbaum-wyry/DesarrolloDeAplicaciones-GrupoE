package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Message extends Entity
{
    private User sender;
    private List<User> seenBy;
    private DateTime timestamp;
    private String content;

    public Message() {}
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


    public void setContent(String content) {
        this.content = content;
    }

    public void setSeenBy(List<User> seenBy) {
        this.seenBy = seenBy;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }


}
