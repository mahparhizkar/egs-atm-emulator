package com.egs.atm.security.recieve;

import com.egs.atm.logger.MyLogger;
import com.egs.atm.security.JwtToken;
import com.egs.atm.security.send.JwtRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Scope("request")
public class JwtRequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    public JwtToken jwtToken;

    @Autowired
    JwtRestTemplate jwtRestTemplate;

    @Autowired
    MyLogger myLogger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null) {
            response.setStatus(403);
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"Bad Token");
            myLogger.eventLogger.info("Token is Empty!");
            return false;
        }

        jwtToken.setToken(token);
        String uri = request.getRequestURI();

        if (uri.equals("/verifyCardNumber") || uri.equals("/verifyPin")) {
            jwtToken.detokenizeByCardNumber();
            myLogger.eventLogger.info("Method: " + request.getMethod() + " & RequestURI: " + request.getRequestURI() + " & RemoteAddr: " + request.getRemoteAddr() + " & CardNumber: " + jwtToken.getCardNumber());
        }

        return super.preHandle(request, response, handler);
    }
}
