package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request) {
        List<String> numbers = request.getNumbers();

        List<Product> orderProduct = makeOrderProduct(numbers);

        Order order = orderRepository.save(Order.create(orderProduct));
        return OrderResponse.of(order);
    }

    private List<Product> makeOrderProduct(List<String> numbers) {
        List<Product> products = productRepository.findAllByProductDetail_NumberIn(numbers);

        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::number, product -> product));

        return numbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
    }

}
