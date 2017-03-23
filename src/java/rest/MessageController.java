/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.math.BigDecimal;
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
@Named
@ApplicationScoped

public class MessageController {
    private List<Message> messages = new ArrayList<>();
    Message currentmessage;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public MessageController(){
        currentmessage = new Message(1, "", "", "", null);
        getValues();
    }
    
    private void getValues(){
        try {
           
            messages.add(new Message(1, "sample1", "This is sample1", "sampleAuthor1", df.parse("2017-03-20")));
            messages.add(new Message(2, "sample2", "This is sample2", "sampleAuthor2", df.parse("2017-03-21")));
            messages.add(new Message(3, "sample3", "This is sample3", "sampleAuthor3", df.parse("2017-03-22")));
        } catch (ParseException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    public JsonArray getAllJson(){
         JsonArrayBuilder json = Json.createArrayBuilder();
         for(Message m : messages){
             json.add(m.toJSON());
         }  
        return json.build();
    }

    public void setCurrentmessage(Message currentmessage) {
        this.currentmessage = currentmessage;
    }

    public Message getCurrentmessage() {
        return currentmessage;
    }
    
    public List<Message> getMessageList() {
        
        return messages;
    }
    
    public Message getById(int id){
         for (Message m : messages) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
   
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
    
  public JsonArray getByDateJson(Date startDate, Date endDate){
     
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (Message m : messages) {
            if(m.getSenttime().after(startDate) && m.getSenttime().before(endDate)){
                json.add(getByIdJson(m.getId()));
            }
        }
       return json.build();
  }
  
  public JsonObject addJson(JsonObject json){
           int id = messages.size() + 1;
            String title = json.getString("title");
            String contents  = json.getString("contents");
            String author = json.getString("author");
            String senttime = "";
        try {
            if (json.containsKey("senttime")){
                senttime = json.getString("senttime");
                currentmessage.setId(id);
                currentmessage.setTitle(title);
                currentmessage.setContents(contents);
                currentmessage.setAuthor(author);
                currentmessage.setSenttime(df.parse(senttime));
               // messages.add(new Message(id, title, contents, author, df.parse(senttime)));
               messages.add(currentmessage);
            }
            else {
                messages.add(new Message(id, title, contents, author, df.parse(senttime)));
             
            }
        } catch (ParseException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     
           return getByIdJson(currentmessage.getId());
           /* JsonObject json1 = Json.createObjectBuilder()
                    .add("id",id  )
                    .add("title", json.getString("title"))
                    .add("contents", json.getString("contents"))
                    .add("author", json.getString("author"))
                    .add("senttime", json.getString("senttime"))
                    .build();
            return json1;  */
  }
  
  public JsonObject editJson(Integer id, JsonObject json){
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
              getByIdJson(id);
            }  
      }
      return null;
  }
  
  public boolean deleteById(int id){
        for(Message m : messages){
            if(m.getId() == id){
              messages.remove(m);
              return true;
            }
        }
        return false;
  }
}
