package com.example.demo.configuration;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    private final HttpSession session;

    public SessionManager(HttpSession session){
        this.session = session;
    }

    public void setUserIdInSession(Long userIdInSession){
        session.setAttribute("userId",userIdInSession);
    }

    public Long getLoggedUserId(){
        Object userId = session.getAttribute("userId");

        if(userId!= null){
            return (Long) userId;
        }

//        TODO: Add throw NotLoggedInException
        return null;
    }

}
