package store.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductPriceTest {

    @ParameterizedTest
    @CsvSource({"콜라, 1000", "사이다, 1000", "비타민워터, 1500", "감자칩, 1500", "정식도시락, 6400"})
    void 이름으로_상품가격을_가지고오기(String name, int expected) {
        Assertions.assertThat(ProductPrice.getPriceByName(name)).isEqualTo(expected);
    }
}