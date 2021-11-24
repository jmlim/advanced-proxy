package com.example.advancedproxy.pureproxy.deorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPattermClient {
    private Component component;

    public DecoratorPattermClient(Component component) {
        this.component = component;
    }

    public void execute() {
        String result = component.operation();
        log.info("result={}", result);
    }
}
