package com.mycompany.clientservercw.exceptions;

//Eduardo Lamasanu w2078922

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {

    @Override
    public Response toResponse(RoomNotEmptyException ex) {
        return Response.status(Response.Status.CONFLICT) // 409
                .entity("{\"error\": \"" + ex.getMessage() + "\"}")
                .build();
    }
}