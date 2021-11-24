package com.example.advancedproxy.trace.callback;

// 템플릿 콜백 패턴 적용
public interface TraceCallback<T> {
    T call();
}
