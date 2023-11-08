# Blog de Coches API REST

Este repositorio contiene el código fuente de una API REST para un blog de coches. La API ofrece una serie de funcionalidades, incluyendo operaciones CRUD, paginación y clasificación, relaciones uno a muchos, ModelMapper para mapear entidades y DTO, manejo de excepciones, validaciones, seguridad con Spring Security y autenticación con JWT.

## Funcionalidades

La API ofrece las siguientes funcionalidades:

- **Operaciones CRUD:** La API permite crear, leer, actualizar y eliminar artículos sobre coches. 

- **Paginación y Clasificación:** Los resultados pueden ser paginados y ordenados según diferentes criterios.

- **Relación de Uno a Muchos:** Los artículos pueden estar relacionados con comentarios. Cada artículo puede tener múltiples comentarios.

- **ModelMapper:** Se utiliza ModelMapper para mapear entidades y DTO, facilitando la conversión de objetos entre las capas de la aplicación.

- **Manejo de Excepciones:** Se manejan y se devuelven respuestas adecuadas para diferentes tipos de excepciones, como recursos no encontrados o errores de validación.

- **Validaciones:** Se aplican validaciones en las entradas de la API para garantizar la integridad de los datos.

- **Seguridad con Spring Security:** La API está protegida con Spring Security para controlar el acceso a los endpoints.

- **Inicio de Sesión y Registro:** Se proporciona una API de inicio de sesión y registro para autenticar a los usuarios.

- **Protección con JWT:** Se utiliza JSON Web Tokens (JWT) para proteger las APIs REST, asegurando la autenticación de los usuarios y la autorización para acceder a ciertos recursos.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
- ModelMapper
- H2 Database (para desarrollo)
- MySQL (para producción)

## Uso

1. Clona este repositorio:

   ```bash
   git clone https://github.com/javicia/blog-coches-api.git
