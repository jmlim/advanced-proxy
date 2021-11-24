package com.example.advancedproxy.app.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * RequestMapping 관련..
 *  - 스프링 MVC 는 타입에 @Controller또는 @requestMapping 애노테이션이 있어야 스프링 컨트롤러로 인식.
 *  - 스프링 컨트롤러로 인식해야 httpUrl 이 매핑되고 동작.
 *  - 그런데 여기서는 @Controller를 사용하지 않고 @RequestMapping 애노테이션 사용.
 *     ㄴ 이유: @Controller를 사용하면 자동 컴포넌트 스캔의 대상이 되기 때문. 수동 빈 등록을 할 것이기 때문에 컴포넌트 스캔이 되지 않도록 @Controller 제외
 */
@Slf4j
@RequestMapping
@ResponseBody
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;

    public OrderControllerV2(OrderServiceV2 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v2/no-log")
    public String noLog() {
        return "ok";
    }
}
