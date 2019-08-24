package com.yp.learncloud.licensingservice.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义消息通道
 */
public interface CustomChannels {

    String INPUT = "inboundOrgChanges";

    @Input(INPUT)
    SubscribableChannel orgs();
}
