package com.johara.product.intercept;

import com.johara.product.client.TrackingServiceClient;
import com.johara.product.kafka.TrackingEventProducerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CookieInterceptor implements HandlerInterceptor {
    public static final String COOKIE_NAME = "X-IDENTITY";
    private final TrackingEventProducerService trackingEventProducerService;

    private final TrackingServiceClient trackingServiceClient;

    @Autowired
    public CookieInterceptor(TrackingServiceClient trackingServiceClient, TrackingEventProducerService trackingEventProducerService){
        this.trackingServiceClient = trackingServiceClient;
        this.trackingEventProducerService = trackingEventProducerService;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        if(request.getCookies() == null){
            String newid = trackingServiceClient.createIdentity().getId();
            System.err.println("New ID: " + newid);
            Cookie cookie = new Cookie(COOKIE_NAME, newid);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals(COOKIE_NAME)){
                    trackingEventProducerService.sendTrackingEvent(cookie.getValue());
                }
            }
        }
        return true;
    }

    public String confirm(){
        return "CookieInterceptor is working";
    }
}
