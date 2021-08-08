package com.karaoke.mp3project.service.email;

import com.karaoke.mp3project.service.IUtility;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
public class Utility implements IUtility {
    public String getSiteURL(HttpServletRequest request){
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(),"");
    }
}
