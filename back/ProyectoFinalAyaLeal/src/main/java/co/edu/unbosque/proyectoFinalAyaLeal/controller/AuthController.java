package co.edu.unbosque.proyectoFinalAyaLeal.controller;

import co.edu.unbosque.proyectoFinalAyaLeal.dto.UsuarioDTO;
import co.edu.unbosque.proyectoFinalAyaLeal.model.Usuario;
import co.edu.unbosque.proyectoFinalAyaLeal.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//controlador REST para autenticacion ,la idea es que recba correo y contrasena y las valida


@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacion", description = "Inicio de sesion de usuarios con JWT")
public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  
  public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }


  // inicio de sesion 
  @Operation(
      summary = "Iniciar sesion",
      description =
          """
          Permite a un usuario iniciar sesión con su correo y su contraseña.
          
          se verifican las credenciales y genera el token JWT para las siguientes peticiones(el token dura 12 horas)

          """)
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Inicio de sesión exitoso",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AuthResponse.class),
                    examples =
                        @ExampleObject(
                            value =
                                """
                                {
                                  "token": "eyJhbGciOiJIUzI1NiJ9...",
                                  "rol": "ESTUDIANTE"
                                }
                                """))),
        @ApiResponse(
            responseCode = "401",
            description = "Correo o contraseña incorrectos",
            content =
                @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(value = "Correo o contraseña inválidos")))
      })
  @PostMapping("/login")
  public ResponseEntity<?> login(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Correo y contraseña del usuario",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = UsuarioDTO.class),
                      examples =
                          @ExampleObject(
                              value =
                                  """
                                  {
                                    "correo": "usuario@unbosque.edu.co",
                                    "contraseña": "ClaveDeEjemplo123"
                                  }
                                  """)))
          @RequestBody
          UsuarioDTO credenciales) {
    try {
      Authentication autenticacion =
          authenticationManager.authenticate(
              UsernamePasswordAuthenticationToken.unauthenticated(
                  credenciales.getCorreo(), credenciales.getContraseña()));

      UserDetails userDetails = (UserDetails) autenticacion.getPrincipal();
      String token = jwtUtil.generateToken(userDetails);

      String rol = null;
      if (userDetails instanceof Usuario usuario) {
        rol = usuario.getRol().name();
      }

      return ResponseEntity.ok(new AuthResponse(token, rol));
    } catch (AuthenticationException e) {
      log.warn("Intento de inicio de sesion fallido");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña inválidos");
    }
  }

  private static class AuthResponse {

    private final String token;
    private final String rol;

    public AuthResponse(String token, String rol) {
      this.token = token;
      this.rol = rol;
    }

    public String getToken() {
      return token;
    }

    public String getRol() {
      return rol;
    }
  }
}