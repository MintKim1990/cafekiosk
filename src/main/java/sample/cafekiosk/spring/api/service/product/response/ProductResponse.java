package sample.cafekiosk.spring.api.service.product.response;

import lombok.Builder;
import lombok.Data;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;


@Data
public class ProductResponse {

    private Long id;
    private String number;
    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;

    @Builder
    private ProductResponse(Long id, String number, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .number(product.number())
                .type(product.type())
                .sellingStatus(product.getSellingStatus())
                .name(product.name())
                .price(product.price())
                .build();
    }

}
