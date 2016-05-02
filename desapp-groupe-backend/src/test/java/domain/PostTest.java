package domain;
import domain.builders.PostBuilder;
import domain.builders.UserBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PostTest {

    @Test
    public void test_setAndGetDate(){
        DateTime date1 = new DateTime(2,3,4,5,6);
        Post post = PostBuilder.aPost().withDate(date1).build();

        Assert.assertEquals(post.getDate(),date1);

        DateTime date2 = new DateTime(2,3,4,5,6);
        post.setDate(date2);

        Assert.assertEquals(post.getDate(),date2);
    }

    @Test
    public void test_setAndGetContent(){
        Post post = PostBuilder.aPost().withContent("").build();

        Assert.assertEquals(post.getContent(),"");

        post.setContent("fede");

        Assert.assertEquals(post.getContent(),"fede");
    }

    @Test
    public void test_setAndGetPublisher(){
        Post post = PostBuilder.aPost().build();

        Assert.assertEquals(post.getPublisher(),null);

        User user = UserBuilder.aUser().build();
        post.setPublisher(user);

        Assert.assertEquals(post.getPublisher(),user);
    }

}