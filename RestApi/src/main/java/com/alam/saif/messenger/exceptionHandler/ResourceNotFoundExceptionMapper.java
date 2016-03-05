package com.alam.saif.messenger.exceptionHandler;

import com.alam.saif.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by saif on 3/6/16.
 */
@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFound> {
    @Override
    public Response toResponse(ResourceNotFound ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),400 );
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(errorMessage)
                       .build();
    }
}
