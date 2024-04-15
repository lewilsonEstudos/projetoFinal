package org.acme;

import java.net.URI;
import java.util.List;

import extend.motosRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import model.modelMotos;

@Path("/motos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Motos {

	@Inject
	motosRepository motosRepository;
	
    @GET
    public Response getAll() {
      List<modelMotos>  motos = motosRepository.listAll();
      return Response.ok(motos).build();
    }
    
    
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id) {
    	return motosRepository.findByIdOptional(id)
    	.map(motos ->Response.ok(motos).build()).orElse(Response.status(Status.NOT_FOUND).build());
    }
    
    
    @GET
    @Path("name/{name}")
    public Response getByName(@PathParam("name") String name) {
    	return motosRepository.find(name).singleResultOptional()
    	.map(motos ->Response.ok(motos).build()).orElse(Response.status(Status.NOT_FOUND).build());
    }
    
    @GET
    @Path("marca/{marca}")
    public Response getByMarca(@PathParam("marca") String marca) {
    	return motosRepository.find(marca).singleResultOptional()
    	.map(motos ->Response.ok(motos).build()).orElse(Response.status(Status.NOT_FOUND).build());
    }
    
    
    @POST
    @Transactional
    public Response create(modelMotos moto) {
    	motosRepository.persist(moto);
    	  if (motosRepository.isPersistent(moto)) {
    	      return Response.created(URI.create("/motos/" + moto.getId())).build();
    	    }
    	    return Response.status(Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = motosRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Status.BAD_REQUEST).build();
      }

}
