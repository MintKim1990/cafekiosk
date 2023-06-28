package sample.cafekiosk.spring.domain.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ProductDetail productDetail;

    @Enumerated(EnumType.STRING)
    private ProductSellingStatus sellingStatus;

    public Product(String number, String name, int price,
                   ProductType type, ProductSellingStatus sellingStatus) {
        this.productDetail = new ProductDetail(number, name, price, type);
        this.sellingStatus = sellingStatus;
    }

    public String number() {
        return productDetail.getNumber();
    }

    public String name() {
        return productDetail.getName();
    }

    public int price() {
        return productDetail.getPrice();
    }

    public ProductType type() {
        return productDetail.getType();
    }


}
