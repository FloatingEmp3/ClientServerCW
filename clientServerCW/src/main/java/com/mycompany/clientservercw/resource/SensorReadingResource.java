package com.mycompany.clientservercw.resource;

import com.mycompany.clientservercw.exceptions.SensorUnavailableException;
import com.mycompany.clientservercw.model.Sensor;
import com.mycompany.clientservercw.model.SensorReading;
import com.mycompany.clientservercw.storage.DataStore;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

//Eduardo Lamasanu w2078922

public class SensorReadingResource {

    private String sensorId;
    private static Map<String, List<SensorReading>> readings = new HashMap<>();

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getReadings() {
        return readings.getOrDefault(sensorId, new ArrayList<>());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading) {

        Sensor sensor = DataStore.sensors.get(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (sensor.getStatus().equals("MAINTENANCE")) {
            throw new SensorUnavailableException("Sensor is under maintenance");
        }

        readings.putIfAbsent(sensorId, new ArrayList<>());
        readings.get(sensorId).add(reading);

        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED).entity(reading).build();
    }
}