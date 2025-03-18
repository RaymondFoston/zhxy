package com.practice.zhxy.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("网站-API文档")
                        .description("本文档描述了网站微服务接口定义")
                        .version("1.0")
                        .contact(new Contact()
                                .name("智慧校园-swagger")
                                .url("http://localhost:9001/swagger-ui/index.html")
                                .email("email@email.com")));
    }

    @Bean
    public OperationCustomizer addGlobalHeaders() {
        return (operation, handlerMethod) -> {
            // 添加 userId 请求头参数
            Parameter userIdParameter = new HeaderParameter()
                    .name("userId")
                    .description("用户ID")
                    .required(false)
                    .schema(new io.swagger.v3.oas.models.media.StringSchema()._default("1"));
            operation.addParametersItem(userIdParameter);

            // 添加 userTempId 请求头参数
            Parameter userTempIdParameter = new HeaderParameter()
                    .name("userTempId")
                    .description("临时用户ID")
                    .required(false)
                    .schema(new io.swagger.v3.oas.models.media.StringSchema()._default("1"));
            operation.addParametersItem(userTempIdParameter);

            return operation;
        };
    }
}

