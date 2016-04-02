package domain.builders;

import domain.Message;
import domain.User;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class MessageBuilder {
    private User sender;
    private List<User> seenBy = new ArrayList<User>();
    private DateTime timestamp;
    private String content;

    public static MessageBuilder aMessage() {
        return new MessageBuilder();
    }

    public MessageBuilder withSender(User user) {
        this.sender = user;
        return this;
    }


    public MessageBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public MessageBuilder withDate(DateTime dateTime) {
        this.timestamp = dateTime;
        return this;
    }

    public Message build() {
        return new Message(sender,content,timestamp);
    }
}
