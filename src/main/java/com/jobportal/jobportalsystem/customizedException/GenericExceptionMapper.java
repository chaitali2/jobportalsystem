package com.jobportal.jobportalsystem.customizedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception ex) {
        if (ex instanceof BusinessException) {
            LOGGER.info("Custom exception", ex);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDetails("400", ex.getMessage()))
                    .build();
        } else {
            LOGGER.info("Exception", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorDetails("Some error code, 500 or somthing", ex.getMessage()))
                    .build();
        }
    }


}
