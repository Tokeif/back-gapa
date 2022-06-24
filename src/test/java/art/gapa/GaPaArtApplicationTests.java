package art.gapa;

import art.gapa.domain.collage.CollageType;
import art.gapa.domain.collage.repository.CollageCategoryRepository;
import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class GaPaArtApplicationTests {

    @Autowired
    CollageCategoryRepository collageCategoryRepository;

    @Test
    void initializeDataForTest() {
        List<CollageType> list = Lists.newArrayListWithExpectedSize(200);

        for (int i = 1; i < 200; i++) {
            String name = UUID.randomUUID().toString();
            String price = String.valueOf(i * 10);
            int numberLimit = i * 100;
            LocalDateTime releaseTime = LocalDateTime.now();

            list.add(collageCategory(name, price, numberLimit, releaseTime));
        }

        for (CollageType collage : list) {
            Assertions.assertThat(collageCategoryRepository.save(collage).getId()).isNotNull();
        }
    }

    CollageType collageCategory(String name, String price, int numberLimit, LocalDateTime releaseTime) {
        CollageType o = new CollageType();
        o.setName(name);
        o.setPrice(new BigDecimal(price));
        o.setNumberLimit(numberLimit);
        o.setReleaseTime(releaseTime);
        return o;
    }

}
