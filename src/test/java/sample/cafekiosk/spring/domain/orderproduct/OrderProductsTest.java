package sample.cafekiosk.spring.domain.orderproduct;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.product.ProductMother.*;

class OrderProductsTest {

    @DisplayName("주문상품은 입력된 상품에 총 가격의 합을 제공합니다.")
    @Test
    void totalPrice() {

        // given
        OrderProducts orderProducts = new OrderProducts(null, List.of(createAmericano(), createLatte(), createBakery()));

        // when
        int totalPrice = orderProducts.totalPrice();

        // then
        assertThat(totalPrice).isEqualTo(13000);
    }
}