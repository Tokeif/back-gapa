package art.gapa;

import art.gapa.domain.collage.CollageSeries;
import art.gapa.domain.collage.CollageType;
import art.gapa.domain.collage.repository.CollageTypeRepository;
import art.gapa.domain.collage.repository.CollageSeriesRepository;
import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class GaPaArtApplicationTests {

    @Autowired
    CollageSeriesRepository collageSeriesRepository;

    @Autowired
    CollageTypeRepository collageTypeRepository;

    @Test
    void initializeDataForTest() {
        List<CollageType> list = Lists.newArrayListWithExpectedSize(200);

        for (int i = 1; i <= 3; i++) {
            CollageSeries series = collageSeries(
                    "系列" + i,
                    "系列作者" + i,
                    "系列描述" + i);
            collageSeriesRepository.save(series);
            for (int j = 1; j <= i * 3; j++) {
                CollageType collageType = collageType(
                        "藏品" + i + "-" + j,
                        series,
                        String.valueOf(j * 10),
                        j * 100,
                        j * 50,
                        "2022-07-0" + j + "T00:00:00.000000",
                        "https://static.ibox.art/file/oss/test/image/nft-goods/2be99fdd12b449c5b8455c4af41434f5.jpg?style=st6",
                        "藏品描述" + i + "-" + j
                );
                list.add(collageType);
            }
        }

        for (CollageType collage : list) {
            Assertions.assertThat(collageTypeRepository.save(collage).getId()).isNotNull();
        }
    }

    CollageSeries collageSeries(String name, String autherName, String description) {
        CollageSeries o = new CollageSeries();
        o.setName(name);
        o.setAutherName(autherName);
        o.setDescription(description);
        return o;
    }

    CollageType collageType(String name, CollageSeries series, String releasePrice, int releaseQuantity,
                            int circulationQuantity, String releaseTime, String picture, String description) {
        CollageType o = new CollageType();
        o.setName(name);
        o.setSeries(series);
        o.setReleasePrice(new BigDecimal(releasePrice));
        o.setReleaseQuantity(releaseQuantity);
        o.setCirculationQuantity(circulationQuantity);
        o.setReleaseTime(LocalDateTime.parse(releaseTime));
        o.setPicture(picture);
        o.setDescription(description);
        return o;
    }

}
