package com.alam.saif.messenger.controller;

import com.alam.saif.messenger.controller.beans.FilteringBean;
import com.alam.saif.messenger.model.Message;
import com.alam.saif.messenger.service.MessageService;
import java.net.URI;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Root resource (exposed at "messages" path)
 */
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService messageService = new MessageService();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return List of Message that will be returned as a application/json response.
     */
    @GET
    public List<Message> getMessages(@BeanParam FilteringBean bean) {

        if(bean.getYear() > 0) {
            return messageService.getAllMessagesForYear(bean.getYear());
        }
        if(bean.getmYear() > 0) {
            return messageService.getAllMessagesForYear(bean.getmYear());
        }

        if(bean.getStart() >= 0 && bean.getSize() > 0) {
            return messageService.getAllMessagesPaginated(bean.getStart(), bean.getSize());
        }

        if(bean.getmStart() >= 0 && bean.getmSize() > 0) {
            return messageService.getAllMessagesPaginated(bean.getmStart(), bean.getmSize());
        }
        return messageService.getAllMessages();
    }


    /**
     *
     * @param uriInfo URI Information
     * @param message Object Of Message Class
     * @return Response with Message Entity
     */
    @POST
    public Response createMessage(@Context UriInfo uriInfo, Message message) {
        Message returnMessage = messageService.createMessage(message);
        String messageId = String.valueOf(returnMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(messageId).build();

        return Response.created(uri)
                .entity(message)
                .build();
    }


    /**
     *
     * @param id Specific Message ID
     * @return Response with Message Entity
     */
    @GET
    @Path("/{messageId}")
    public Response getMessage(@PathParam("messageId")Long id) {
        Message message = messageService.getMessageById(id);
        return Response.status(Response.Status.OK)
                .entity(message)
                .build();
    }


    /**
     *
     * @param id Specific Message ID
     * @param message Object Of Message Class
     * @return Response with Message Entity
     */
    @PUT
    @Path("/{messageId}")
    public Response updateMessage(@PathParam("messageId")Long id, Message message) {
        Message returnMessage = new Message();
        if(id > 0 && message != null) {
            returnMessage = messageService.updateMessage(message,id);
        }
        return Response.status(Response.Status.ACCEPTED)
                .entity(returnMessage)
                .build();
    }


    /**
     *
     * @param id Specific Message ID
     */
    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId")Long id) {
        messageService.deleteMessage(id);

    }


    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }
}
