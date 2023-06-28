package sample.cafekiosk.spring.domain.product;

import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.BAKERY;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

public class ProductMother {

    public static Product createAmericano() {
        return new Product("001", "아메리카노", 4000, HANDMADE, SELLING);
    }

    public static Product createLatte() {
        return new Product("002", "카페라떼", 4500, HANDMADE, HOLD);
    }

    public static Product createBakery() {
        return new Product("003", "크루아상", 4500, BAKERY, STOP_SELLING);
    }

}
