package domain.builders;

import domain.Post;
import domain.Wall;

import java.util.ArrayList;
import java.util.List;

public class WallBuilder {

    private List<Post> posts = new ArrayList<Post>();

    public static WallBuilder aWall() {
        return new WallBuilder();
    }

    public WallBuilder withPost(Post post) {
        posts.add(post);
        return this;
    }

    public Wall build() {
        Wall wall = new Wall();
        wall.getPosts().addAll(this.posts);
        return wall;
    }
}
