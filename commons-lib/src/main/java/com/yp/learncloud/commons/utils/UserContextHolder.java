package com.yp.learncloud.commons.utils;

import com.google.common.base.Preconditions;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> USER_CONTEXT = ThreadLocal.withInitial(UserContext::new);

    public static UserContext getContext() {
        return USER_CONTEXT.get();
    }

    public static void setContext(UserContext context) {
        Preconditions.checkNotNull(context, "Only non-null UserContext instances are permitted");
        USER_CONTEXT.set(context);
    }
}
