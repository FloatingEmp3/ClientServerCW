package com.mycompany.clientservercw.config;

import com.mycompany.clientservercw.exceptions.GlobalExceptionMapper;
import com.mycompany.clientservercw.exceptions.LinkedResourceNotFoundExceptionMapper;
import com.mycompany.clientservercw.exceptions.RoomNotEmptyExceptionMapper;
import com.mycompany.clientservercw.exceptions.SensorUnavailableExceptionMapper;
import com.mycompany.clientservercw.filters.LoggingFilter;
import com.mycompany.clientservercw.resource.DiscoveryResource;
import com.mycompany.clientservercw.resource.RoomResource;
import com.mycompany.clientservercw.resource.SensorResource;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

//Eduardo Lamasanu w2078922

@ApplicationPath("/api/v1")
public class ApiApplication extends ResourceConfig {

    public ApiApplication() {
        register(DiscoveryResource.class);
        register(RoomResource.class);
        register(SensorResource.class);
        register(RoomNotEmptyExceptionMapper.class);
        register(LinkedResourceNotFoundExceptionMapper.class);
        register(SensorUnavailableExceptionMapper.class);
        register(GlobalExceptionMapper.class);
        register(LoggingFilter.class);
    }
}