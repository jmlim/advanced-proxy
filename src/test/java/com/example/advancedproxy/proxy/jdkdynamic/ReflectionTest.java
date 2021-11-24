package com.example.advancedproxy.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직 1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);
        //공통 로직 1 종료

        // 공통 로직 2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);
        // 공통 로직 2 종료
    }

    // 위의 공통 로직을 합치기
    @Test
    void reflection1() throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException {
        Class<?> classHello = Class.forName("com.example.advancedproxy.proxy.jdkdynamic.ReflectionTest$Hello");

        // callA 메서드 정보
        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        // callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    /**
     * dynamicCall(Method method, Object target) 공통 로직1, 공통 로직2를 한번에 처리할 수 있는 통합된 공통 처리 로직이다.
     * Method method: 첫 번째 파라미터는 호출할 메서드 정보가 넘어온다. 이것이 핵심이다.
     * 기존에는 메서드 이름을 직접 호출했지만, 이제는 Method라는 메타정보를 통해서 호출할 메서드 정보가 동적으로 제공된다.
     * <p>
     * Object target: 실제 실행할 인스턴스 정보가 넘어온다.
     * 타입이 Object라는 것은 어떠한 인스턴스도 받을 수 있다는 뜻이다.
     * 물론 method.invoke(target)를 사용할 때 호출할 클래스와 메서드 정보가 서로 다르면 예외가 발생한다.
     *
     * @throws Exception
     */
    // 위의 공통 로직을 합치기
    @Test
    void reflection2() throws Exception {
        Class<?> classHello = Class.forName("com.example.advancedproxy.proxy.jdkdynamic.ReflectionTest$Hello");

        // callA 메서드 정보
        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        // callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallA, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result1{}", result);
    }


    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
