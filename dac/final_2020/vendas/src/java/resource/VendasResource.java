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
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import service.VendasService;
import vo.QtdProdVO;
import vo.VendasVO;

/**
 * REST Web Service
 *
 * @author lucas
 */
@Path("vendas")
@ApplicationScoped
public class VendasResource {
    
    @Inject
    private VendasService vendasService;

    @Context
    private UriInfo context;

    public VendasResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVendas() {
        List<VendasVO> vendas = this.vendasService.findAll();
        GenericEntity<List<VendasVO>> lista = new GenericEntity<List<VendasVO>>(vendas){};
        return Response.ok().entity(lista).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveVenda(VendasVO vendasVO) {
        Long res = this.vendasService.saveVenda(vendasVO);
        if(res > 0) {
            return Response.status(Status.CREATED).build();
        } else {
            return Response.noContent().build();
        }
    }
    
    @GET
    @Path("qtdvendas/{id}")
    public QtdProdVO findQtdVendas(@PathParam("id") int id) {
        int res = this.vendasService.getQtdVendas(id);
        QtdProdVO resVO = new QtdProdVO(res);
        return resVO;
    }
}
