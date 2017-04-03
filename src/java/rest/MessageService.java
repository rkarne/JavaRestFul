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
import java.util.List;
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
    public Response getAllJson() {
         //JsonArrayBuilder json = Json.createArrayBuilder();
        // json = messageController.getAllJson();
       // for (Message m : messageController.getMessageList()) {
           // json.add(m.convertToJson());
        //}
        return Response.ok(messageController.getAllJson()).build();
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
     * @param id
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public JsonObject getJsonById(@PathParam("id") int id){
     return messageController.getMessageById(id).convertToJson();
    }
    
    @GET
    @Path("{startdate}/{enddate}")
    @Produces("application/json")
    public Response getJsonByDate(@PathParam("startdate") String startDate, @PathParam("enddate") String endDate ) {
         List<Message> rangeMessages = messageController.getMessageByDateJson(startDate, endDate);
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (Message m : rangeMessages) {
            json.add(m.convertToJson());
        }
        return Response.ok(json.build().toString()).build(); 
    }
    /**
     * post a json add that to list and return list
     * @param json
     * @return Response as JSON
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(JsonObject json){
       List<Message> message = messageController.addJson(json);
       //JsonArrayBuilder js = Json.createArrayBuilder();
       //for (Message m : message) {
         //  js.add(m.convertToJson());
        //}
        return getAllJson();
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
         return messageController.editJson(id, json).convertToJson();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces("text/plain")
    public String deleteJson(@PathParam("id") int id){
         return messageController.deleteById(id);
    }
}
