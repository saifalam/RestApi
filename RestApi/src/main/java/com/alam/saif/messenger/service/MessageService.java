package com.alam.saif.messenger.service;

import com.alam.saif.messenger.DataStorage.DataStorage;
import com.alam.saif.messenger.model.Message;

import java.util.*;

/**
 * Created by BS102-Saif on 3/4/2016.
 */
public class MessageService {
    private Map<Long,Message> messages = DataStorage.getMessages();

    public List<Message> getAllMessages() {
        return new ArrayList<Message>(messages.values());
    }

    public Message createMessage(Message message) {
        message.setId(messages.size()+1);
        messages.put(message.getId(),message);
        return message;
    }

    public Message getMessageById(Long id) {
        Message message = new Message();
        for(Message temp : messages.values()) {
            if(id == temp.getId() ) {
                message = temp;
            }
        }
        return message;
    }

    public Message updateMessage(Message message,Long id) {
        message.setId(id);
        for(Message temp : messages.values()) {
            if(id == temp.getId() ) {
                messages.put(id, message);
            }
        }
        return message;
    }

    public void deleteMessage(Long id) {
        for(Message temp : messages.values()) {
            if(id == temp.getId() ) {
                messages.remove(id);
                break;
            }
        }
    }

    public List<Message> getAllMessagesForYear(int year) {
        List<Message> messageList = new ArrayList<Message>();
        Calendar calendar = Calendar.getInstance();
        for(Message message : messages.values()){
            calendar.setTime(message.getCreated());
            if(calendar.get(calendar.YEAR) == year) {
                messageList.add(message);
            }
        }
        return messageList;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        List<Message> messageList = new ArrayList<>(messages.values());

        if(start+size > messages.size()) {
            return new ArrayList<>();
        }
        return messageList.subList(start,start+size);
    }
}
