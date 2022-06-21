package art.gapa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author JoverZhang
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "GaPa.art", version = "1.0.0"))
public class GaPaArtApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaPaArtApplication.class, args);
    }

}
