package com.mobi7.trackpoi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
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
	public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
		return openApi -> openApi.getPaths().forEach((path, pathItem) -> {

			pathItem.readOperations().forEach(operation -> {
				ApiResponses apiResponses = operation.getResponses();

				switch (path) {
				case "/api/dwells":
					apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
					apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
					apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
					apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
					break;

				case "/api/dwells/plate/{plate}":
					apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
					apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
					apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
					break;

				case "/api/dwell-engine/calculate":
					apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
					apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
					apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
					break;

				case "/api/pois":
					apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
					apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
					apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
					apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
					break;

				case "/api/vehicles/plates":
				case "/api/vehicles/positions":
					apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
					apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
					apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
					apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
					break;

				default:
					break;
				}
			});
		});
	}

	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}
}
