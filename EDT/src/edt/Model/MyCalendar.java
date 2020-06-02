/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author mathi
 */
public class MyCalendar {
    Calendar calendar;
    
    public MyCalendar(){
       calendar = Calendar.getInstance();
    }
    
    public void changeDate(Date date){
        calendar.setTime(date);
    }
    
    public int getWeek(){
        return calendar.get(Calendar.WEEK_OF_YEAR);
    } 
    
    public void semaineSuivante(){
        calendar.add(Calendar.DATE, 7);       
    }
    
    public void semainePrecedente(){
        calendar.add(Calendar.DATE, -7);       
    }
    
    public Date quelleDate(){
        return calendar.getTime();
    }
}
