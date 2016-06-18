package domain.service_tests;

import domain.Chat;
import domain.User;
import domain.repositories.UserRepository;
import domain.services.ChatService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


public class ChatServiceTest extends AbstractServiceTest{

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ChatService chatService;

    @Test
    public void test_sendMessage(){
        User user = new User("fede","fede","fede","fede");
        User user2 = new User("fede2","fede2","fede2","fede2");

        userRepository.save(user);
        userRepository.save(user2);

        Chat chat = chatService.getChatByUser(user.getId(),user2.getId());

        chatService.sendMessage(user.getId(),chat.getId(),"hola como estas");
        Assert.assertEquals(user2.getChats().size(),1);
    }

    @Test
    public void test_chatsUser(){
        User user = new User("fede","fede","fede","fede");
        User user2 = new User("fede2","fede2","fede2","fede2");

        userRepository.save(user);
        userRepository.save(user2);

        Chat chat = chatService.getChatByUser(user.getId(),user2.getId());

        chatService.sendMessage(user.getId(),chat.getId(),"hola como estas");


        Chat chat2 = chatService.getChatByUser(user2.getId(),user.getId());

        chatService.sendMessage(user.getId(),chat.getId(),"todo bien vos?");

        Chat chat3 = chatService.getChatByUser(user.getId(),user2.getId());

        chatService.sendMessage(user.getId(),chat.getId(),"aca andamos");


        Assert.assertEquals(chatService.chatsUser(user).get(0).getMessages().size(),3);
        Assert.assertEquals(chatService.chatsUser(user).size(),1);
    }
}
