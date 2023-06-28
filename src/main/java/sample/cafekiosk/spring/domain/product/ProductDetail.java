package sample.cafekiosk.spring.domain.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDetail {

    private String number;

    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    public ProductDetail(String number, String name, int price, ProductType type) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetail)) return false;
        ProductDetail that = (ProductDetail) o;
        return price == that.price && Objects.equals(number, that.number) && Objects.equals(name, that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, price, type);
    }
}
