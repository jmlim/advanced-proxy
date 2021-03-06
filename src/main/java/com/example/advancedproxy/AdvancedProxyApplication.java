package com.example.advancedproxy;

import com.example.advancedproxy.config.v5_autoproxy.AutoProxyConfig;
import com.example.advancedproxy.config.v6_aop.AopConfig;
import com.example.advancedproxy.trace.logtrace.LogTrace;
import com.example.advancedproxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({AopConfig.class})
// @Import({BeanPostProcessorConfig.class})
//@Import(ProxyFactoryConfigV2.class)
// @Import(ProxyFactoryConfigV1.class)
// @Import(DynamicProxyFilterConfig.class)
// @Import(DynamicProxyBasicConfig.class)
// @Import(ConcreteProxyConfig.class)
//@Import({InterfaceProxyConfig.class})
//@Import({AppV1Config.class, AppV2Config.class})
@SpringBootApplication(scanBasePackages = "com.example.advancedproxy.app")
public class AdvancedProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
