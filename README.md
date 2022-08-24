# ApiRest_ElectroThingsV1_SpringBoot_MongoDB

* Api Rest para la Aplicación Electro Things acerca de Productos Electrónicos con Spring Boot, Spring MVC, Spring Security, JWT, Spring Data MongoDB, SpringDoc-OpenApi, Swagger UI, Api Highchart, Maven, Lombok, Log4j, Git, MongoDBCompass, MongoDB y Otras Tecnologías.
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

</br>

* Repositorio AppTiendaElectronica : https://github.com/andresWeitzel/AppTiendaElectronica_Angular_Bootstrap_SpringBoot_MongoDB
* Repositorio db_ElectroThings : https://github.com/andresWeitzel/db_ElectroThings_MongoDB

</br>

### Documentación Gráfica de algunos Recursos la Api.


#### Documentación con Swagger-UI `producto-controller`
 <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/productosController.png" />
 
 #### Listado de Productos Paginados
 <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/get/getAll.png" />
  <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/get/getAllResponse.png" />

  #### Listado de Productos o Producto Paginado/s por Fecha
   <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/get/getByFecha.png" />
  <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/get/getByFechaResponse.png" />
  
 #### Inserción de un Producto Método Post
  <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/post/post.png" />
  
  <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/post/postResponse.png" />

  #### Búsqueda del Producto Insertado según su Código
  <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/post/getByCodigo.png" />
    <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/post/getByCodigoResponse.png" />
  
 #### Actualización de un Producto Método Put
  <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/put/put.png" />
<img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/put/putResponse.png" />

  #### Eliminación de un Producto Método Delete
  <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/delete/delete.png" />
    <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/delete/deleteResponse.png" />
 
  
  
 #### Búsqueda del Producto Eliminado según su Id
   <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/delete/getById.png" />
     <img width="70%" height="50%"  src="https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/documentation/delete/getByIdResponse.png" />

  
  

</br>

### Tecnologías Implementadas

| **Tecnologías** | **Versión** | **Finalidad** |               
| ------------- | ------------- | ------------- |
| Java |   12.0.2 | JDK |
| Spring Tool Suite 4 | 4.9.0  | IDE |
| Spring Boot |   2.6.4  | Framework |
| Spring Boot Data JPA  | 2.6.3 | Mapeo de objetos y persistencia en la db |
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

### Descarga y Documentacion de las Tecnologías Implementadas

| **Tecnología**  | **Descarga** | **Documentación** |               
| ------------- | ------------- | ------------- |
| Java-JDK 12 | https://www.oracle.com/java/technologies/javase/jdk12-archive-downloads.html |  https://docs.oracle.com/en/ |
| Spring Tool Suite 4 |https://spring.io/tools | https://spring.io/guides |
| Lombok | https://projectlombok.org/download | https://projectlombok.org/download |
| Open UI |https://open-ui.org/ | https://open-ui.org/ |
| Maven Repository | https://mvnrepository.com/ | https://mvnrepository.com/ | 
| MongoDB |	https://www.mongodb.com/try/download/community |	https://www.mongodb.com/try/download/community |
| MongoDB Compass	|https://www.mongodb.com/try/download/compass	| https://www.mongodb.com/try/download/compass |
| Git  | https://git-scm.com/downloads |  https://git-scm.com/docs |

</br>

### Patrones de Diseño Implementados

| **Patrón de Diseño** | **Finalidad** |               
| ------------- | ------------- |
| DAO | Uso de interfaces entre la aplicación y el almacenamiento de datos. |
| MVC | Separación y Representación de los Datos, Manejo de errores, Escalabilidad, etc  |

</br>

### Dependencias Implementadas

| **Dependencia Maven**  | **Versión** | **Finalidad** |             
| ------------- | ------------- | ------------- |
| spring-boot-starter-data-jpa | 2.6.7 | Api de JpaRepository para el manejo de métodos | 
| spring-boot-starter-test | 2.6.7 | Para Testing | 
| spring-boot-starter-web | 2.6.7 | Se agrega toda la configuración web automáticamente de Maven a Spring |
| spring-boot-starter-validation | 2.6.7 | Uso de Validacion desde nuestro Bean |
| spring-boot-starter-data-mongodb | 2.6.7 | Manejo de SpringDataMongoDB con los métodos del Repository y anotations  |
| springdoc-openapi-ui | 1.6.4 | Plantillas para el Front |
| spring-boot-devtools | 2.6.7 | Herramientas para el Manejo de Spring Boot | 
| lombok | 1.18.22 |  Dependencia para la automatización de Código |




</br>

### Documentación No Oficial Recomendada
#### Api Rest con MongoDB/SpringBoot
* Api Rest Spring Boot Mongodb : https://www.youtube.com/watch?v=OtBukxJy4kg
* Api Rest Guía Spring Boot Mongodb Codigo :https://github.com/heroe-geek/rest-api-springboot-mvc/blob/master/src/main/java/com/hg/crud/controllers/ProductController.java
* Tutorial MongoDB con Spring Boot : https://www.mongodb.com/compatibility/spring-boot
* Config Mongo y Spring Boot : https://hevodata.com/learn/spring-boot-mongodb-config/
#### Spring Data MongoDB
* Tutorial Spring Data MongoDB (01) : https://stackabuse.com/spring-data-mongodb-guide-to-the-aggregation-annotation/
* Tutorial Spring Data MongoDB(02) :  https://blog.marcnuri.com/spring-data-mongodb-implementacion-de-un-repositorio-a-medida
#### Querys MongoRepository
* Tutorial Uso de Querys en MongoDB : https://www.baeldung.com/queries-in-spring-data-mongodb
* Tutorial Querys MongoRepository : https://stackabuse.com/spring-data-mongodb-guide-to-the-query-annotation/
* Código Ejemplo genérico : https://github.com/heroe-geek/rest-api-springboot-mvc/blob/master/src/main/java/com/hg/crud/models/ProductDTO.java


</br>

## Documentación de Desarrollo del Repositorio No Disponible Momentáneamente

