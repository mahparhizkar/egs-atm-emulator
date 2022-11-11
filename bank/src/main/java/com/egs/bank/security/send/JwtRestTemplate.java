package com.egs.bank.security.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("request")
public class JwtRestTemplate extends RestTemplate {
    @Autowired
    JwtHeaderRequestInterceptor jwtHeaderRequestInterceptor;

    public <T> T  jwtPost(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables){
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(jwtHeaderRequestInterceptor);
        setInterceptors(interceptors);
        return postForObject(url,request,responseType,uriVariables);
    }
}
