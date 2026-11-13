package co.edu.unbosque.proyectoFinalAyaLeal.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.proyectoFinalAyaLeal.model.Usuario;

// repo para usuario, con crud y busqueda por correo 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  // busqueda por correo
  public Optional<Usuario> findByCorreo(String correo);

  // verifica si el correo existe
  public boolean existsByCorreo(String correo);
}