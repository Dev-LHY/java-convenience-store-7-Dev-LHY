package store.model;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void 상품이_정상적으로_만들어지는지1() {
        List<String> textLines = new ArrayList<>();
        textLines.add("콜라,1000,10,탄산2+1");
        List<Product> products = Product.createProducts(textLines);
        Assertions.assertThat(products.getFirst().getName()).isEqualTo("콜라");
        Assertions.assertThat(products.getFirst().getPrice()).isEqualTo(1000);
        Assertions.assertThat(products.getFirst().getQuantity()).isEqualTo(10);
        Assertions.assertThat(products.getFirst().getPromotion()).isEqualTo("탄산2+1");
    }

    @Test
    void 상품이_정상적으로_만들어지는지2() {
        List<String> textLines = new ArrayList<>();
        textLines.add("컵라면,1700,10,null");
        List<Product> products = Product.createProducts(textLines);
        Assertions.assertThat(products.getFirst().getName()).isEqualTo("컵라면");
        Assertions.assertThat(products.getFirst().getPrice()).isEqualTo(1700);
        Assertions.assertThat(products.getFirst().getQuantity()).isEqualTo(10);
        Assertions.assertThat(products.getFirst().getPromotion()).isEqualTo(null);
    }
}