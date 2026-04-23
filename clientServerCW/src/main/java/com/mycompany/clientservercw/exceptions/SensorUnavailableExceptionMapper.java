package com.mycompany.clientservercw.exceptions;

//Eduardo Lamasanu w2078922

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {

    @Override
    public Response toResponse(SensorUnavailableException ex) {
        return Response.status(Response.Status.FORBIDDEN) // 403
                .entity("{\"error\": \"" + ex.getMessage() + "\"}")
                .build();
    }
}