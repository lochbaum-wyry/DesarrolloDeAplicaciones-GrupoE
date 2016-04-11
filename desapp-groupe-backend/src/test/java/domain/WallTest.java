package domain;

import domain.builders.PostBuilder;
import domain.builders.UserBuilder;
import domain.builders.WallBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class WallTest {

    @Test
    public void testGetPostsIn(){

        User user = UserBuilder.aUser().withName("Mario").build();

        Post post1 = PostBuilder.aPost().withUser(user).withDate(new DateTime(3/2/3)).withContent("holaaa").build();
        Post post2 = PostBuilder.aPost().withUser(user).withDate(new DateTime(5/2/3)).withContent("capo!!!").build();
        Post post3 = PostBuilder.aPost().withUser(user).withDate(new DateTime(6/2/3)).withContent("Gil").build();

        Wall wall = WallBuilder.aWall().withPost(post1).withPost(post2).withPost(post3).build();

        Integer received = wall.getPostsIn(new DateTime(7/5/7)).size();
        Integer expected = 1;

        Assert.assertEquals(received,expected);
    }
}
