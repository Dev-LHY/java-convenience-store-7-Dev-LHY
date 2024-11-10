package store.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.util.TextFileLoader;

class StockManagerTest {
    StockManager stockManager = StockManager.getInstance();
    List<Product> products;
    List<Promotion> promotions;

    @BeforeEach
    void init() throws IOException {
        List<String> product = new ArrayList<>();
        product.add("콜라,1000,10,탄산2+1");
        product.add("콜라,1000,5,null");
        product.add("사이다,1000,10,탄산2+1");
        products = Product.createProducts(product);

        List<String> promotion = new ArrayList<>();
        promotion.add("MD추천상품,1,1,2024-01-01,2024-12-31");
        promotion.add("반짝할인,1,1,2024-11-01,2024-11-30");
        promotions = Promotion.createPromotions(TextFileLoader.promotionsLoader());
    }

    @Test
    void 상품_등록_테스트() {
        stockManager.addAllProducts(products);
        Map<String, List<Product>> stock = stockManager.getStock();

        Assertions.assertThat(stock).containsKeys("콜라", "사이다");
        Assertions.assertThat(stock.get("콜라").getFirst().getQuantity()).isEqualTo(10);
        Assertions.assertThat(stock.get("사이다").getFirst().getQuantity()).isEqualTo(10);
        Assertions.assertThat(stock.get("콜라").getFirst().getPrice()).isEqualTo(1000);
        Assertions.assertThat(stock.get("사이다").getFirst().getPromotion()).isEqualTo("탄산2+1");
    }

    @Test
    void 해당_상품_전체_수량_반환_테스트() {
        stockManager.reset();
        stockManager.addAllProducts(products);
        Map<String, List<Product>> stock = stockManager.getStock();
        Assertions.assertThat(stockManager.getTotalQuantityByName("콜라")).isEqualTo(15);
    }

    @Test
    void 해당_상품_프로모션상품_수량_반환_테스트() {
        stockManager.reset();
        stockManager.addAllProducts(products);
        Map<String, List<Product>> stock = stockManager.getStock();
        Assertions.assertThat(stockManager.getPromotionQuantityByName("콜라")).isEqualTo(10);
    }

    @Test
    void 해당_상품_일반상품_수량_반환_테스트() {
        stockManager.reset();
        stockManager.addAllProducts(products);
        Map<String, List<Product>> stock = stockManager.getStock();
        Assertions.assertThat(stockManager.getNonePromotionQuantityByName("콜라")).isEqualTo(5);
    }
}