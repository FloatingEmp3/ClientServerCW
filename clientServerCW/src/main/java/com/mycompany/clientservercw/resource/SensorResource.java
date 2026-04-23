package com.mycompany.clientservercw.resource;

//Eduardo Lamasanu w2078922

import com.mycompany.clientservercw.exceptions.LinkedResourceNotFoundException;
import com.mycompany.clientservercw.model.Room;
import com.mycompany.clientservercw.model.Sensor;
import com.mycompany.clientservercw.storage.DataStore;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collection;

@Path("/sensors")
public class SensorResource {

    @GET
@Produces(MediaType.APPLICATION_JSON)
public Collection<Sensor> getSensors(@QueryParam("type") String type) {

    if (type == null) {
        return DataStore.sensors.values();
    }

    return DataStore.sensors.values().stream()
            .filter(sensor -> sensor.getType().equalsIgnoreCase(type))
            .toList();
}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSensor(Sensor sensor) {
        Room room = DataStore.rooms.get(sensor.getRoomId());

        if (room == null) {
    throw new LinkedResourceNotFoundException("Room does not exist.");
}

        DataStore.sensors.put(sensor.getId(), sensor);
        room.getSensorIds().add(sensor.getId());

        return Response.status(Response.Status.CREATED).entity(sensor).build();
    }
    
    @Path("/{id}/readings")
public SensorReadingResource getReadingResource(@PathParam("id") String id) {
    return new SensorReadingResource(id);
}
}