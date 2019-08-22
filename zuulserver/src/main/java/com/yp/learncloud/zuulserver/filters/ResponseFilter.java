package com.yp.learncloud.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 响应过滤器
 */
@Component
@Slf4j
public class ResponseFilter extends ZuulFilter {
    private static final  int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;
    private FilterUtils filterUtils;

    public ResponseFilter(FilterUtils filterUtils) {
        this.filterUtils = filterUtils;
    }

    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        log.info("adding the correlation id to the outbound headers. correlationId:{}", filterUtils.getCorrelationId());
        context.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());

        log.info("Completing outgoing request for {}.", context.getRequest().getRequestURI());
        return null;
    }
}
