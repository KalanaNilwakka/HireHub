package com.Kalana.HireHub.util;

import com.Kalana.HireHub.security.user.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

    public CustomUserDetails getLoggedInUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomUserDetails loggedInUser = (CustomUserDetails) authentication.getPrincipal();
        return loggedInUser;
    }
}
