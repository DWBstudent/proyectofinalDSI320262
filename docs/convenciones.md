# Convenciones del proyecto
## Nombres

### Back (Java, Spring Boot), repositorio y Git
- Sin `-` ni `_`: palabras pegadas o camelCase en paquetes, clases, tablas, columnas y rutas.
- Los servicios llevan la palabra `Service`: `MateriaService`.
### Front (Angular)
Sigue completamente la guia oficial: https://angular.dev/style-guide
- Archivos en minuscula con palabras separadas por guion: `TarjetaMateria` -> `tarjeta-materia.ts`.
- Componentes sin sufijo; `.ts`, `.html` y `.css` con el mismo nombre.
- Servicios con `Service`: `MateriaService` -> `materia-service.ts`.
- Pruebas al lado del codigo: `tarjeta-materia.spec.ts`.
- Carpetas por funcionalidad (`materias/`, `notas/`), nunca por tipo (`components/`, `services/`).
- Prohibidos los nombres genericos: `utils.ts`, `helpers.ts`, `common.ts`.
- `protected` para lo que solo usa la plantilla; `readonly` para inputs, outputs y consultas.
- Metodos de eventos nombrados por lo que hacen: `guardarNotas()`, no `handleClick()`.
- Clases CSS propias (la guia no las regula): una palabra o camelCase.

## Formato
- UTF-8, 2 espacios, finales de linea LF (lo aplica `.editorconfig`).
- TypeScript: Google TypeScript Style Guide, a la que remite la guia de Angular.
- IDE: Eclipse para el back y WebStorm para el front.

## Git
- `main` protegida: todo entra por pull request con una aprobacion.
- Ramas sin guiones: `feature/inscripcion`, `fix/notaservice`.
- Commits con Conventional Commits: `feat: ...`, `fix: ...`, `docs: ...`.
- Merge commit al integrar; commits pequeños y diarios.
