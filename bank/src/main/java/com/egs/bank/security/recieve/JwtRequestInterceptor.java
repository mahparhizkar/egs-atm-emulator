package com.egs.bank.security.recieve;

import com.egs.bank.logger.MyLogger;
import com.egs.bank.security.JwtToken;
import com.egs.bank.security.SessionHandler;
import com.egs.bank.security.send.JwtRestTemplate;
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

    @Autowired
    SessionHandler sessionHandler;

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
            if (uri.equals("/verifyCardNumber")) {
                jwtToken.detokenizeByCardNumber();
            } else {
                jwtToken.detokenizeByCardNumberAndPin();
            }
            myLogger.eventLogger.info("Method: " + request.getMethod() + " & RequestURI: " + request.getRequestURI() + " & RemoteAddr: " + request.getRemoteAddr() + " & CardNumber: " + jwtToken.getCardNumber());
            if (jwtToken.verify()) {
                return super.preHandle(request, response, handler);
            } else {
                response.setStatus(403);
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"Bad Token");
                myLogger.eventLogger.info("Token is not valid for cardNumber: " + jwtToken.getCardNumber());
                return false;
            }
        }

        if (sessionHandler.getSession(token) != null) {
            return super.preHandle(request, response, handler);
        } else {
            response.setStatus(403);
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"Bad Token");
            myLogger.eventLogger.info("Token expired for cardNumber: " + jwtToken.getCardNumber());
            return false;
        }
    }
}
