package sample.cafekiosk.unit;

import lombok.Getter;
import org.aspectj.weaver.ast.Or;
import sample.cafekiosk.unit.beverage.Beverage;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.time.LocalDateTime.now;

@Getter
public class CafeKiosk {

    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void add(Beverage beverage, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("음료는 1잔 이상 부터 주문할 수 있습니다.");
        }

        for (int i = 0; i < count; i++) {
            beverages.add(beverage);
        }
    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }

    public int caculateTotalPrice() {
        return beverages.stream()
                .mapToInt(Beverage::gerPrice)
                .sum();
    }

    public Order createOrder() {
        return new Order(now(), beverages);
    }

}
