package co.edu.unbosque.proyectoFinalAyaLeal.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    String descripcionPrincipal =
        "<h2>API del sistema académico SALA</h2>"
            + "<p>Esta API replica funciones del Sistema Académico SALA de la Universidad El"
            + " Bosque. Los recursos están protegidos con JWT.</p>"
            + "<h3>Conceptos básicos:</h3>"
            + "<ul>"
            + "<li><strong>JWT (JSON Web Token)</strong>: token de acceso que recibes al iniciar"
            + " sesión y que se envía en las peticiones siguientes.</li>"
            + "<li><strong>Autenticación</strong>: verificar tu identidad con correo y"
            + " contraseña.</li>"
            + "<li><strong>Autorización</strong>: qué puede hacer cada usuario según su rol.</li>"
            + "</ul>"
            + "<h3>Flujo básico de uso:</h3>"
            + "<ol>"
            + "<li>Inicia sesión con <code>/auth/login</code> para obtener un token JWT.</li>"
            + "<li>Haz clic en <strong>Authorize</strong> y pega el token.</li>"
            + "<li>Usa los endpoints protegidos según tu rol.</li>"
            + "</ol>"
            + "<h3>Roles de usuario:</h3>"
            + "<ul>"
            + "<li><strong>ESTUDIANTE</strong></li>"
            + "<li><strong>DOCENTE</strong></li>"
            + "<li><strong>ADMIN</strong>: además gestiona usuarios y consulta la auditoría.</li>"
            + "</ul>"
            + "<h3>Códigos de estado HTTP comunes:</h3>"
            + "<ul>"
            + "<li><strong>200/201</strong>: operación exitosa</li>"
            + "<li><strong>400</strong>: error en la solicitud (datos incorrectos)</li>"
            + "<li><strong>401</strong>: no autenticado (token inválido, expirado o"
            + " ausente)</li>"
            + "<li><strong>403</strong>: no autorizado (el rol no tiene permiso)</li>"
            + "<li><strong>404</strong>: recurso no encontrado</li>"
            + "<li><strong>409</strong>: conflicto (por ejemplo, correo ya registrado)</li>"
            + "</ul>";

    String descripcionSeguridad =
        "Autenticación mediante JWT (JSON Web Token)."
            + "<p>Para autenticarte, sigue estos pasos:</p>"
            + "<ol>"
            + "<li>Obtén un token con el endpoint <code>/auth/login</code>.</li>"
            + "<li>Copia el valor de <code>token</code> de la respuesta.</li>"
            + "<li>Haz clic en el botón <strong>Authorize</strong> de esta página.</li>"
            + "<li>En el campo <strong>Value</strong> pega solo el token. Swagger agrega la"
            + " palabra <code>Bearer</code> por ti.</li>"
            + "<li>Haz clic en <strong>Authorize</strong> y luego en <strong>Close</strong>.</li>"
            + "</ol>";

    Info info =
        new Info()
            .title("API SALA - Proyecto final DSI3")
            .version("1.0")
            .description(descripcionPrincipal)
            .contact(
                new Contact()
                    .name("Equipo del proyecto final DSI3")
                    .url("https://github.com/DWBstudent/proyectofinalDSI320262"))
            .license(new License().name("Uso académico - Universidad El Bosque"));

    SecurityScheme esquemaSeguridad =
        new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .description(descripcionSeguridad);

    return new OpenAPI()
        .info(info)
        .components(
            new Components()
                .addSecuritySchemes("bearerAuth", esquemaSeguridad)
                .addResponses(
                    "UnauthorizedError",
                    new ApiResponse()
                        .description("No autenticado: token JWT inválido, expirado o ausente"))
                .addResponses(
                    "ForbiddenError",
                    new ApiResponse()
                        .description("Acceso prohibido: el rol no tiene permiso para esta operación"))
                .addResponses(
                    "NotFoundError", new ApiResponse().description("Recurso no encontrado")));
  }
}