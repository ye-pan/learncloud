package com.yp.learncloud.zuulserver.utils;

import com.yp.learncloud.commons.utils.UserContext;
import com.yp.learncloud.commons.utils.UserContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 将该拦截器配置在{@link org.springframework.web.client.RestTemplate}中，保证通过网关调用会将header的参数，传给内部服务实例
 */
public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
        return execution.execute(request, body);
    }
}
