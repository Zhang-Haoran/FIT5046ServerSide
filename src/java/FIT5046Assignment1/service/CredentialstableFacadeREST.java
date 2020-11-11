/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FIT5046Assignment1.service;

import FIT5046Assignment1.Credentialstable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author 61402
 */
@Stateless
@Path("fit5046assignment1.credentialstable")
public class CredentialstableFacadeREST extends AbstractFacade<Credentialstable> {

    @PersistenceContext(unitName = "FIT5046Assignment1PU")
    private EntityManager em;

    public CredentialstableFacadeREST() {
        super(Credentialstable.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credentialstable entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Credentialstable entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credentialstable find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credentialstable> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credentialstable> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
  @GET
    @Path("findByUsername/{username}")
    @Produces({"application/json"})
    public List<Credentialstable> findByUsername(@PathParam("username") String username)
        {
            Query query = em.createNamedQuery("Credentialstable.findByUsername");
            query.setParameter("username",username);
            return query.getResultList();
        }
    
    @GET
    @Path("findByPasswordhash/{passwordhash}")
    @Produces({"application/json"})
    public List<Credentialstable> findByPasswordhash(@PathParam("passwordhash") String passwordhash)
        {
            Query query = em.createNamedQuery("Credentialstable.findByPasswordhash");
            query.setParameter("passwordhash",passwordhash);
            return query.getResultList();
        }
    
    @GET
    @Path("findBySignupdate/{signupdate}")
    @Produces({"application/json"})
    public List<Credentialstable> findBySignupdate(@PathParam("signupdate") Date signupdate)
        {
            Query query = em.createNamedQuery("Credentialstable.findBySignupdate");
            query.setParameter("signupdate",signupdate);
            return query.getResultList();
        }
    
    @GET
    @Path("findByUsernameAndPassword/{username}/{passwordhash}")
    @Produces({"application/json"})
    public List<Credentialstable> findByUsernameAndPassword(@PathParam("username") String username,@PathParam("passwordhash") String passwordhash)
        {
            Query query = em.createNamedQuery("Credentialstable.findByUsernameAndPassword");
            query.setParameter("username",username);
            query.setParameter("passwordhash",passwordhash);
            return query.getResultList();
        }
    
    @GET
    @Path("findByAllUsername")
    @Produces({"application/json"})
    public JsonArray findByAllUsername()
        {
            Query query = em.createNamedQuery("Credentialstable.findByAllUsername");
            List<Credentialstable> result = query.getResultList();//store the query result into a list
            JsonArrayBuilder usernameJsonArray = Json.createArrayBuilder();  
         //go through each data to fetch the username and store in a list
        for(Credentialstable m: result){
            String username = m.getUsername();
            JsonObject usernameJson = Json.createObjectBuilder().add("username",username).build();
            usernameJsonArray.add(usernameJson);
        }
      
        JsonArray jArrayResult = usernameJsonArray.build();  
        return jArrayResult;
        }
    
}
