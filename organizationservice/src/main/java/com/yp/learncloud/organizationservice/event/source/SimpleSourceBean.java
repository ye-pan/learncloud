package com.yp.learncloud.organizationservice.event.source;

import com.yp.learncloud.commons.utils.UserContextHolder;
import com.yp.learncloud.organizationservice.event.model.OrganizationChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleSourceBean {
    private Source source;

    public SimpleSourceBean(Source source) {
        this.source = source;
    }

    public void publishOrgChange(String action, String orgId) {
        OrganizationChange org = new OrganizationChange(OrganizationChange.class.getTypeName(), action,
                orgId, UserContextHolder.getContext().getCorrelationId());
        source.output().send(MessageBuilder.withPayload(org).build());
    }
}
