package com.egs.bank.security;

import com.egs.bank.common.CommonUtil;
import com.egs.bank.common.LoginState;
import com.egs.bank.dto.LoginResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

@Component
@Getter
@Setter
public class SessionHandler {

    Map<String, LoginResponseDto> sessionMap = new Hashtable<String, LoginResponseDto>();

    @Value("${session.timeout}")
    long sessionTimeOut;

    public LoginResponseDto getSession(String token){
        return  sessionMap.get(token);
    }

    public LoginResponseDto addSession(String cardNumber, String pinType, String pinValue) {
        String randomClientToken;
        do {
            randomClientToken = CommonUtil.generateRandom(64);
        } while(sessionMap.get(randomClientToken) != null);

        LoginResponseDto loginResponse = LoginResponseDto.builder()
                .cardNumber(cardNumber)
                .pinType(pinType)
                .pinValue(pinValue)
                .state(LoginState.LOGGED_IN)
                .token(randomClientToken)
                .lastRequestTimestamp(System.currentTimeMillis())
                .build();
        sessionMap.put(randomClientToken, loginResponse);
        return loginResponse;
    }

    @Scheduled(cron = "0 * * * * *")
    public void clearExpiredSessions() {
        Iterator iterator = sessionMap.entrySet().iterator();
        while (iterator.hasNext()) {

            Map.Entry<String, LoginResponseDto> pair = (Map.Entry<String, LoginResponseDto>) iterator.next();
            if (System.currentTimeMillis() - pair.getValue().getLastRequestTimestamp() > sessionTimeOut) {
                iterator.remove();
                System.out.println("removing " + pair.getKey());
            }

        }
    }
}
