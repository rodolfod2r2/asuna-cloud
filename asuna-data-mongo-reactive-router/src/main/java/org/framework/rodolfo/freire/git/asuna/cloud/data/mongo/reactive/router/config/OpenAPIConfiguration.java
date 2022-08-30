package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.router.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfiguration {

    @Value("${asuna-cloud-swagger.info.title}")
    private String docInfoTitle;
    @Value("${asuna-cloud-swagger.info.description}")
    private String docInfoDescription;
    @Value("${asuna-cloud-swagger.info.version}")
    private String docInfoVersion;
    @Value("${asuna-cloud-swagger.info.termsOfServiceUrl}")
    private String docInfoTermsOfServiceUrl;
    @Value("${asuna-cloud-swagger.info.contact.name}")
    private String docInfoContactName;
    @Value("${asuna-cloud-swagger.info.contact.url}")
    private String docInfoContactUrl;
    @Value("${asuna-cloud-swagger.info.contact.email}")
    private String docInfoContactEmail;
    @Value("${asuna-cloud-swagger.info.license}")
    private String docInfoLicense;
    @Value("${asuna-cloud-swagger.info.licenseUrl}")
    private String docInfoLicenseUrl;
    @Value("${asuna-cloud-swagger.server.url}")
    private String docServerUrl;
    @Value("${asuna-cloud-swagger.server.description}")
    private String docServerDescription;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(this.docInfoTitle)
                        .description(this.docInfoDescription)
                        .version(this.docInfoVersion)
                        .contact(new Contact().name(this.docInfoContactName).email(this.docInfoContactEmail).url(this.docInfoContactUrl)
                        )
                        .license(new License().name(this.docInfoLicense).url(this.docInfoLicenseUrl)
                        )
                        .termsOfService(this.docInfoTermsOfServiceUrl)
                )
                .servers(Collections.singletonList(new Server().url(this.docServerUrl).description(this.docServerDescription)
                        )
                );
    }
}
