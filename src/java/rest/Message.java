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
 
  public Message()
    {
    
    }
  DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
  
   public  Message(int id, String title, String contents, String author, Date senttime){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.senttime = senttime;
    }
    public Message(JsonObject json) {
        id = json.getInt("id");
        title = json.getString("title");
        contents = json.getString("contents");
        author = json.getString("author");
        String senttime = json.getString("senttime");
        try {
            this.senttime = d.parse(senttime);
        } catch (ParseException ex) {
            this.senttime = new Date();
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("id", id)
                .add("title", title)
                .add("contents", contents)
                .add("author", author)
                .add("senttime",senttime.toString())
                .build();
    }
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
