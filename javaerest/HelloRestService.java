package rest;

import it.example.persistence.DataStore;
import it.example.persistence.MyEntity;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/entity")
public class HelloRestService {

    private DataStore dataStore = new DataStore();

    //restituisce tutte le entità presenti (in formato JSON)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEntities() {
        return Response.ok(dataStore.getData().values()).build();
    }

    //crea una nuova entità
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEntity(MyEntity entity) {
        dataStore.addEntity(entity);
        return Response.status(Response.Status.CREATED).build();
    }

    //aggiorna un'entità esistente in base all'ID specificato
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEntity(@PathParam("id") int id, MyEntity entity) {
        entity.setId(id);
        dataStore.updateEntity(entity);
        return Response.ok().build();
    }

    //elimina l'entità avente l'ID specificato
    @DELETE
    @Path("/{id}")
    public Response deleteEntity(@PathParam("id") int id) {
        dataStore.removeEntity(id);
        return Response.noContent().build();
    }
}
