package com.learn.spring.beans;

import java.util.List;

import com.learn.spring.interfaces.Congress;
import com.learn.spring.interfaces.CongressChief;
import com.learn.spring.interfaces.CongressMan;

public class LocalCongress implements Congress {
    
    private List<CongressMan> congressMans; 
    private CongressChief chief;
    
    public LocalCongress(CongressChief chief, List<CongressMan> congressMans){
        this.chief = chief;
        this.congressMans = congressMans;
    }
    
    public void performMeeting(){
        chief.doPitch();
    }
    
    public void showCongressMans(){
        System.out.println(congressMans.toString());
    }

}
