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
import service.ProdutoService;
import vo.ProdutoVO;
import vo.QtdProdVO;
import vo.VendasVO;

/**
 * REST Web Service
 *
 * @author lucas
 */
@Path("produto")
@ApplicationScoped
public class ProdutoResource {
    
    @Inject
    private ProdutoService produtoService;

    @Context
    private UriInfo context;

    public ProdutoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutos() {
        List<ProdutoVO> produtos = this.produtoService.findAll();
        GenericEntity<List<ProdutoVO>> lista = new GenericEntity<List<ProdutoVO>>(produtos){};
        return Response.ok().entity(lista).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("find/{id}")
    public ProdutoVO findProduto(@PathParam("id") Long id) {
        ProdutoVO produto = this.produtoService.find(id);
        return produto;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveProduto(ProdutoVO produtoVO) {
        Long res = this.produtoService.saveProduto(produtoVO);
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
    public Response deleteProd(ProdutoVO produtoVO) {
        this.produtoService.deleteProduto(produtoVO.getId());
        return Response.status(Status.OK).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("edit")
    public Response editProduto(ProdutoVO produtoVO) {
        ProdutoVO res = produtoService.edit(produtoVO);
        if(res != null) {
            return Response.status(Status.OK).build();
        } else {
            return Response.noContent().build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("qtdestoque/att")
    public Response attEstoque(VendasVO vendasVO) {
        VendasVO res = produtoService.attQtdEstoque(vendasVO);
        if(res != null) {
            return Response.status(Status.OK).build();
        } else {
            return Response.noContent().build();
        }
    }
    
    @GET
    @Path("qtdestoque/{id}")
    public QtdProdVO findQtdEstoque(@PathParam("id") int id) {
        ProdutoVO produto = this.produtoService.find(Long.valueOf(id));
        QtdProdVO resVO = new QtdProdVO(produto.getQtdEstoque());
        return resVO;
    }
}
