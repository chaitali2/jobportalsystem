package com.jobportal.jobportalsystem.customizedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception ex) {
        if (ex instanceof BusinessException) {
            ex.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorProps("400", ex.getMessage()))
                    .build();
        } else {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorProps("Some error code, 500 or somthing", ex.getMessage()))
                    .build();
        }
    }


}
