package com.jobportal.jobportalsystem.customizedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionMapper.class);


    @Override
    public Response toResponse(ValidationException ex) {
            LOGGER.info("ValidationException ");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(prepareMessage((ValidationException) ex))
                    .type("text/plain")
                    .build();
    }

    private String prepareMessage(ValidationException exception) {
        String msg = "";
        for (ConstraintViolation<?> cv : ((ConstraintViolationException) exception).getConstraintViolations()) {
            msg += cv.getMessage() + "\n";
            LOGGER.info("cv.getMessage()=="+cv.getMessage());
//            msg += cv.getPropertyPath() + " " + cv.getMessage() + "\n";
        }
        return msg;
    }
}
