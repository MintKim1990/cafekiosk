package sample.cafekiosk.spring.api.service.order.response;

import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.order.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {

    private Long id;
    private int totalPrice;
    private LocalDateTime orderDateTime;
    private List<ProductResponse> products;

    @Builder
    private OrderResponse(Long id, int totalPrice, LocalDateTime orderDateTime, List<ProductResponse> products) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderDateTime = orderDateTime;
        this.products = products;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .orderDateTime(order.getOrderDateTime())
                .products(makeProductResponse(order))
                .build();
    }

    private static List<ProductResponse> makeProductResponse(Order order) {
        return order.orderProducts()
                .stream()
                .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                .collect(Collectors.toList());
    }
}
