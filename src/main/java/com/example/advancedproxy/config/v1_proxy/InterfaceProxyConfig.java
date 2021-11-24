package com.example.advancedproxy.config.v1_proxy;

import com.example.advancedproxy.app.v1.*;
import com.example.advancedproxy.app.v1.interface_proxy.OrderControllerInterfaceProxy;
import com.example.advancedproxy.app.v1.interface_proxy.OrderRepositoryInterfaceProxy;
import com.example.advancedproxy.app.v1.interface_proxy.OrderServiceInterfaceProxy;
import com.example.advancedproxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1Impl orderServiceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(orderServiceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepository, logTrace);
    }
}
