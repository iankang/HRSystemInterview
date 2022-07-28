package com.kangethe.hrsystem.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringFoxConfig {

    private final String SCHEME_NAME = "bearerScheme";
    private final String SCHEME = "Bearer";


    private ApiKey apiKey() {
        return new ApiKey("Authorization",  // name: My key - Authorization
                "api_key",  // keyname: api_key
                "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }


    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme().name(SCHEME_NAME).type(SecurityScheme.Type.HTTP).scheme(SCHEME);
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("HRS Policy Portal")).components(new Components().addSecuritySchemes(SCHEME_NAME, createSecurityScheme())).addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));

    }

}
