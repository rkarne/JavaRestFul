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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author rkarne
 */
public class Message {
    
    private int id;
    private String title;
    private String contents;
    private String author;
    private Date senttime;
 
    /**
     * Empty Constructor
     */
  public Message()
    {
    
    }
  DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
  
  /**
   * Para
   * @param id
   * @param title
   * @param contents
   * @param author
   * @param senttime 
   */
   public  Message(int id, String title, String contents, String author, Date senttime){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.senttime = senttime;
    }
   
   /**
    * Class constructor with parameter as JSON
    * @param json 
    */
    public Message(JsonObject json) {
        id = json.getInt("id");
        title = json.getString("title");
        contents = json.getString("contents");
        author = json.getString("author");
        try {
            senttime = d.parse(json.getString("senttime"));
        } catch (ParseException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
            Date dt =  new Date();
            senttime = dt;
        }
    }

    /**
     * Class instance variables into json object
     * @return JSON object 
     */
    public JsonObject convertToJson() {
        return Json.createObjectBuilder()
                .add("id", id)
                .add("title", title)
                .add("contents", contents)
                .add("author", author)
                .add("senttime",senttime.toString())
                .build();
    }
    
    /**
     * Setters and getters
     * @return 
     */
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getAuthor() {
        return author;
    }

    public Date getSenttime() {
        return senttime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSenttime(Date senttime) {
        this.senttime = senttime;
    }

  
    
}
