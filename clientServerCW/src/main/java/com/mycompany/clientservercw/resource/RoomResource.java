package com.mycompany.clientservercw.resource;

import com.mycompany.clientservercw.exceptions.RoomNotEmptyException;
import com.mycompany.clientservercw.model.Room;
import com.mycompany.clientservercw.storage.DataStore;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collection;

//Eduardo Lamasanu w2078922

@Path("/rooms")
public class RoomResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Room> getRooms() {
        return DataStore.rooms.values();
    }
    
    @POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response createRoom(Room room) {
    DataStore.rooms.put(room.getId(), room);
    return Response.status(Response.Status.CREATED).entity(room).build();
}

@GET
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response getRoom(@PathParam("id") String id) {
    Room room = DataStore.rooms.get(id);

    if (room == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    return Response.ok(room).build();
}

@DELETE
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response deleteRoom(@PathParam("id") String id) {
    Room room = DataStore.rooms.get(id);

    if (room == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    if (room.getSensorIds() != null && !room.getSensorIds().isEmpty()) {
        throw new RoomNotEmptyException("Room cannot be deleted because it still has sensors assigned.");
    }

    DataStore.rooms.remove(id);
    return Response.ok("Room deleted successfully").build();
}
}