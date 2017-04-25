/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Roja Jayashree Karne
 */
public class Message {
/**
 * data format is given here to perform the actions 
 */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    private int id;
    private String title;
    private String contents;
    private String author;
    private Date senttime;

    /**
     * empty constructor
     */
    public Message() {
    }

    /**
     * constructor that accepts the json object and assign those values to the class variables
     * @param json 
     */
    public Message(JsonObject json) {        
        id = json.getInt("id", 0);
        title = json.getString("title", "");
        contents = json.getString("contents", "");
        author = json.getString("author", "");
        String timeStr = json.getString("senttime", "");
        try {
            senttime = sdf.parse(timeStr);
        } catch (ParseException ex) {
            // This sets the time to NOW if there's a failure parsing
            senttime = new Date();
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, "Failed Parsing Date: " + timeStr);
        }
    }

    /**
     * getters and setters from the class variables
     * @return 
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getSenttime() {
        return senttime;
    }

    public void setSenttime(Date senttime) {
        this.senttime = senttime;
    }

    /**
     * converts the class variables into jsonObject 
     * @return 
     */
    public JsonObject toJson() {
        String timeStr = sdf.format(senttime);
        return Json.createObjectBuilder()
                .add("id", id)
                .add("title", title)
                .add("contents", contents)
                .add("author", author)
                .add("senttime", timeStr)
                .build();
    }
}
