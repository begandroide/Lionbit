package cl.lionbit.sga.config;


import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static cl.lionbit.sga.constans.Paths.VERSION;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).select()
                /*.apis(RequestHandlerSelectors
                        .basePackage("cl.lionbit.sga"))
                //.Paths(PathSelectors.regex("/.*"))
                .Paths(PathSelectors.any())*/
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build().pathMapping("/")
                .apiInfo(apiEndPointsInfo())
                .pathMapping("/").useDefaultResponseMessages(false);
    }

    private ApiInfo apiEndPointsInfo() {

        return new ApiInfoBuilder().title("Sga REST API")
                .contact(new Contact("Francisco Reyes", "", "francisco.reyesg.14@sansano.usm.cl"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version(VERSION + "-SNAPSHOT")
                .build();
    }


}
