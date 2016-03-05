package com.alam.saif.messenger.exceptionHandler;

import com.alam.saif.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by saif on 3/6/16.
 */
@Provider
public class GenericExceptionMapper  implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),500 );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
    }
}

