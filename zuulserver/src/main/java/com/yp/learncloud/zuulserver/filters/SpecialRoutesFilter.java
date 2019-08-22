package com.yp.learncloud.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yp.learncloud.zuulserver.model.AbTestingRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * TODO 过于复杂待重新实现
 */
@Slf4j
public class SpecialRoutesFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    private FilterUtils filterUtils;

    private RestTemplate restTemplate;

    public SpecialRoutesFilter(FilterUtils filterUtils, RestTemplate restTemplate) {
        this.filterUtils = filterUtils;
        this.restTemplate = restTemplate;
    }

    @Override
    public String filterType() {
        return FilterUtils.ROUTE_FILTER_TYPE;
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
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        AbTestingRoute route = getAbRoute(filterUtils.getServiceId());
        if(route != null && useSpecialRoute(route)) {
            String routeUrl = buildRouteString(context.getRequest().getRequestURI(), route.getEndpoint(), context.get("serviceId").toString());
            forwardToSpecialRoute(routeUrl);
        }
        return null;
    }

    private void forwardToSpecialRoute(String routeUrl) {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        context.getZuulRequestHeaders();
        context.getOriginResponseHeaders();
        request.getParameterMap();

    }

    private String buildRouteString(String oldEndpoint, String newEndpoint, String serviceName) {
        int index = oldEndpoint.indexOf(serviceName);
        String strippedRoute = oldEndpoint.substring(index + serviceName.length());
        String targetUrl = String.format("%s/%s", newEndpoint, strippedRoute);
        log.info("Target route:{}", targetUrl);
        return targetUrl;
    }

    private boolean useSpecialRoute(AbTestingRoute route) {
        Random random = new Random();
        if(route.getActive().equals("N")) {
            return false;
        }
        int value = random.nextInt(11);
        return route.getWeight() < value;
    }

    private AbTestingRoute getAbRoute(String serviceName) {
        try {
            return restTemplate.getForObject("http://specialrouteservice/v1/route/abtesting/{serviceName}", AbTestingRoute.class, serviceName);
        } catch(HttpClientErrorException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }
}
