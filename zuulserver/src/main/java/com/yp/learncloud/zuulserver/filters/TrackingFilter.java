package com.yp.learncloud.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * 请求过滤器
 */
@Slf4j
@Component
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    private FilterUtils filterUtils;

    public TrackingFilter(FilterUtils filterUtils) {
        this.filterUtils = filterUtils;
    }

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
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
        String correlationId = filterUtils.getCorrelationId();
        if(correlationId != null) {
            log.info("tmx-correlation-id found in tracking filter: {}", correlationId);
        } else {
            correlationId = UUID.randomUUID().toString();
            filterUtils.setCorrelationId(correlationId);
            log.info("tms-correlation-id generated in tracking filter: {}", correlationId);
        }
        log.info("Processing incoming request for {}.",  RequestContext.getCurrentContext().getRequest().getRequestURI());
        return null;
    }
}
