package co.edu.unbosque.proyectoFinalAyaLeal;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    /**
     * Indica a Tomcat cual es la clase principal de la aplicacion.
     */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProyectoFinalAyaLealApplication.class);
	}

}
