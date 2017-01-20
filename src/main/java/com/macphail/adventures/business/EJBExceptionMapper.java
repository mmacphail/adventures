package com.macphail.adventures.business;

import com.macphail.adventures.business.game.service.exceptions.AdventureAlreadyEndedException;

import javax.ejb.EJBException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by alexandre.fruchaud on 08/01/2017.
 */
@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException exception) {
        Throwable cause = exception.getCause();
        Response unkownError = Response.serverError().header("reason", exception.toString()).build();
        if(cause == null) {
            return unkownError;
        }
        if(cause instanceof EntityNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .header("reason", "Not found")
                    .build();
        }
        if(cause instanceof OptimisticLockException) {
            return Response.status(Response.Status.CONFLICT)
                    .header("reason", "Conflict occurend")
                    .build();
        }
        if(cause instanceof AdventureAlreadyEndedException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("reason", "The adventure already ended")
                    .build();
        }

        return unkownError;
    }
}
