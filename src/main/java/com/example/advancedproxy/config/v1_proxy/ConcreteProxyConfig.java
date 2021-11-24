package com.example.advancedproxy.config.v1_proxy;

import com.example.advancedproxy.app.v2.OrderControllerV2;
import com.example.advancedproxy.app.v2.OrderRepositoryV2;
import com.example.advancedproxy.app.v2.OrderServiceV2;
import com.example.advancedproxy.app.v2.concrete_proxy.OrderControllerConcreteProxy;
import com.example.advancedproxy.app.v2.concrete_proxy.OrderRepositoryConcreteProxy;
import com.example.advancedproxy.app.v2.concrete_proxy.OrderServiceConcreteProxy;
import com.example.advancedproxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {
    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace) {
        OrderControllerV2 controllerImpl = new OrderControllerV2(orderService(logTrace));
        return new OrderControllerConcreteProxy(controllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace logTrace) {
        OrderServiceV2 orderServiceImpl = new OrderServiceV2(orderRepository(logTrace));
        return new OrderServiceConcreteProxy(orderServiceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
        OrderRepositoryV2 orderRepositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(orderRepositoryImpl, logTrace);
    }
}
