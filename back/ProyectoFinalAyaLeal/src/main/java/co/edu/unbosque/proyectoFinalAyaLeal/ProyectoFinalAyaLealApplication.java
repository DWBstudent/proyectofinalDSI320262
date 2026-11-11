package co.edu.unbosque.proyectoFinalAyaLeal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Clase principal del back del proyecto final, es una pagina web  tipo SALA de la universidad el bosque . Es el punto de
 * entrada de la aplicación y declara los beans que se usan en todo el contexto.
 */
@SpringBootApplication
public class ProyectoFinalAyaLealApplication {

  /**
   * este es el metodo principal que incia la app en sprting 
   */
  public static void main(String[] args) {
    SpringApplication.run(ProyectoFinalAyaLealApplication.class, args);
  }

  /**
   * luego creamos el bean estos son los servicios que se usan para convetir la info 
   */
  @Bean
  public ModelMapper getModelMapper() {
    return new ModelMapper();
  }
}