# Autoría — Mini Tasks

- **Nombre:** Álvaro Escalona
- **Usuario GitHub:** Alvaro-Escalona
- **Repositorio:** https://github.com/Alvaro-Escalona/Mini-task

## Punto de partida

Proyecto creado desde cero con Spring Initializr (Spring Boot 4.0.5, Java 25, Maven), sin fork, plantilla ni historial heredado. Repositorio Git inicializado en local y publicado en una cuenta propia de GitHub sobre la rama `main`.

## Commits

| # | Mensaje | Fase |
|---|---------|------|
| 1 | Initial commit: setup Spring Boot 4.0.5 project | Sesión 2 — base |
| 2 | feat: entidad Task con estado inicial no completado | Sesión 2 — verde |
| 3 | chore: migración Flyway para tabla tasks | Sesión 2 — esquema |
| 4 | chore: configura consola H2 manualmente | Sesión 2 — herramientas |
| 5 | test: GET /v1/tasks/{id} devuelve tarea existente | Sesión 3 — rojo |
| 6 | feat: TaskControllerV1 con GET /v1/tasks/{id} | Sesión 3 — verde |
| 7 | test: GET /v1/tasks/{id} devuelve 404 para id inexistente | Sesión 3 — rojo |

Los tests se ejecutan con `mvn test`.
