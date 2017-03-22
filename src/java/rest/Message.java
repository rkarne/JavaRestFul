/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Date;

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
 
    
    Message(int id, String title, String contents, String author, Date senttime){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.senttime = senttime;
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
