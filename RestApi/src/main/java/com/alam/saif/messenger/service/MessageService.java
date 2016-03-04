package com.alam.saif.messenger.service;

import com.alam.saif.messenger.model.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BS102-Saif on 3/4/2016.
 */
public class MessageService {
    public List<Message> getAllMessages() {
        List<Message> messageList = new ArrayList<>();
        Message message1 = new Message(1L,"message 1", new Date(),"Saif Alam");
        Message message2 = new Message(2L,"message 2", new Date(),"Saif Alam");
        messageList.add(message1);
        messageList.add(message2);
        return messageList;
    }
}
