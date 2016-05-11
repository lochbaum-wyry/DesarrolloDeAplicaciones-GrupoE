package domain.services;

import domain.Chat;
import domain.User;
import domain.repositories.ChatRepository;
import domain.repositories.UserRepository;

import java.util.List;

public class ChatService {

    ChatRepository chatRepository;
    UserRepository userRepository;

    public ChatService(){}

    public ChatService(ChatRepository chatRepository,UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }


    public void sendMessage(User send,User receive, String content)
    {
        Chat chat = send.getOrAddChatWith(receive);
        chat.addMessage(send,content);

        chatRepository.update(chat);
        userRepository.update(send);
        userRepository.update(receive);
    }

    public List<Chat> chatsUser(User user){
        return user.getChats();
    }


    public ChatRepository getChatRepository() {
        return chatRepository;
    }

}
