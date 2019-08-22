package com.yp.learncloud.licensingservice.hystrix;

import com.yp.learncloud.commons.utils.UserContext;
import com.yp.learncloud.commons.utils.UserContextHolder;
import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<T> implements Callable<T> {

    private final Callable<T> delegate;
    private UserContext originalUserContext;
    public DelegatingUserContextCallable(Callable<T> callable, UserContext context) {
        this.delegate = callable;
        this.originalUserContext = context;
    }

    @Override
    public T call() throws Exception {
        //import 如果Hystrix使用的时THREAD隔离策略，这里将父线程的上下文注入到了Hystrix管理命令线程中
        UserContextHolder.setContext(originalUserContext);
        try {
            return delegate.call();
        } finally {
            originalUserContext = null;
        }
    }
}
