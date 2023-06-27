package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CafeKioskTest {

    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void addSeveralBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano(), 2);

        assertThat(cafeKiosk.getBeverages()).hasSize(2);
        assertThat(cafeKiosk.caculateTotalPrice()).isEqualTo(8000);
    }

    @Test
    void addZeroBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        assertThatThrownBy(() -> cafeKiosk.add(new Americano(), 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 부터 주문할 수 있습니다.");
    }

    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();

        cafeKiosk.add(new Americano());
        cafeKiosk.add(new Latte());

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

}