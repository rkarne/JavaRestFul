/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author rkarne
 */
@Path("/messages")
@ApplicationScoped

public class MessageService {

    @Context
    private UriInfo context;
   
    private MessageController messageController = new MessageController();

 
  
    /**
     * Retrieves representation of an instance of rest.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public JsonArray getJson() {
        return messageController.getAllJson();
    }
  /*  public Response getJson(){
         JsonArrayBuilder json = Json.createArrayBuilder();
        for (Message m : messageController.getMessageList()) {
            json.add(m.toJSON());
        }
        return Response.ok(json.build().toString()).build();
    } */
   /**
     * GET method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public JsonObject getJsonById(@PathParam("id") int id){
     return messageController.getByIdJson(id);
        
    }
    
    
    
    @GET
    @Path("{startdate}/{enddate}")
    @Produces("application/json")
    public JsonArray getJsonByDate(@PathParam("startdate") String startDate, @PathParam("enddate") String endDate ) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        
       return messageController.getByDateJson(df.parse(startDate), df.parse(endDate));
        
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public JsonArray postJson(JsonObject json){
       return messageController.addJson(json);
    }
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public JsonObject putJson(@PathParam("id") int id, JsonObject json) {
         return messageController.editJson(id, json);
    }
    
    @DELETE
    @Path("/{id}")
    @Produces("text/plain")
    public boolean deleteJson(int id){
         return messageController.deleteById(id);
    }
}
