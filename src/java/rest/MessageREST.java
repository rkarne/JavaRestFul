/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Roja Jayashree Karne
 */
@Path("/messages")
@ApplicationScoped
public class MessageREST {

    @Inject
    private MessageController messageController;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
/**
 * get all the list object of message class in the form of json array
 * @return Response
 */
    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(messageController.getAllJson()).build();
    }

    /**
     * fetch the id from list and convert the into json object 
     * @param id
     * @return Response
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") int id) {
        JsonObject json = messageController.getByIdJson(id);
        if (json != null) {
            return Response.ok(json).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * accepts the string from and to convert that into date and fetch that from list objects and convert that as object array
     * @param fromStr
     * @param toStr
     * @return 
     */
    @GET
    @Path("{from}/{to}")
    @Produces("application/json")
    public Response getByDate(@PathParam("from") String fromStr, @PathParam("to") String toStr) {
        try {
            return Response.ok(messageController.getByDateJson(sdf.parse(fromStr), sdf.parse(toStr))).build();
        } catch (ParseException ex) {
            Logger.getLogger(MessageREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    /**
     * post the json to insert into db and list and then return the json array with the updated list from database
     * @param json
     * @return Response
     */

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(JsonObject json) {
        return Response.ok(messageController.addJson(json)).build();
    }

    /**
     * accept the id and json of the updated list object and uodate that into db and list 
     * @param id
     * @param json
     * @return  Response
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response edit(@PathParam("id") int id, JsonObject json) {
        JsonArray jsonWithId = messageController.editJson(id, json);
        if (jsonWithId != null) {
            return Response.ok(jsonWithId).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * delete the list object of message class type from db and from list
     * @param id
     * @return Response
     */
    @DELETE
    @Path("{id}")
    public Response del(@PathParam("id") int id) {
        if (messageController.deleteById(id)) {
            return Response.status(Response.Status.ACCEPTED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
