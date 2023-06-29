package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.order.OrderStatus.INIT;
import static sample.cafekiosk.spring.domain.product.ProductMother.*;

class OrderTest {

    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산합니다.")
    @Test
    void totalPrice() {
        // given
        List<Product> products = List.of(createAmericano(), createLatte(), createBakery());

        // when
        Order order = Order.create(products);

        // then
        assertThat(order.getTotalPrice()).isEqualTo(13000);
    }

    @DisplayName("주문 생성 시 주문 상태는 INIT 이여야 합니다.")
    @Test
    void init() {
        // given
        List<Product> products = List.of(createAmericano(), createLatte(), createBakery());

        // when
        Order order = Order.create(products);

        // then
        assertThat(order.getStatus()).isEqualTo(INIT);
    }

}