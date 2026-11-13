package co.edu.unbosque.proyectoFinalAyaLeal.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.unbosque.proyectoFinalAyaLeal.model.Usuario;
import co.edu.unbosque.proyectoFinalAyaLeal.repository.UsuarioRepository;

// CARGA de datos para iniciar la aplicacion 
@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Value("${admin.correo}")
  private String adminCorreo;

  @Value("${admin.contrasena}")
  private String adminContraseña;

  // creacion de administrador si no existe 
  @Bean
  CommandLineRunner initDatabase(UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder) {
    return args -> {
      if (usuarioRepo.existsByCorreo(adminCorreo)) {
        log.info("El administrador ya existe, se omite su creacion");
      } else {
        Usuario admin =
            new Usuario(
                "Administrador SALA",
                adminCorreo,
                passwordEncoder.encode(adminContraseña),
                Usuario.Rol.ADMIN);
        usuarioRepo.save(admin);
        log.info("Precargando el usuario administrador");
      }
    };
  }
}