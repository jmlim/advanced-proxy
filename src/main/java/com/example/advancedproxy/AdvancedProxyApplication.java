package com.example.advancedproxy;

import com.example.advancedproxy.config.v1_proxy.InterfaceProxyConfig;
import com.example.advancedproxy.trace.logtrace.LogTrace;
import com.example.advancedproxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({InterfaceProxyConfig.class})
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
