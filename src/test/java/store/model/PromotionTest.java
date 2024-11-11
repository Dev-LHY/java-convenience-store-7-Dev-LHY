package store.model;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @Test
    void 프로모션이_정상적으로_만들어지는지1() {
        List<String> textLines = new ArrayList<>();
        textLines.add("탄산2+1,2,1,2024-01-01,2024-12-31");
        List<Promotion> promotions = Promotion.createPromotions(textLines);
        Assertions.assertThat(promotions.getFirst().getName()).isEqualTo("탄산2+1");
        Assertions.assertThat(promotions.getFirst().getBuy()).isEqualTo(2);
        Assertions.assertThat(promotions.getFirst().getGet()).isEqualTo(1);
        Assertions.assertThat(promotions.getFirst().getStart_date()).isEqualTo("2024-01-01");
        Assertions.assertThat(promotions.getFirst().getEnd_date()).isEqualTo("2024-12-31");
    }

    @Test
    void 프로모션이_정상적으로_만들어지는지2() {
        List<String> textLines = new ArrayList<>();
        textLines.add("MD추천상품,1,1,2024-01-01,2024-12-31");
        List<Promotion> promotions = Promotion.createPromotions(textLines);
        Assertions.assertThat(promotions.getFirst().getName()).isEqualTo("MD추천상품");
        Assertions.assertThat(promotions.getFirst().getBuy()).isEqualTo(1);
        Assertions.assertThat(promotions.getFirst().getGet()).isEqualTo(1);
        Assertions.assertThat(promotions.getFirst().getStart_date()).isEqualTo("2024-01-01");
        Assertions.assertThat(promotions.getFirst().getEnd_date()).isEqualTo("2024-12-31");
    }


}