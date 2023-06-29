package sample.cafekiosk.spring.api.controller.order.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    private List<String> numbers;

    public OrderCreateRequest(List<String> numbers) {
        this.numbers = numbers;
    }
}
