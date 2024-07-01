package top.easylove.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Contact contact = new Contact()
                .name("Eucotopia")
                .email("3499508634@qq.com")
                .url("https://www.easylove.top/blog/")
                .extensions(new HashMap<>());

        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                .identifier("Apache-2.0")
                .extensions(new HashMap<>());

        Info info = new Info()
                .title("Swagger3.0 (Open API) 框架学习示例文档")
                .description("学习Swagger框架而用来定义测试的文档")
                .version("1.2.1")
                .termsOfService("https://example.com/")
                .license(license)
                .contact(contact);
        // 返回信息
        return new OpenAPI()
                .openapi("3.0.1")
                .info(info);
    }
}
