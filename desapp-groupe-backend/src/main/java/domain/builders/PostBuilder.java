package domain.builders;

import domain.Post;
import domain.User;
import org.joda.time.DateTime;


public class PostBuilder {
    private  User publisher;
    private DateTime date;
    private String content;

    public static PostBuilder aPost() {
        return new PostBuilder();
    }

    public PostBuilder withUser(User user) {
        this.publisher = user;
        return this;
    }

    public PostBuilder withDate(DateTime date) {
        this.date = date;
        return this;
    }

    public PostBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public Post build() {
        return new Post(this.publisher,this.date,this.content,null);
    }

}
