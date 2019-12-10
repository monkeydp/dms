package com.monkeydp.daios.dms.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * @author iPotato
 * @date 2019/4/22
 */
@Configuration
@EnableSwagger2
class DmsSwagger {

    @Bean
    internal fun dmsApis(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.monkeydp.daios.dms"))
                .paths(PathSelectors.any())
                .build()
    }

    private val apiInfo: ApiInfo
        get() = ApiInfoBuilder()
                .title("DMS Apis")
                .description("Data Management Service Apis")
                .version("0.0.1")
                .build()
}