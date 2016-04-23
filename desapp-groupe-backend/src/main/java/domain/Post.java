package domain;

import org.joda.time.DateTime;

public class Post {
    private User publisher;
    private DateTime date;
    private String content;
    private int id;

    public Post(User user,DateTime date,String content){
        this.publisher = user;
        this.date = date;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public DateTime getDate() {
        return date;
    }

    public User getPublisher() {
        return publisher;
    }

}
