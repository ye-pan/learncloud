package com.yp.learncloud.licensingservice.event.handler;

import com.yp.learncloud.licensingservice.event.CustomChannels;
import com.yp.learncloud.licensingservice.event.model.OrganizationChange;
import com.yp.learncloud.licensingservice.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(CustomChannels.class)
public class OrganizationChangeHandler {
    private OrganizationRepository organizationRepository;

    public OrganizationChangeHandler(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
    @StreamListener(CustomChannels.INPUT)
    public void loggerSink(OrganizationChange org) {
        log.info("Received an event for organization: {}", org);
        switch(org.getAction()) {
            case "UPDATE":
                organizationRepository.deleteById(org.getOrganizationId());
                break;
            case "DELETE":
                organizationRepository.deleteById(org.getOrganizationId());
                break;
            default:
                log.error("Receive an UNKNOW event from the organization service of type: {}", org.getType());
        }
    }
}
