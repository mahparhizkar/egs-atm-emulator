package com.egs.atm.security.send;

import com.egs.atm.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtHeaderRequestInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    JwtToken jwtToken;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("token", jwtToken.getToken());
        return execution.execute(request, body);
    }
}
