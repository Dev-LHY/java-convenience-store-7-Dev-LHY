package store.model;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {
    Customer customer;

    @BeforeEach
    public void init() {
        this.customer = new Customer("[콜라-3]");
    }

    @Test
    void 하나의_상품이_추가되는지() {
        Map<String, Integer> shoppingCart = customer.getShoppingCart();
        customer.addQuantity("콜라");
        Assertions.assertThat(shoppingCart.get("콜라")).isEqualTo(4);
    }

    @Test
    void 원하는_만큼_상품이_줄어드는지() {
        Customer customer = new Customer("[콜라-3]");
        Map<String, Integer> shoppingCart = customer.getShoppingCart();
        customer.reduceQuantity("콜라", 2);
        Assertions.assertThat(shoppingCart.get("콜라")).isEqualTo(1);
    }
}