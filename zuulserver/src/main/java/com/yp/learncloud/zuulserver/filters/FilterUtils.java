package com.yp.learncloud.zuulserver.filters;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "tmx-auth-token";
    public static final String USER_ID        = "tmx-user-id";
    public static final String ORG_ID         = "tmx-org-id";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";

    public String getCorrelationId() {
        RequestContext context = RequestContext.getCurrentContext();
        if(context.getRequest().getHeader(CORRELATION_ID) != null) {
            return context.getRequest().getHeader(CORRELATION_ID);
        } else {
            return context.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId) {
        RequestContext.getCurrentContext().addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    public String getOrgId() {
        if(RequestContext.getCurrentContext().getRequest().getHeader(ORG_ID) != null) {
            return RequestContext.getCurrentContext().getRequest().getHeader(ORG_ID);
        } else {
            return RequestContext.getCurrentContext().getZuulRequestHeaders().get(ORG_ID);
        }
    }

    public void setOrgId(String orgId) {
        RequestContext.getCurrentContext().addZuulRequestHeader(ORG_ID, orgId);
    }

    public String getUserId() {
        if(RequestContext.getCurrentContext().getRequest().getHeader(USER_ID) != null) {
            return RequestContext.getCurrentContext().getRequest().getHeader(USER_ID);
        } else {
            return RequestContext.getCurrentContext().getZuulRequestHeaders().get(USER_ID);
        }
    }

    public void setUserId(String userId) {
        RequestContext.getCurrentContext().addZuulRequestHeader(USER_ID, userId);
    }

    public String getAuthToken() {
        return RequestContext.getCurrentContext().getRequest().getHeader(AUTH_TOKEN);
    }

    public String getServiceId() {
        Object serviceId = RequestContext.getCurrentContext().get("serviceId");
        return serviceId != null ? serviceId.toString() : "";
    }
}
