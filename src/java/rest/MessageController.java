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

/**
 *
 * @author rkarne
 */
@ApplicationScoped
@Named
public class MessageController {
    private List<Message> messageList;
    Message currentmessage;

    public MessageController(){
        currentmessage = new Message(1, "", "", "", null);
        getValues();
    }
    
    private void getValues(){
        try {
            messageList = new ArrayList<>();
            DateFormat df = new SimpleDateFormat("yyy-MM-dd");
            Date senttime = df.parse("2017-03-22");
            currentmessage = new Message(1,"TestTitle", "This is sample testing", "Test", senttime);
            add(currentmessage);
        } catch (ParseException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void add(Message s) {
        messageList.add(s);
    }
    
    public List<Message> getMessageList() {
        return messageList;
    }
    
    public Message getById(int id){
         for (Message m : messageList) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
    
    public List<Message> getFromTo(Date fromDate, Date endDate){
        return messageList;
    }
    
    public List<Message> getAll(){
        return messageList;
    }
}
