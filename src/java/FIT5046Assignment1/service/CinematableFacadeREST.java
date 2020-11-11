/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FIT5046Assignment1.service;

import FIT5046Assignment1.Cinematable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
@Path("fit5046assignment1.cinematable")
public class CinematableFacadeREST extends AbstractFacade<Cinematable> {

    @PersistenceContext(unitName = "FIT5046Assignment1PU")
    private EntityManager em;

    public CinematableFacadeREST() {
        super(Cinematable.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cinematable entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cinematable entity) {
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
    public Cinematable find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cinematable> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cinematable> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByCinemaname/{cinemaname}")
    @Produces({"application/json"})
    public List<Cinematable> findByCinemaname(@PathParam("cinemaname") String cinemaname)
        {
            Query query = em.createNamedQuery("Cinematable.findByCinemaname");
            query.setParameter("cinemaname",cinemaname);
            return query.getResultList();
        }
    
        @GET
    @Path("findBySuburb/{suburb}")
    @Produces({"application/json"})
    public List<Cinematable> findBySuburb(@PathParam("suburb") String suburb)
        {
            Query query = em.createNamedQuery("Cinematable.findBySuburb");
            query.setParameter("suburb",suburb);
            return query.getResultList();
        }
    
      @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<Cinematable> findByPostcode(@PathParam("postcode") String postcode)
        {
            Query query = em.createNamedQuery("Cinematable.findByPostcode");
            query.setParameter("postcode",postcode);
            return query.getResultList();
        }
    
  
    @GET
    @Path("findByUseridANDStartingdateANDEnddate/{userid}/{startingdate}/{endingdate}")
    @Produces({"application/json"})
    public JsonArray  findByUseridANDStartingdateANDEnddate(@PathParam("userid") int userid, @PathParam("startingdate") Date startingdate,@PathParam("endingdate") Date endingdate){
        TypedQuery<Cinematable> query = em.createQuery("SELECT m.cinemaid FROM Memoirtable m WHERE m.userid.userid = :userid AND m.watchdate BETWEEN :startingdate AND :endingdate",Cinematable.class);
        query.setParameter("userid", userid);
        query.setParameter("startingdate", startingdate);
        query.setParameter("endingdate", endingdate);
        List<Cinematable> result = query.getResultList();//store the query result into a list
        String suburb = "";
        ArrayList<String> suburbList = new ArrayList<>();
        JsonArrayBuilder suburbJsonArray = Json.createArrayBuilder();
        //go through each data to fetch the suburb and store in a list
        for(Cinematable c: result){
            suburb = c.getSuburb();
            suburbList.add(suburb);
        }
        ArrayList<String> alreadyCalculated = new ArrayList<>();//to store if the suburb is repeated
        int numberCount =0;
        //go through every suburb in the list to find its occurrence
        for(String s:suburbList){
            numberCount = Collections.frequency(suburbList,s);//check the occurrence of a particular data to count the number
            JsonObject suburbJson = Json.createObjectBuilder().add("totalnumber",numberCount).add("suburb",s).build();
            //check if it is a repeated value. the repeated value only needs to show once in the result but the occurrence should be counted
            if(!alreadyCalculated.contains(s)){
            suburbJsonArray.add(suburbJson);//if it is first time appeared, it will be displayed in the output
            alreadyCalculated.add(s);
            }
        }
        JsonArray jArrayResult = suburbJsonArray.build();  
        return jArrayResult;
    }
    
   
    
}
