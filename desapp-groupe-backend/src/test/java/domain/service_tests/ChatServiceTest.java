package domain.service_tests;

import domain.User;
import domain.repositories.ChatRepository;
import domain.repositories.UserRepository;
import domain.services.ChatService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring-persistence-context.xml")
@Transactional
public class ChatServiceTest {

    @Test
    public void test_sendMessage(){
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;
        UserRepository userRepository = (UserRepository) factory.getBean("persistence.userrepository");

        User user = new User("fede","fede","fede","fede");
        User user2 = new User("fede2","fede2","fede2","fede2");

        ChatRepository chatRepository = (ChatRepository) factory.getBean("persistence.chatrepository");

        ChatService chatService = new ChatService(chatRepository,userRepository);

        userRepository.save(user);
        userRepository.save(user2);

        chatService.sendMessage(user,user2,"como estas ?");

        Assert.assertEquals(user2.getChats().size(),1);
    }

    @Test
    public void test_chatsUser(){
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-persistence-context.xml");
        BeanFactory factory = context;
        UserRepository userRepository = (UserRepository) factory.getBean("persistence.userrepository");

        User user = new User("fede","fede","fede","fede");
        User user2 = new User("fede2","fede2","fede2","fede2");

        ChatRepository chatRepository = (ChatRepository) factory.getBean("persistence.chatrepository");

        ChatService chatService = new ChatService(chatRepository,userRepository);

        userRepository.save(user);
        userRepository.save(user2);

        chatService.sendMessage(user,user2,"como estas ?");
        chatService.sendMessage(user2,user,"todo bien, vos ?");
        chatService.sendMessage(user,user2,"aca andamos");

        Assert.assertEquals(chatService.chatsUser(user).get(0).getMessages().size(),3);
        Assert.assertEquals(chatService.chatsUser(user).size(),1);
    }

}
