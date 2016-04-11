package domain;
import domain.builders.PostBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PostTest {
    @Test
    public void test_getContent(){
        DateTime date = new DateTime();
        User user = Mockito.mock(User.class);
        Post post = PostBuilder.aPost().withContent("unPost").withDate(date).withUser(user).build();

        Assert.assertEquals(post.getContent(),"unPost");
    }

    @Test
    public void test_getPublisher(){
        DateTime date = new DateTime();
        User user = Mockito.mock(User.class);
        Post post = PostBuilder.aPost().withContent("unPost").withDate(date).withUser(user).build();

        Assert.assertEquals(post.getPublisher(),user);
    }

}
