package co.edu.unbosque.proyectoFinalAyaLeal.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * entidad para la persona que usa el sitema
 */
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
  private static final long serialVersionUID = 1L;
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
  private String nombre;
  @Column(unique = true)
  private String correo;
  private String contraseña;
  @Enumerated(EnumType.STRING)
  private Rol rol;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
  private boolean enabled;
  public enum Rol {

    ESTUDIANTE,
    DOCENTE,
    ADMIN
  }

  /**
   * Constructor
   */
  public Usuario() {
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
    this.rol = Rol.ESTUDIANTE;
  }

  /**
   * Constructor con los datos basicos dejando al estudiante
   */
  public Usuario(String nombre, String correo, String contraseña) {
    this();
    this.nombre = nombre;
    this.correo = correo;
    this.contraseña = contraseña;
  }

  /**
   * contrusctor para cualquiera
   */
  public Usuario(String nombre, String correo, String contraseña, Rol rol) {
    this(nombre, correo, contraseña);
    this.rol = rol;
  }

  /**
   * muestra los permisos que tiene el usuario 
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
  }

  /**
   * contraseña cifrafa
   */
  @Override
  public String getPassword() {
    return contraseña;
  }

  /**
   * correo
   */
  @Override
  public String getUsername() {
    return correo;
  }

  /**
   * verificar para que siga actitva la cuenta
   */
  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }


  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }
  @Override
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * mostrar el id 
   */
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
  
  /*
   * metodos para setear estados de las cuentas
    */

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }
  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }
  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * caulcular el codigo apartir del id y del correo
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, correo);
  }

  /*
   * verificar duplicados
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Usuario other = (Usuario) obj;
    return Objects.equals(id, other.id) && Objects.equals(correo, other.correo);
  }

  @Override
  public String toString() {
    return "Usuario [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", rol=" + rol + "]";
  }
}