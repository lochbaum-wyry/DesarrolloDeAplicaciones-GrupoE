package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Wall {
    private List<Post> posts;

    public Wall(){
        this.posts = new ArrayList<Post>();
    }

    public List<Post> getPostsIn(DateTime fromDate){
        return posts.stream().filter(post -> post.getDate().isAfter(fromDate)).collect(Collectors.toList());
    }

    public List<Post> getPosts() {
        return posts;
    }
}