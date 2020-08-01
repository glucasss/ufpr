/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import service.ClientService;
import vo.ClientVO;

/**
 * REST Web Service
 *
 * @author lucas
 */
@Path("client")
@ApplicationScoped
public class ClientResource {
    
    @Inject
    private ClientService clientService;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClientResource
     */
    public ClientResource() {
    }

    /**
     * Retrieves representation of an instance of resource.ClientResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients() {
        List<ClientVO> clientes = this.clientService.findAll();
        GenericEntity<List<ClientVO>> lista = new GenericEntity<List<ClientVO>>(clientes){};
        return Response.ok().entity(lista).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("find/{id}")
    public ClientVO findClient(@PathParam("id") Long id) {
        ClientVO cliente = this.clientService.find(id);
        return cliente;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveClient(ClientVO clientVO) {
        Long res = this.clientService.saveClient(clientVO);
        if(res > 0) {
            return Response.status(Status.CREATED).build();
        } else {
            return Response.noContent().build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("del")
    public Response deleteClient(ClientVO clientVO) {
        this.clientService.deleteClient(clientVO.getId());
        return Response.status(Status.OK).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("edit")
    public Response editClient(ClientVO clientVO) {
        ClientVO res = clientService.edit(clientVO);
        if(res != null) {
            return Response.status(Status.OK).build();
        } else {
            return Response.noContent().build();
        }
    }
}
