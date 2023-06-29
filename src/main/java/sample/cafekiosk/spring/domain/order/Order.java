package sample.cafekiosk.spring.domain.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.BaseEntity;
import sample.cafekiosk.spring.domain.orderproduct.OrderProduct;
import sample.cafekiosk.spring.domain.orderproduct.OrderProducts;
import sample.cafekiosk.spring.domain.product.Product;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int totalPrice;

    private LocalDateTime orderDateTime;

    @Embedded
    private OrderProducts orderProducts;

    private Order(List<Product> products) {
        this.status = OrderStatus.INIT;
        this.orderDateTime = LocalDateTime.now();
        this.orderProducts = new OrderProducts(this, products);
        this.totalPrice = orderProducts.totalPrice();
    }

    public static Order create(List<Product> products) {
        return new Order(products);
    }

    public List<OrderProduct> orderProducts() {
        return orderProducts.immutableOrderProducts();
    }

}
