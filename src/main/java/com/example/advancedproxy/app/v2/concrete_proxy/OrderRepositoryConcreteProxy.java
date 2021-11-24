package com.example.advancedproxy.app.v2.concrete_proxy;

import com.example.advancedproxy.app.v2.OrderRepositoryV2;
import com.example.advancedproxy.trace.TraceStatus;
import com.example.advancedproxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {
    private final OrderRepositoryV2 target;
    private final LogTrace logTrace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");
            target.save(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
        super.save(itemId);
    }
}
