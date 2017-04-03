/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author rkarne
 */

@ApplicationScoped

public class MessageController {
    private List<Message> messages = new ArrayList<>();
    //Message currentmessage;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Empty class constructor 
     */
    public MessageController(){
        //currentmessage = new Message(1, "", "", "", null);
        getValues();
    }
    
    /**
     * create a method that add value to list
     */
    private void getValues(){
        try {
           
            messages.add(new Message(1, "sample1", "This is sample1", "sampleAuthor1", df.parse("2017-03-20")));
            messages.add(new Message(2, "sample2", "This is sample2", "sampleAuthor2", df.parse("2017-03-21")));
            messages.add(new Message(3, "sample3", "This is sample3", "sampleAuthor3", df.parse("2017-03-22")));
        } catch (ParseException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
   
    /**
     * method creates a JSON array and return JSON 
     * @return JSON array
     */
    public JsonArray getAllJson(){
         JsonArrayBuilder json = Json.createArrayBuilder();
         for(Message msg : messages){
             json.add(msg.convertToJson());
         }  
        return json.build();
    }

   
    
    public List<Message> getMessageList() {
        
        return messages;
    }
    
    /**
     * method with input as message id 
     * @param id
     * @return message object
     */
    public Message getMessageById(int id){
         for (Message msg: messages) {
            if (msg.getId() == id) {
                return msg;
            }
        }
        return null;
    }
   
    /**
     * Get JSON Object by id
     * @param id
     * @return 
     */
    public JsonObject getByIdJson(int id){
        
        for(Message m : messages){
            if(m.getId() == id){
             JsonObject json = Json.createObjectBuilder()
                     .add("id", m.getId())
                     .add("title", m.getTitle())
                     .add("contents", m.getContents())
                     .add("author", m.getAuthor())
                     .add("senttime", m.getSenttime().toString())
                     .build(); 
             return json;
            }
        }
        return null;
    }
    
    /**
     * Accepts the date as string and returns list
     * @param startDate
     * @param endDate
     * @return list
     */
  public List<Message> getMessageByDateJson(String startDate, String  endDate){
        List<Message> messagesByDate = new ArrayList<>();
         for (Message m : messages){
            try {
                
                if (m.getSenttime().after(df.parse(startDate)) 
                        && m.getSenttime().before(df.parse(endDate))
                        || m.getSenttime().equals(df.parse(startDate))
                        || m.getSenttime().equals(df.parse(endDate))){
                    messagesByDate.add(m);
                }
            } catch (ParseException ex) {
                Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return messagesByDate;    
  }
 
  /**
   * Adds the message object to list and returns list
   * @param json
   * @return list
   */
  public List<Message> addJson(JsonObject json){
        try {
              int id = messages.size() + 1;
              String title = json.getString("title");
              String content = json.getString("contents");
              String author = json.getString("author");
              Date sent = df.parse(json.getString("senttime"));
            //messages.add(new Message(messages.size() + 1, json.getString("title"), json.getString("contents"), json.getString("author"), df.parse(json.getString("senttime"))));
              messages.add(new Message(id, title, content, author, sent));
        } catch (ParseException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
           return messages;

           /* JsonObject json1 = Json.createObjectBuilder()
                    .add("id",id  )
                    .add("title", json.getString("title"))
                    .add("contents", json.getString("contents"))
                    .add("author", json.getString("author"))
                    .add("senttime", json.getString("senttime"))
                    .build();
            return json1;  */
  }
  /**
   * accepts the id of message and returns the message object
   * @param id
   * @param json
   * @return 
   */
  public Message editJson(Integer id, JsonObject json){
      for(Message m : messages){
            if(m.getId() == id){
                try {
                    m.setTitle(json.getString("title"));
                    m.setContents(json.getString("contents"));
                    m.setAuthor(json.getString("author"));
                    m.setSenttime(df.parse(json.getString("senttime")));  
                } catch (ParseException ex) {
                    Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
                } 
                return m;
            } 
      }
      return null;
  }
  
  /**
   * delete Message by id from list
   * @param id
   * @return String "200 OK" if deleted else return failed
   */
  public String deleteById(int id){
        for(Message m : messages){
            if(m.getId() == id){
              messages.remove(m);
              return "200 OK";
            }
        }
        return "Failed to delete Message";
  }
}
