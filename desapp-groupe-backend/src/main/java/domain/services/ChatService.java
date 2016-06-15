package domain.services;

import domain.Chat;
import domain.User;
import domain.repositories.ChatRepository;
import domain.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ChatService {

    ChatRepository chatRepository;
    UserRepository userRepository;

    public ChatService(){}

    public ChatService(ChatRepository chatRepository,UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void sendMessage(Integer senderId,Integer chatId, String content)
    {
        User send = userRepository.findById(senderId);

        Chat chat = chatRepository.findById(chatId);
        chat.addMessage(send,content);

        chatRepository.update(chat);
        userRepository.update(send);
    }

    public List<Chat> chatsUser(User user){
        return user.getChats();
    }


    public ChatRepository getChatRepository() {
        return chatRepository;
    }

    @Transactional
    public Chat getChatByUser(Integer userId, Integer userChatId) {
        User user = userRepository.findById(userId);
        User userChat = userRepository.findById(userChatId);

        Chat chat = user.getOrAddChatWith(userChat);

        chatRepository.update(chat);
        userRepository.update(user);
        userRepository.update(userChat);

        return chatRepository.findById(chat.getId());
    }
}
