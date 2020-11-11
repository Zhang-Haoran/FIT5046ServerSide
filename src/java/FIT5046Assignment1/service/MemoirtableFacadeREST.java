/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FIT5046Assignment1.service;

import FIT5046Assignment1.Memoirtable;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
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
@Path("fit5046assignment1.memoirtable")
public class MemoirtableFacadeREST extends AbstractFacade<Memoirtable> {

    @PersistenceContext(unitName = "FIT5046Assignment1PU")
    private EntityManager em;

    public MemoirtableFacadeREST() {
        super(Memoirtable.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Memoirtable entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Memoirtable entity) {
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
    public Memoirtable find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoirtable> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoirtable> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByMoviename/{moviename}")
    @Produces({"application/json"})
    public List<Memoirtable> findByMoviename(@PathParam("moviename") String moviename)
        {
            Query query = em.createNamedQuery("Memoirtable.findByMoviename");
            query.setParameter("moviename",moviename);
            return query.getResultList();
        }
    
      @GET
    @Path("findByMoviereleasedate/{moviereleasedate}")
    @Produces({"application/json"})
    public List<Memoirtable> findByMoviereleasedate(@PathParam("moviereleasedate") Date moviereleasedate)
        {
            Query query = em.createNamedQuery("Memoirtable.findByMoviereleasedate");
            query.setParameter("moviereleasedate",moviereleasedate);
            return query.getResultList();
        }
    
       @GET
    @Path("findByWatchdate/{watchdate}")
    @Produces({"application/json"})
    public List<Memoirtable> findByWatchdate(@PathParam("watchdate") Date watchdate)
        {
            Query query = em.createNamedQuery("Memoirtable.findByWatchdate");
            query.setParameter("watchdate",watchdate);
            return query.getResultList();
        }
    
       @GET
    @Path("findByWatchtime/{watchtime}")
    @Produces({"application/json"})
    public List<Memoirtable> findByWatchtime(@PathParam("watchtime") Time watchtime)
        {
            Query query = em.createNamedQuery("Memoirtable.findByWatchtime");
            query.setParameter("watchtime",watchtime);
            return query.getResultList();
        }
    
        @GET
    @Path("findByComment/{comment}")
    @Produces({"application/json"})
    public List<Memoirtable> findByComment(@PathParam("comment") String comment)
        {
            Query query = em.createNamedQuery("Memoirtable.findByComment");
            query.setParameter("comment",comment);
            return query.getResultList();
        }
    
      @GET
    @Path("findByRatingscore/{ratingscore}")
    @Produces({"application/json"})
    public List<Memoirtable> findByRatingscore(@PathParam("ratingscore") int ratingscore)
        {
            Query query = em.createNamedQuery("Memoirtable.findByRatingscore");
            query.setParameter("ratingscore",ratingscore);
            return query.getResultList();
        }
    
    @GET
    @Path("findByMovienameANDCinemaname/{moviename}/{cinemaname}")
    @Produces({"application/json"})
    public List<Memoirtable>findByMovienameANDCinemaname(@PathParam("moviename") String moviename, @PathParam("cinemaname") String cinemaname)
    {
            TypedQuery<Memoirtable> query = em.createQuery("SELECT m FROM Memoirtable m WHERE m.moviename =:moviename AND m.cinemaid = (SELECT c.cinemaid FROM Cinematable c WHERE c.cinemaname =:cinemaname) ",Memoirtable.class);
            query.setParameter("moviename",moviename);
            query.setParameter("cinemaname",cinemaname);
            return query.getResultList();
    }
    
       @GET
    @Path("findByMovienameANDSuburb/{moviename}/{suburb}")
    @Produces({"application/json"})
    public List<Memoirtable> findByMovienameANDSuburb(@PathParam("moviename") String moviename,@PathParam("suburb") String suburb )
        {
            Query query = em.createNamedQuery("Memoirtable.findByMovienameANDSuburb");
            query.setParameter("moviename",moviename);
            query.setParameter("suburb",suburb);
            return query.getResultList();
        }
    
   
    @GET
    @Path("findByUseridANDYear/{userid}/{year}")
    @Produces({"application/json"})
    public JsonArray findByUseridANDYear(@PathParam("userid") int userid, @PathParam("year") String year){
        TypedQuery<Memoirtable> query = em.createQuery("SELECT m FROM Memoirtable m WHERE m.userid.userid = :userid AND EXTRACT (YEAR FROM m.watchdate) = :year",Memoirtable.class);
        query.setParameter("userid", userid);
        query.setParameter("year", year); 
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");//date format MMMM gives the month name
        List<Memoirtable> result = query.getResultList();//store the query result into a list
        ArrayList<String> monthList = new ArrayList<>();
        JsonArrayBuilder monthJsonArray = Json.createArrayBuilder();  
         //go through each data to fetch the month from watchdate and store in a list
        for(Memoirtable m: result){
            String month = dateFormat.format(m.getWatchdate());
        monthList.add(month);
    }
         ArrayList<String> alreadyCalculated = new ArrayList<>(); //to store if the month is repeated
        int numberCount =0;
        //go through every month in the list to find its occurrence
        for(String s:monthList){
            numberCount = Collections.frequency(monthList,s);//check the occurrence of a particular data to count the number
            JsonObject monthJson = Json.createObjectBuilder().add("totalnumber",numberCount).add("month",s).build();
            //check if it is a repeated value. the repeated value only needs to show once in the result but the occurrence should be counted
            if(!alreadyCalculated.contains(s)){
            monthJsonArray.add(monthJson);//if it is first time appeared, it will be displayed in the output
            alreadyCalculated.add(s);
            }
        } 
        JsonArray jArrayResult = monthJsonArray.build();  
        return jArrayResult;
        
    }
    
    @GET
    @Path("findByUserid/{userid}")
    @Produces({"application/json"})
    public JsonArray findByUserid(@PathParam("userid") int userid)
        {
            TypedQuery<Memoirtable> query = em.createQuery("SELECT m FROM Memoirtable m WHERE m.userid.userid = :userid",Memoirtable.class);
            query.setParameter("userid", userid);
            List<Memoirtable> result = query.getResultList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//date format give only year, month and day, remove time
            List<Memoirtable> maxRatingMovie = new ArrayList<>();//store the movie with highest score just in case there are two movie with the same highest score
            ArrayList<Integer> scoreList = new ArrayList<>();
            JsonArrayBuilder movieJsonArray = Json.createArrayBuilder();  
            //fetch score from data
            for(Memoirtable m: result){
            int ratingScore = m.getRatingscore();
            scoreList.add(ratingScore);
    }
            int maxScore = Collections.max(scoreList);//find the highest score in the list
            //go through each data find the one or ones with highest score
            for(Memoirtable m: result){
                int ratingScore = m.getRatingscore();
                if(ratingScore == maxScore){
                    maxRatingMovie.add(m);// add the highest score movie into the list
                }
            }
            //display all the movie with highest score
            for(Memoirtable m: maxRatingMovie){
               String eachMovie = ("Name: "+m.getUserid().getName())+", Rating Score: "+(m.getRatingscore())+", Movie release date: "+(dateFormat.format(m.getMoviereleasedate()).toString());
               movieJsonArray.add(eachMovie);
            }
            JsonArray jArrayResult = movieJsonArray.build();  
            return jArrayResult;
        }
    
     @GET
    @Path("findMovieNameByUserid/{userid}")
    @Produces({"application/json"})
    public JsonArray findMovieNameByUserid(@PathParam("userid") int userid)
        {
            //fetch the all the memoir which year of wathchdate is same as its release year
            TypedQuery<Memoirtable> query = em.createQuery("SELECT m FROM Memoirtable m WHERE m.userid.userid = :userid AND EXTRACT (YEAR FROM m.moviereleasedate) = EXTRACT (YEAR FROM m.watchdate) ",Memoirtable.class);
            query.setParameter("userid", userid);
            List<Memoirtable> result = query.getResultList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//date format give only year, month and day, remove time
            ArrayList<String> movieList = new ArrayList<>();
            JsonArrayBuilder movieJsonArray = Json.createArrayBuilder();  
            for(Memoirtable m: result){
           
            String movieInfo = "Movie Name: "+m.getMoviename()+", Release Date: "+ dateFormat.format( m.getMoviereleasedate()).toString();
            movieList.add(movieInfo);
            movieJsonArray.add(movieInfo);
    }
            JsonArray jArrayResult = movieJsonArray.build();  
            return jArrayResult;
        }
    
     @GET
    @Path("findSameMovieNameByUserid/{userid}")
    @Produces({"application/json"})
    public JsonArray findSameMovieNameByUserid(@PathParam("userid") int userid)
        {
            TypedQuery<Memoirtable> query = em.createQuery("SELECT m FROM Memoirtable m WHERE m.userid.userid = :userid ",Memoirtable.class);
            query.setParameter("userid", userid);
            List<Memoirtable> result = query.getResultList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//date format give only year, month and day, remove time
            ArrayList<String> movieList = new ArrayList<>();
            ArrayList<String> sameMovieList = new ArrayList<>();//if movie has the same name and it has remade, store in the list
            JsonArrayBuilder movieJsonArray = Json.createArrayBuilder();  
            for(Memoirtable m: result){
            String moviename = m.getMoviename();
            //if the movie has already contains current processing movie name, then they have the same name.
            if(movieList.contains(moviename)){
                sameMovieList.add(moviename);
            }
            movieList.add(moviename);//add every movie into the movie list
              }
           for(Memoirtable m: result){
               //fetch all the movie which have the same name 
               if(sameMovieList.contains(m.getMoviename())){
                   movieJsonArray.add("Movie Name: "+m.getMoviename()+", Release Date: "+dateFormat.format(m.getMoviereleasedate()));
               }
           }
            JsonArray jArrayResult = movieJsonArray.build();  
            return jArrayResult;
        }
    @GET
    @Path("findHighRatingMovieNameByUserid/{userid}")
    @Produces({"application/json"})
    public JsonArray findHighRatingMovieNameByUserid(@PathParam("userid") int userid)
        {
            //fetch the data which release date is current year
            TypedQuery<Memoirtable> query = em.createQuery("SELECT m FROM Memoirtable m WHERE m.userid.userid = :userid AND EXTRACT (YEAR FROM m.moviereleasedate) = EXTRACT (YEAR FROM current_date) ",Memoirtable.class);
            query.setParameter("userid", userid);
            List<Memoirtable> result = query.getResultList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//date format give only year, month and day, remove time
            ArrayList<List<Integer>> scoreList = new ArrayList<>();// 2D array. first item store rating score. second item store its memoir id. it is a list of list
            ArrayList<Integer> highScoreList = new ArrayList<>();//a list to store top five highest score
            JsonArrayBuilder movieJsonArray = Json.createArrayBuilder();  
            for(Memoirtable m: result){
            int ratingScore = m.getRatingscore();
            int memoirid = m.getMemoirid();
            
            ArrayList<Integer> scoreIdPair = new ArrayList<>();// a list to store memoir id and rating score as pair. that's the list which will be stored into list
            scoreIdPair.add(ratingScore);
            scoreIdPair.add(memoirid);
            //store rating score and memoir id as pair into 2D array. it is a list of list
            scoreList.add(scoreIdPair);
              }
            //compare the first item of 2D array. it compares the rating score.
            Comparator<List<Integer>> comparator = new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    return o1.get(0).compareTo(o2.get(0));
                }
            };
            //sort from low to highest in the list
            Collections.sort(scoreList,comparator);
            //reverse the list. we can get rating score from highest to lowest
            Collections.reverse(scoreList);
            //get the top five highest score
            for(int i = 0; i<5;i++){
                highScoreList.add(scoreList.get(i).get(1));
            }
            
            //because it is 2D array. we store the movie id in the second item. therefore, we can find the memoir by id
            for(int i: highScoreList){
                for(Memoirtable m: result){
                    //for every data that we query before, if the memoir id is same as the memoir id that we store in the 2D arry. we will add them into the output
                    if(m.getMemoirid() == i){
                        JsonObject movieJson = Json.createObjectBuilder().add("Movie Name",m.getMoviename()).add("Release Date",dateFormat.format(m.getMoviereleasedate())).add("Rating Score", m.getRatingscore()).build();
                        movieJsonArray.add(movieJson);
                    }
            }
            
        }
    
                JsonArray jArrayResult = movieJsonArray.build();  
                return jArrayResult;
}
}

 