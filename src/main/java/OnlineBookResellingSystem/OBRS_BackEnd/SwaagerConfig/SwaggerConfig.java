package OnlineBookResellingSystem.OBRS_BackEnd.SwaagerConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI mySwaggerConfiguration()
    {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Online Book Reselling System Api's")
                                .description("By Faulty_Coder(E Sai Ganesh)"));
    }
}
