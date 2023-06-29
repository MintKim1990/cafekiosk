package sample.cafekiosk.spring.domain.orderproduct;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.product.Product;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class OrderProducts {

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> values = new ArrayList<>();

    public OrderProducts(Order order, List<Product> products) {
        for (Product product : products) {
            values.add(new OrderProduct(order, product));
        }
    }

    public int totalPrice() {
        return values.stream()
                .mapToInt(OrderProduct::price)
                .sum();
    }

    public List<OrderProduct> immutableOrderProducts() {
        return Collections.unmodifiableList(values);
    }

}
