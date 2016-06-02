package domain;


import org.joda.time.DateTime;

public class Post extends Entity
{
    private User publisher;

    private DateTime date;

    private String content;

    public Post() {}

    public Post(User user,DateTime date,String content){
        this.publisher = user;
        this.date = date;
        this.content = content;
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
    
    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }


}
