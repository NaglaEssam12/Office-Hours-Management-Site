/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoNotifications;
import static java.lang.System.out;
import model.Notification;

/**
 *
 * @author DELL
 */
public class NotifyUser {
    DaoNotifications daoNotifications = new DaoNotifications();
    
    public boolean notify(String fromUserEmail, String toUserEmail, String notificationMessage){
        boolean flag = false;
        Notification notification = new Notification();
        notification.setFromEmail(fromUserEmail);
        notification.setToEmail(toUserEmail);
        notification.setNotificationMessage(notificationMessage);
        
        if(daoNotifications.sendNotificationToSystem(notification))
        {
           flag = true;
        }
        else{
            flag = false;
        }
        return flag;
    }
    
    public void sendEmail(String from, String to,String subjectMail, String message){
        SendMail sendMail = new SendMail();
        if(sendMail.send(to,subjectMail,message,from,"offorg123"))
        {
            out.println("Mail send successfully");
        }
        else{
             out.println("Failed sending mail!!!");
        }
    }
}
