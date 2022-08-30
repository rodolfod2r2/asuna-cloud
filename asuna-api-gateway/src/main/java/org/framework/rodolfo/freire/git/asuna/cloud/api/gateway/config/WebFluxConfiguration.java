package org.framework.rodolfo.freire.git.asuna.cloud.api.gateway.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;

@Configuration
public class WebFluxConfiguration extends DelegatingWebFluxConfiguration {

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(3600L);
    }
}
