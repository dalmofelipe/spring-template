package com.dalmofelipe.springtemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

// https://springdoc.org/#can-i-customize-openapi-object-programmatically
// https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#OpenAPIDefinition
// https://www.bezkoder.com/swagger-3-annotations/
// https://springdoc.org/#migrating-from-springfox
@OpenAPIDefinition(
	info = @Info(
		title = "String Template",
		version = "0.0.1-"+"${application.environment}",
		description = "Repositório de referência para projeto com Spring Boot.\n\nAPI de Usuários",
		license = @License(name = "Apache 2.0"),
		contact = @Contact(url = "${application.repository}", name = "${application.author}", 
			email = "${application.author.email}")
	)
)
@SpringBootApplication()
public class SpringTemplateApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringTemplateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringTemplateApplication.class, args);
		log.info("OK - Running");
	}

}
