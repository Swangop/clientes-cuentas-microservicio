# Microservicio de Gestión de Cuentas Bancarias

Microservicio REST desarrollado con Spring Boot siguiendo arquitectura hexagonal (Ports & Adapters) para la gestión de clientes y sus cuentas bancarias.

## Tecnologías

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database (en memoria)
- Lombok
- OpenAPI/Swagger
- JUnit 5 + MockMvc

## Arquitectura

El proyecto sigue el patrón de arquitectura hexagonal:

```
src/main/java/com/banco/
├── domain/
│   ├── model/          # Entidades de dominio
│   └── port/
│       ├── in/         # Puertos de entrada (casos de uso)
│       └── out/        # Puertos de salida (repositorios)
├── application/
│   └── service/        # Implementación de casos de uso
└── infrastructure/
    ├── config/         # Configuración (OpenAPI)
    ├── persistence/    # Adaptadores de persistencia
    │   ├── adapter/
    │   ├── entity/
    │   ├── mapper/
    │   └── repository/
    └── rest/           # Adaptadores REST
        ├── controller/
        ├── dto/
        └── mapper/
```

## Cómo ejecutar el proyecto

### Prerrequisitos
- Java 17 o superior
- Maven 3.6+

### Ejecutar la aplicación

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/clientes-cuentas-microservicio.git

# Navegar al directorio
cd clientes-cuentas-microservicio

# Ejecutar con Maven
./mvnw spring-boot:run
```

O en Windows:
```bash
mvnw.cmd spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

### Consola H2

Acceder a la consola de la base de datos H2:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:bancodb`
- Usuario: `sa`
- Password: (vacío)

### Documentación API (Swagger)

Acceder a la documentación interactiva de la API:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

## Cómo ejecutar los tests

```bash
# Ejecutar todos los tests
./mvnw test

# Windows
mvnw.cmd test
```

## Endpoints disponibles

### Clientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/clientes` | Obtener todos los clientes con sus cuentas |
| GET | `/clientes/mayores-de-edad` | Obtener clientes >= 18 años |
| GET | `/clientes/con-cuenta-superior-a/{cantidad}` | Obtener clientes con total > cantidad |
| GET | `/clientes/{dni}` | Obtener cliente por DNI |

### Cuentas Bancarias

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/cuentas` | Crear nueva cuenta bancaria |
| PUT | `/cuentas/{idCuenta}` | Actualizar saldo de cuenta |

## Ejemplos de peticiones

### Obtener todos los clientes

```bash
curl -X GET http://localhost:8080/clientes
```

**Respuesta:**
```json
[
  {
    "dni": "11111111A",
    "nombre": "Juan",
    "apellido1": "Pérez",
    "apellido2": "López",
    "fechaNacimiento": "1959-09-12",
    "cuentas": [
      {
        "id": 1,
        "dniCliente": "11111111A",
        "tipoCuenta": "PREMIUM",
        "total": 150000
      },
      {
        "id": 2,
        "dniCliente": "11111111A",
        "tipoCuenta": "NORMAL",
        "total": 20000
      }
    ]
  }
]
```

### Obtener clientes mayores de edad

```bash
curl -X GET http://localhost:8080/clientes/mayores-de-edad
```

### Obtener clientes con cuentas superiores a 100000

```bash
curl -X GET http://localhost:8080/clientes/con-cuenta-superior-a/100000
```

### Obtener cliente por DNI

```bash
curl -X GET http://localhost:8080/clientes/11111111A
```

### Crear nueva cuenta bancaria

```bash
curl -X POST http://localhost:8080/cuentas \
  -H "Content-Type: application/json" \
  -d '{
    "dniCliente": "11111111A",
    "tipoCuenta": "NORMAL",
    "total": 50000
  }'
```

**Respuesta:**
```json
{
  "id": 8,
  "dniCliente": "11111111A",
  "tipoCuenta": "NORMAL",
  "total": 50000
}
```

### Crear cuenta para cliente nuevo

```bash
curl -X POST http://localhost:8080/cuentas \
  -H "Content-Type: application/json" \
  -d '{
    "dniCliente": "66666666F",
    "tipoCuenta": "PREMIUM",
    "total": 25000
  }'
```

*Nota: Si el cliente no existe, se creará automáticamente.*

### Actualizar saldo de cuenta

```bash
curl -X PUT http://localhost:8080/cuentas/1 \
  -H "Content-Type: application/json" \
  -d '{
    "total": 180000
  }'
```

**Respuesta:**
```json
{
  "id": 1,
  "dniCliente": "11111111A",
  "tipoCuenta": "PREMIUM",
  "total": 180000
}
```

## Datos iniciales

El sistema se inicializa con los siguientes datos:

### Clientes

| DNI | Nombre | Apellido1 | Apellido2 | Fecha Nacimiento |
|-----|--------|-----------|-----------|------------------|
| 11111111A | Juan | Pérez | López | 12/09/1959 |
| 22222222B | Raúl | Canales | Rodríguez | 01/03/1985 |
| 33333333C | Elena | Ruiz | Herrera | 10/05/2010 |
| 44444444D | Raquel | Ruiz | Herrera | 21/06/2002 |
| 55555555E | María | Sánchez | Torres | 08/08/1999 |

### Cuentas Bancarias

| DNI Cliente | Tipo Cuenta | Total |
|-------------|-------------|-------|
| 11111111A | PREMIUM | 150000 |
| 11111111A | NORMAL | 20000 |
| 22222222B | NORMAL | 50000 |
| 22222222B | JUNIOR | 300 |
| 33333333C | JUNIOR | 300 |
| 44444444D | NORMAL | 75000 |
| 55555555E | PREMIUM | 120000 |

## Licencia

Apache 2.0
