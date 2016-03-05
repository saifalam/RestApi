package com.alam.saif.messenger.service;

import com.alam.saif.messenger.dataStorage.DataStorage;
import com.alam.saif.messenger.exceptionHandler.ResourceNotFound;
import com.alam.saif.messenger.model.Comment;
import com.alam.saif.messenger.model.ErrorMessage;
import com.alam.saif.messenger.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BS102-Saif on 3/4/2016.
 */
public class CommentService {
    private Map<Long, Message> messages = DataStorage.getMessages();

    public List<Comment> getAllComments(long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());
    }


    public Comment getComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);

        if (comment == null) {
            throw new ResourceNotFound("Comment id: " + commentId + " for Message id: "+ messageId+ " not found" );
        }
        return comment;
    }

    public Comment createComment(Comment comment, long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size()+1);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment( long messageId,long commentId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();

        for(Comment temp : comments.values()) {
            if(commentId == temp.getId()) {
                comment.setId(commentId);
                comments.put(commentId, comment);
            }
        }
        return comment;
    }

    public void deleteComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        for(Comment comment : comments.values()) {
            if(commentId == comment.getId()) {
                comments.remove(commentId);
                break;
            }
        }
    }

}
