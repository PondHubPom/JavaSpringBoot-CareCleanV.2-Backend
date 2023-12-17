package com.Hoime.CareClean.middleware;

import com.Hoime.CareClean.security.Security;
import com.Hoime.CareClean.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestAuthorizationMiddleware implements HandlerInterceptor {

    @Autowired
    private Security security;
    @Autowired
    private UtilService utilService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String paths = request.getRequestURI();

        String[] pathByPassList = {
                "login",
                "register",
                "util",
                "forgot-password",
                "upload",
        };

        for (String path : pathByPassList) {
            if (paths.contains(path)) {
                return true;
            }
        }

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (security.validateToken(token)) {

                String id = security.getIdToken(token);

                if (id == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                }
                request.setAttribute("id", id);

                return true;
            }
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Expired");

    }
}