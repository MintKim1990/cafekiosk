package sample.cafekiosk.spring.api.service.order;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.orderproduct.OrderProductRepository;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductMother.*;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderService orderService;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
    }

    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder() {

        // given
        productRepository.saveAll(List.of(createAmericano(), createLatte(), createBakery()));
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(List.of("001", "002"));

        // when
        OrderResponse orderResponse = orderService.createOrder(orderCreateRequest);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse.getTotalPrice()).isEqualTo(8500);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("number", "price")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("001", 4000),
                        Tuple.tuple("002", 4500)
                );

    }

    @DisplayName("중복되는 상품번호 리스트로 주문을 생성할 수 있다.")
    @Test
    void createOrderWithDuplicateProductNumbers() {

        // given
        productRepository.saveAll(List.of(createAmericano(), createLatte(), createBakery()));
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(List.of("001", "001"));

        // when
        OrderResponse orderResponse = orderService.createOrder(orderCreateRequest);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse.getTotalPrice()).isEqualTo(8000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("number", "price")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("001", 4000),
                        Tuple.tuple("001", 4000)
                );

    }
}