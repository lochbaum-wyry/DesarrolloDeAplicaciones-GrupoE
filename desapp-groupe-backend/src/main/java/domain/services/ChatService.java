package domain.services;

import domain.Chat;
import domain.User;
import domain.repositories.ChatRepository;

public class ChatService {

    ChatRepository chatRepository;


    public void sendMessage(User send,User receive, String content)
    {
        //TODO :creo que es al reves: P send es receive
        Chat chat = getOrAddChatWith(send,receive);
        chat.addMessage(send,content);
        chatRepository.update(chat);
    }

    private Chat getOrAddChatWith(User send,User receive) {
        Chat chat = chatRepository.findByUserId(receive.getId());

        if(chat.equals(null)){
            chat = new Chat(send.getName().toString(),receive,send);
            chatRepository.save(chat);
        }

        return chat;
//        Chat chat ;
//        Optional<Chat> maybeChat = getChatWith(user);
//        if(maybeChat.isPresent())
//        {
//            chat = maybeChat.get();
//        } else
//        {
//            chat = new Chat(this.name.toString(),this,user);
//            addChat(chat);
//            user.addChat(chat);
//        }
//        return chat;
//    }
    }
}
