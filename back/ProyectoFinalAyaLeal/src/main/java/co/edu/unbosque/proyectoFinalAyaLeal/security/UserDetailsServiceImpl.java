package co.edu.unbosque.proyectoFinalAyaLeal.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import co.edu.unbosque.proyectoFinalAyaLeal.repository.UsuarioRepository;


//seguridad en el inicio de sesion y en el filtro para cargar el usuario usando el correo

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
  private final UsuarioRepository usuarioRepo;

  public UserDetailsServiceImpl(UsuarioRepository usuarioRepo) {
    this.usuarioRepo = usuarioRepo;
  }

  // buscar usuario por correo
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepo
        .findByCorreo(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("No existe un usuario con el correo: " + username));
  }
}