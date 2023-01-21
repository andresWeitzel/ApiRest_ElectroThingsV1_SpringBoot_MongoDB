# ApiRest ElectroThings V1
Api Rest para la Aplicación Electro Things acerca de Productos Electrónicos con Spring Boot, Spring MVC, Spring Security, JWT, Spring Data MongoDB, SpringFox, Swagger UI, Api Highchart, Maven, Lombok, Log4j, Git, MongoDBCompass, MongoDB y Otras Tecnologías.

<br>

## Índice

<details>
 <summary> Ver 📜</summary>
 
 <br>
 
 - [Descripción del Proyecto.](#descripción-)
 - [Repositorios.](#repositorios-)
 
</details>

<br>

## Descripción [🔝](#índice) 

<details>
 <summary></summary>
 
 <br>

* Se Desarrollan Clases Específicas para el Manejo de Excepciones para cada Servicio , como también un manejador de excepciones y validaciones por campos de beans.
* Todas las funcionalidades tienen generación de logs en el Server para los errores y excepciones personalizadas.
* Se incluye documentación completa de la Api con open-api para la visualización con swagger-ui, las anotaciones de open-api se aplican junto con los códigos de 
 respuesta de tipo HTTP para cada función en los respectivos controllers.
* Se desarrolla toda la funcionalidad para las Operaciones CRUD, como así también paginados y funcionalidades para uso de filtros de búsqueda de productos desde el frontend. 
* Se separa la capa de seguridad para la autenticación , implementando Spring Security y JWT. Además de realizar las operaciones CRUD para usuarios se aplica login y signin para la capa de presentación.
* También se desarrollan los métodos de búsquedas independientes de tipo Like para todos los campos, tanto de usuarios como productos.
* Los objetos de tipo getBy se manipulan como paginados, salvo los getById y Optional que se requiere un response por objeto y no una E.D como de tipo lista, stream, etc.
* Entre Otros.
* Se pone a disposición todos los recursos anteriores para productos y usuarios.
 
<br>

</details>
 
 
## Repositorios 🔝 

<details>
 <summary>Ver</summary>
 
 <br>

* [Repositorio AppTiendaElectronica Original](https://github.com/andresWeitzel/AppTiendaElectronica_Angular_Bootstrap_SpringBoot_MongoDB)
* [Repositorio AppTiendaElectronica](https://github.com/andresWeitzel/AppElectroThings_Angular_Bootstrap_SpringBoot_MongoDB)
* [Repositorio db_ElectroThings](https://github.com/andresWeitzel/db_ElectroThings_MongoDB)
* [PlayList Proyecto](https://www.youtube.com/watch?v=wrl32hijoqo&list=PLCl11UFjHurCMBTbTNCGQerdF9LBXgX15&index=1)

<br>

</details>


## EndPoints y Recursos. Vista Swagger UI

### EndPoints
* http://localhost:8098/api/v1/productos/
* http://localhost:8098/api/v1/admin/usuarios/
* http://localhost:8098/api/v1/auth/

* Algunos de los recursos de cada endpoint se muestran en las siguientes imágenes respectivas a cada controller

<br>

## Documentación Gráfica de algunos Recursos la Api.


### Descripción de la Api Rest
![Index app](./doc/controllers/inicioDocSwagger.png)

### Modelo de Respuesta Métodos Http Code
![Index app](./doc/controllers/httpResponses.png)

<br>

### Recursos por Endpoint

### Documentación con Swagger-UI `auth-controller`
![Index app](./doc/controllers/authController.png)

### Documentación con Swagger-UI `producto-controller`
![Index app](./doc/controllers/productosController.png)

### Documentación con Swagger-UI `usuario-controller`
![Index app](./doc/controllers/usuarioController.png)

### Listado de Productos Paginados
 ![Index app](./doc/get/getAll.png)
 ![Index app](./doc/get/getAllResponse.png)


### Listado de Productos o Producto Paginado/s por Fecha
 ![Index app](./doc/get/getByFecha.png)
 ![Index app](./doc/get/getByFechaResponse.png)

  
### Inserción de un Producto Método Post
 ![Index app](./doc/post/post.png)
 ![Index app](./doc/post/postResponse.png)

### Búsqueda del Producto Insertado según su Código
 ![Index app](./doc/post/getByCodigo.png)
 ![Index app](./doc/post/getByCodigoResponse.png)
  
### Actualización de un Producto Método Put
 ![Index app](./doc/put/put.png)
 ![Index app](./doc/put/putResponse.png)
  

### Eliminación de un Producto Método Delete
 ![Index app](./doc/delete/delete.png)
 ![Index app](./doc/delete/deleteResponse.png)
  
### Búsqueda del Producto Eliminado según su Id
 ![Index app](./doc/delete/getById.png)
 ![Index app](./doc/delete/getByIdResponse.png)
 
 
 
<br>

### Prueba de Funcionalidad de la ApiRest


### Login de Usuarios
[![Alt text](./doc/Yt/01.00_Login.png)](https://www.youtube.com/watch?v=wrl32hijoqo&list=PLCl11UFjHurCMBTbTNCGQerdF9LBXgX15&index=1) 


### Signin de Usuarios y Refresh Token
[![Alt text](./doc/Yt/01.01_SigninTokRefresh.png)](https://www.youtube.com/watch?v=mQGu5QntgQA&list=PLCl11UFjHurCMBTbTNCGQerdF9LBXgX15&index=2) 

### Comprobación Usuario Controller
[![Alt text](./doc/Yt/02.00_UsuarioController.png)](https://www.youtube.com/watch?v=l32x3TX_Gcs&list=PLCl11UFjHurCMBTbTNCGQerdF9LBXgX15&index=3) 

### Operaciones de Búsqueda para Producto Controller
[![Alt text](./doc/Yt/03.00_ProductoControllerGetBy.png)](https://www.youtube.com/watch?v=lBltAUMeMlw&list=PLCl11UFjHurCMBTbTNCGQerdF9LBXgX15&index=4) 

### Operaciones CRUD para Producto Controller
[![Alt text](./doc/Yt/03.01_ProductoControllerCRUD.png)](https://www.youtube.com/watch?v=Pdulm99Qhnk&list=PLCl11UFjHurCMBTbTNCGQerdF9LBXgX15&index=5) 

</hr>

</br>

### Tecnologías Implementadas

| **Tecnologías** | **Versión** | **Finalidad** |               
| ------------- | ------------- | ------------- |
| Java |   12.0.2 | JDK |
| Spring Tool Suite 4 | 4.9.0  | IDE |
| Spring Boot |   2.6.4  | Framework |
| Spring Boot Data JPA  | 2.6.3 | Mapeo de objetos y persistencia en la db |
| Spring Validation  | 2.7 | Anotations para Validaciones |
| Spring Security  | 2.6.7 | Módulo de Seguridad de Spring |
| Json Web Token  | 0.9.1 | Manejo de Token's de Seguridad |
| Lombok | 1.18.22 | Automatización de Código | 
| Open-Api y UI Swagger | 1.6.4 | Documentación de la Api | 
| UI Swagger | 1.6.4 | Visualización y Gestión de la Api | 
| Maven |  4.0.0 | Gestor de Proyectos |
| MongoDB | 5.0 | Base de Datos |
| MongoDB Compass	| 1.31.2 |	Gestor para MongoDB |
| CMD | 10 | Símbolo del Sistema para linea de comandos | 
| GNU bash / Terminal | 4.4.23  | Bash / Terminal para el manejo e implementación de Git integrado al Spring Tool Suite |
| Git | 2.29.1  | Control de Versiones |


</br>

### Descarga y Documentación de las Tecnologías Implementadas

| **Documentación**  | **Descarga** | 
| ------------- | ------------- | 
| [Java-JDK 12](https://docs.oracle.com/en/) | https://www.oracle.com/java/technologies/javase/jdk12-archive-downloads.html |
| [Spring Tool Suite 4](https://spring.io/guides) | https://spring.io/tools |
| [Lombok](https://projectlombok.org/download) | https://projectlombok.org/download |
| [Open UI](https://open-ui.org/) | https://open-ui.org/ |
| [Maven Repository](https://mvnrepository.com/) | https://mvnrepository.com/ |
| [MongoDB](https://www.mongodb.com/try/download/community) |	https://www.mongodb.com/try/download/community |
| [MongoDB Compass](https://www.mongodb.com/try/download/compass)	|https://www.mongodb.com/try/download/compass	|
| [Git](https://git-scm.com/docs)  | https://git-scm.com/downloads |

</br>

### Patrones de Diseño Implementados

| **Patrón de Diseño** | **Finalidad** |               
| ------------- | ------------- |
| [DAO](https://www.oscarblancarteblog.com/2018/12/10/data-access-object-dao-pattern/) | Uso de interfaces entre la aplicación y el almacenamiento de datos. |
| [MVC](https://keepcoding.io/blog/que-es-el-patron-de-arquitectura-mvvm/) | Separación y Representación de los Datos, Manejo de errores, Escalabilidad, etc  |
| [DTO](https://www.oscarblancarteblog.com/2018/11/30/data-transfer-object-dto-patron-diseno/) | Transferencia de objectos separando la capa de acceso a datos  |
| [Dependency Inyection](https://stackify.com/dependency-injection/) | Uso de funcionalidades requeridas de clases a través de inyección |
| Otros | Otros |


</br>

### Dependencias Implementadas

| **Dependencia Maven**  | **Versión** | **Finalidad** |             
| ------------- | ------------- | ------------- |
| spring-boot-starter-data-jpa | 2.6.7 | Api de JpaRepository para el manejo de métodos | 
| spring-boot-starter-test | 2.6.7 | Para Testing | 
| spring-boot-starter-web | 2.6.7 | Se agrega toda la configuración web automáticamente de Maven a Spring | 
| spring-boot-starter-validation | 2.7.0 | Validación de Annotations |
| spring-boot-devtools | 2.6.7 | Herramientas para el Manejo de Spring Boot | 
| spring-boot-starter-security | 2.6.7 | Módulo de Seguridad de Spring |
| jjwt | 0.9.1 | Manejo de Token's |
| nimbus-jose-jwt | 9.22 | Refresh Token |
| jaxb-api | 4.0 | Serialización de Objetos Java a XML |
| jackson-databind | 4.0 | Serialización de Objetos Java a JSON |
| javax-annotation-api | 4.0 | Api para la lectura de annotations|
| springfox-boot-starter | 3.0.0 | Doc Api |
| springfox-swagger-ui | 3.0.0 | Doc Api |
| spring-boot-starter-data-mongodb | 2.6.7 | Manejo de SpringDataMongoDB con los métodos del Repository y anotations  |
| springdoc-openapi-ui | 1.6.4 | Plantillas para el Front |
| spring-boot-devtools | 2.6.7 | Herramientas para el Manejo de Spring Boot | 
| lombok | 1.18.22 |  Dependencia para la automatización de Código |




</br>

### Doc No Oficial Recomendada
#### Api Rest con MongoDB/SpringBoot
* [Api Rest Spring Boot Mongodb](https://www.youtube.com/watch?v=OtBukxJy4kg)
* [Api Rest Guía Spring Boot Mongodb Codigo](https://github.com/heroe-geek/rest-api-springboot-mvc/blob/master/src/main/java/com/hg/crud/controllers/ProductController.java)
* [Tutorial MongoDB con Spring Boot](https://www.mongodb.com/compatibility/spring-boot)
* [Config Mongo y Spring Boot](https://hevodata.com/learn/spring-boot-mongodb-config/)
#### Spring Data MongoDB
* [Tutorial Spring Data MongoDB (01)](https://stackabuse.com/spring-data-mongodb-guide-to-the-aggregation-annotation/)
* [Tutorial Spring Data MongoDB(02)](https://blog.marcnuri.com/spring-data-mongodb-implementacion-de-un-repositorio-a-medida)
#### Querys MongoRepository
* [Tutorial Uso de Querys en MongoDB](https://www.baeldung.com/queries-in-spring-data-mongodb)
* [Tutorial Querys MongoRepository](https://stackabuse.com/spring-data-mongodb-guide-to-the-query-annotation/)
* [Código Ejemplo genérico](https://github.com/heroe-geek/rest-api-springboot-mvc/blob/master/src/main/java/com/hg/crud/models/ProductDTO.java)
#### Swagger UI y SpringFox
* [Config Swagger](https://www.baeldung.com/swagger-set-example-description)
* [Generar doc con swagger](https://howtodoinjava.com/swagger2/swagger-spring-mvc-rest-example/)
* [Api Response con swagger](https://docs.swagger.io/swagger-core/v1.5.0/apidocs/io/swagger/annotations/ApiResponse.html)
* [Códigos HTTP de respuestas desarrollar en apis](https://www.ibm.com/docs/es/odm/8.5.1?topic=api-rest-response-codes-error-messages)

### Doc Gráfica No Oficial Recomendada
* [Implementación de Spring Security y JWT Youtube](https://www.youtube.com/watch?v=_91iKzsKdqY&list=PL4bT56Uw3S4z9rtwwGvuk1Mjhu5sdLSwX&index=12)
* [Implementación Spring Security](https://www.youtube.com/watch?v=tDZPdovCH4I)
* [Tutorial doc open-api](https://www.youtube.com/watch?v=iaVBleTf88U)



</br>

## Documentación de Desarrollo de la Api No Disponible Momentáneamente

