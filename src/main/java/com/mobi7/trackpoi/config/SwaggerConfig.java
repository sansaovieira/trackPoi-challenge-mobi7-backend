package com.mobi7.trackpoi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Mobi7 TrackPOI API").version("1.0").description(
				"API para gestão de eventos de permanência (dwell events), pontos de interesse (POIs) e posições de veículos."));
	}

	@Bean
	public GroupedOpenApi trackpoiApi() {
		return GroupedOpenApi.builder().group("trackpoi").packagesToScan("com.mobi7.trackpoi.controller").build();
	}

	@Bean
	public OpenApiCustomizer globalResponsesCustomizer() {
		return openApi -> {
			openApi.getPaths().forEach((path, pathItem) -> {
				pathItem.readOperations().forEach(operation -> {
					ApiResponses responses = operation.getResponses();
					if (operation.getTags().contains("Dwell Events") || operation.getTags().contains("Dwell Engine")
							|| operation.getTags().contains("POIs") || operation.getTags().contains("Veículos")) {
						responses.addApiResponse("200", createApiResponse("Sucesso!"));
						responses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
						responses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
						responses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
						responses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
						responses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
						responses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
					}
				});
			});
		};
	}

	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}
}
