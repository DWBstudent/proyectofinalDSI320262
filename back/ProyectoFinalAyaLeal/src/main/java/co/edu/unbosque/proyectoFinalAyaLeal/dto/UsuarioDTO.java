package co.edu.unbosque.proyectoFinalAyaLeal.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.edu.unbosque.proyectoFinalAyaLeal.model.Usuario.Rol;

/**
 * La contraseña solo se recib, nunca se incluye en las respuestas JSON.
 */
public class UsuarioDTO {

  private Long id;
  private String nombre;
  private String correo;
  private String contraseña;
  private Rol rol;

  
  public UsuarioDTO() {} // constructor por defencto
  
  public UsuarioDTO(String nombre, String correo, String contraseña) {
    this.nombre = nombre;
    this.correo = correo;
    this.contraseña = contraseña;
  }

// constructor por rol
  public UsuarioDTO(String nombre, String correo, String contraseña, Rol rol) {
    this(nombre, correo, contraseña);
    this.rol = rol;
  }

// getters y setters
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public String getCorreo() {
    return correo;
  }
  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getContraseña() {
    return contraseña;
  }

  public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
  }

  public Rol getRol() {
    return rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }
// metodos
  // calcula el codigo hash con id y correo
  @Override
  public int hashCode() {
    return Objects.hash(id, correo);
  }

  // verificacion de objeto con otros para saber si son iguales
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    UsuarioDTO other = (UsuarioDTO) obj;
    return Objects.equals(id, other.id) && Objects.equals(correo, other.correo);
  }

  
  // devuelve el texto sin la pontraseñas 
  @Override
  public String toString() {
    return "UsuarioDTO [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", rol=" + rol
        + "]";
  }
}