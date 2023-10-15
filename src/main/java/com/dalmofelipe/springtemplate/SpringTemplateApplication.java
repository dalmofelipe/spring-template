package com.dalmofelipe.springtemplate;

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
		version = "0.0.1",
		description = "Repositório de referência para projeto com Spring Boot.\n\nAPI de Usuários",
		license = @License(name = "Apache 2.0"),
		contact = @Contact(url = "http://github.com/dalmofelipe", name = "Dalmo", email = "dalmofelipe.dev@gmail.com")
	)
)
@SpringBootApplication()
public class SpringTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTemplateApplication.class, args);
	}

}
