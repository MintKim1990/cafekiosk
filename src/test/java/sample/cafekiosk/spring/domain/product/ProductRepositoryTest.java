package sample.cafekiosk.spring.domain.product;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductMother.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.HOLD;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상태를 가진 상품들을 조회")
    @Test
    void findAllBySellingStatusIn() {
        // given
        productRepository.saveAll(List.of(createAmericano(), createLatte(), createBakery()));

        // when
        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        // then
        assertThat(products).hasSize(2)
                .extracting("productDetail") // 결과에서 작성만 필드만 추출하여 리스트 반환
                .containsExactlyInAnyOrder(
                        new ProductDetail("001", "아메리카노", 4000, HANDMADE),
                        new ProductDetail("002", "카페라떼", 4500, HANDMADE)
                ); // 추출한 리스트 필드에 값을 비교 순서 상관 X
    }

}