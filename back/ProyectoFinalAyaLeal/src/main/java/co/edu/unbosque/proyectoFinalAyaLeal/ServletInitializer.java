package co.edu.unbosque.proyectoFinalAyaLeal;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * Inicializador para desplegar la aplicación como WAR en un Tomcat externo. Tomcat usa esta clase
 * para arrancar Spring Boot en lugar del método main.
 */
public class ServletInitializer extends SpringBootServletInitializer {
    /**
     * Indica a Spring Boot cuál es la clase principal de la aplicación.
     *
     * @param application Constructor de la aplicación que entrega Tomcat
     * @return Constructor configurado con la clase principal
     */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProyectoFinalAyaLealApplication.class);
	}

}
