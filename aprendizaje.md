# Aprendizaje — Mini Tasks

## Estado

Repositorio: https://github.com/Alvaro-Escalona/Mini-task

Último test verde: `TaskControllerV1Test.getsExistingTask` y `TaskControllerV1Test.missingTaskReturns404`.

---

## Avance Sesión 2 — Entidad `Task`

### Ciclo TDD: `newTaskShouldNotBeCompleted`

- **Test que produjo el rojo:** `TaskTest.newTaskShouldNotBeCompleted`
- **Por qué falló:** no existía la clase `Task`. Error de compilación: `cannot find symbol: class Task`.
- **Cambio que lo hizo pasar:** crear la entidad `Task` con `id`, `title`, `completed` y el constructor que fija `completed = false`.
- **Migración Flyway:** `V1__create_tasks_table.sql` con la tabla `tasks` (`id`, `title`, `completed`).

### Decisiones

- Se usan los starters de Spring Boot 4 (`spring-boot-starter-flyway`, `spring-boot-h2console`) porque en SB4 `flyway-core` y `h2` solos no autoconfiguran.
- La consola H2 se registra manualmente mediante `H2ConsoleConfig` porque la autoconfiguración no se activaba con la dependencia sola.

---

## Avance Sesión 3 — `GET /v1/tasks/{id}`

### Ciclo 1: tarea existente

- **Test que produjo el rojo:** `TaskControllerV1Test.getsExistingTask`
- **Por qué falló:** no existía la clase `TaskControllerV1`. Error de compilación: `cannot find symbol: class TaskControllerV1`.
- **Cambio que lo hizo pasar:** crear `TaskControllerV1` con `@GetMapping("/{id}")` que busca la tarea en la lista en memoria y devuelve `200` con su JSON.
- **Commits:** `test: GET /v1/tasks/{id} devuelve tarea existente` → `feat: TaskControllerV1 con GET /v1/tasks/{id}`

### Ciclo 2: tarea inexistente

- **Test que produjo el rojo:** `TaskControllerV1Test.missingTaskReturns404`
- **Por qué falló:** en esta ocasión la implementación del ciclo 1 ya cubría el caso (`orElse(ResponseEntity.notFound().build())`), así que el test pasó a verde sin necesidad de cambiar el controlador.
- **Cambio que lo hizo pasar:** ninguno nuevo; la implementación anterior ya resolvía el 404.
- **Commit:** `test: GET /v1/tasks/{id} devuelve 404 para id inexistente`

### Decisiones

- V1 guarda las tareas en una lista en memoria (`ArrayList`), no en JPA, según indica el enunciado para esta fase.
- El test usa `@Autowired TaskControllerV1 controller` en lugar de `new TaskControllerV1()` para que MockMvc y el test compartan la misma instancia del controlador.

---

## Responsabilidades de las capas (estado actual)

| Capa | Clase | Responsabilidad |
|------|-------|-----------------|
| Web | `TaskControllerV1` | Rutas `/v1/tasks`, códigos HTTP (200, 404) |
| Dominio | `Task` | Entidad con `id`, `title`, `completed` |
| Persistencia | `V1__create_tasks_table.sql` | Esquema de la tabla `tasks` |

Todavía no hay capa de servicio ni DTOs.
