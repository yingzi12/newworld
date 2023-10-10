package com.xinshijie.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"com.xinshijie"};
        return GroupedOpenApi.builder().group("后台管理")
                .pathsToMatch(paths)
                .addOperationCustomizer((operation, handlerMethod) -> {
                    return operation.addParametersItem(
                                    new HeaderParameter().name("Authorization")
                                            .example("测试")
                                            .description("access token")
                                            .schema(new StringSchema()._default("BR").name("Authorization").description("access token")
                                            ));
                })
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("super-app  admin API")
                        .version("1.0")
                        .description("admin后台管理")
                        .termsOfService("https://uat-api.jtexpress.sg/admin/")
                        .license(new License().name("Apache 2.0")
                                .url("https://uat-api.jtexpress.sg/admin/doc.html"))
                )
                ;
    }


}