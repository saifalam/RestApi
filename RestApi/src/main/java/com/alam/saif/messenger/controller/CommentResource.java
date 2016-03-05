package com.alam.saif.messenger.controller;

import com.alam.saif.messenger.model.Comment;
import com.alam.saif.messenger.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by BS102-Saif on 3/4/2016.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
        return commentService.getAllComments(messageId);
    }


    @POST
    public Response createComment(@PathParam("messageId") long messageId, Comment comment) {
        Comment responseComment = commentService.createComment(comment, messageId);
        return Response.status(Response.Status.CREATED)
                .entity(responseComment)
                .build();
    }

    @PUT
    public Response updateComment(@PathParam("messageId") long messageId,
                                  @PathParam("commentId") long commentId,
                                  Comment comment) {
        Comment responseComment = commentService.updateComment(messageId, commentId, comment);
        return Response.status(Response.Status.OK)
                .entity(responseComment)
                .build();
    }


    @GET
    @Path("/{commentId}")
    public Response getComment(@PathParam("messageId") long messageId,
                               @PathParam("commentId") long commentId) {
        Comment comment = commentService.getComment(messageId, commentId);
        return Response.status(Response.Status.OK)
                       .entity(comment)
                       .build();
    }

    @GET
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") long messageId,
                              @PathParam("commentId") long commentId) {
        commentService.deleteComment(messageId, commentId);
    }

}
