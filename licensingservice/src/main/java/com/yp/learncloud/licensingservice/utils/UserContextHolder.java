package com.yp.learncloud.licensingservice.utils;

import org.springframework.util.Assert;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> USER_CONTEXT = ThreadLocal.withInitial(UserContext::new);

    public static final UserContext getContext() {
        return USER_CONTEXT.get();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        USER_CONTEXT.set(context);
    }
}
