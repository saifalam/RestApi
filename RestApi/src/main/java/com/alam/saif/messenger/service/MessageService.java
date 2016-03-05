package com.alam.saif.messenger.service;

import com.alam.saif.messenger.dataStorage.DataStorage;
import com.alam.saif.messenger.exceptionHandler.ResourceNotFound;
import com.alam.saif.messenger.model.ErrorMessage;
import com.alam.saif.messenger.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
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
        Message message = null;
        for(Message temp : messages.values()) {
            if(id == temp.getId() ) {
                message = temp;
            }
        }

        if (message == null) {
            //throw new ResourceNotFound("Message id: " + id + " not found" );

            /*NotFoundException is a subClass of WebApplicationException provided by JAX-RX
              I do appreciate to use Custom ExceptionMapper to handle specific Exception
            */
            ErrorMessage errorMessage = new ErrorMessage("Message id " + id + " not found ...", 404);
            Response response = Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
            throw new NotFoundException(response);
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
