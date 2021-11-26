package com.example.advancedproxy.config.v6_aop;

import com.example.advancedproxy.config.AppV1Config;
import com.example.advancedproxy.config.AppV2Config;
import com.example.advancedproxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }
}
