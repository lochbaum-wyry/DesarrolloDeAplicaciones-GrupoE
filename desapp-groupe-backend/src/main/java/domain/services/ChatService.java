package domain.services;

import domain.Chat;
import domain.User;
import domain.repositories.ChatRepository;
import domain.repositories.UserRepository;

import java.util.List;

public class ChatService {

    ChatRepository chatRepository;
    UserRepository userRepository;


    public void sendMessage(User send,User receive, String content)
    {
        //TODO :creo que es al reves: P send es receive
        Chat chat = getOrAddChatWith(send,receive);
        chat.addMessage(send,content);
        chatRepository.update(chat);
    }

    public Chat getOrAddChatWith(User send,User receive) {
        Chat chat = chatRepository.findChatByUsers(receive.getId(),send.getId());

        if(chat.equals(null)){
            chat = new Chat(send.getName().toString(),receive,send);

            send.addChat(chat);
            userRepository.update(send);

            receive.addChat(chat);
            userRepository.update(receive);

            chatRepository.save(chat);
        }

        return chat;
    }

    public List<Chat> chatsUser(User user){
        return chatRepository.findByUserId(user.getId());
    }


    public ChatRepository getChatRepository() {
        return chatRepository;
    }

}
