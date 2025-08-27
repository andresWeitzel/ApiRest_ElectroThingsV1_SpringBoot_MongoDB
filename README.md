
<img src="./doc/assets/img/controllers/inicioDocSwagger.png" style="width: 100%; height: 80%"/>

  <div align="right">
    <img width="28" height="30" src="./doc/assets/icons/backend/java/png/java.png" />
    <img width="22" height="24" src="./doc/assets/icons/devops/png/maven.png" />
    <img width="26" height="26" src="./doc/assets/icons/devops/png/git.png" />
    <img width="27" height="27" src="./doc/assets/icons/devops/png/postman.png" />
    <img width="25" height="25" src="./doc/assets/icons/devops/png/swagger.png" /> 
    <img width="25" height="25" src="./doc/assets/icons/backend/java/png/spring-boot.png" />
    <img width="25" height="25" src="./doc/assets/icons/backend/java/png/spring-security.png" />
    <img width="24" height="26" src="./doc/assets/icons/database/png/mongo.png" />  
  </div>  

<br>

# ApiRest ElectroThings V1
Api Rest para la Aplicación Electro Things acerca de Productos Electrónicos con Spring Boot, Spring MVC, Spring Security, JWT, Spring Data MongoDB, SpringFox, Swagger UI, Api Highchart, Maven, Lombok, Log4j, Git, MongoDBCompass, MongoDB y Otras Tecnologías.
* [Repositorio AppElectroThings_Angular_SpringBoot_MongoDB](https://github.com/andresWeitzel/AppElectroThings_Angular_SpringBoot_MongoDB)
* [Repositorio db_ElectroThings_MongoDB](https://github.com/andresWeitzel/db_ElectroThings_MongoDB)
* [PlayList del Proyecto](https://www.youtube.com/watch?v=wrl32hijoqo&list=PLCl11UFjHurCMBTbTNCGQerdF9LBXgX15&index=1)

<br>

## Índice 📜

<details>
 <summary> Ver </summary>
 
 <br>
 
 
### Sección 1) Descripción, Tecnologías y Dependencias 

 - [1.0) Descripción del Proyecto.](#10-descripción-)
 - [1.1) Ejecución del Proyecto.](#11-ejecución-del-proyecto-)
 - [1.2) Patrones de Diseño.](#12-patrones-de-diseño-)
 - [1.3) Tecnologías.](#13-tecnologías-)
 - [1.4) Dependencias Maven.](#14-dependencias-maven-)
 - [1.5) Descargas.](#15-descargas-)

  
  
### Sección 2) Endpoints y Recursos 
 
 - [2.0) EndPoints.](#20-endpoints-)
 - [2.1) Recursos por endpoints.](#21-recursos-por-endpoints-)
  
  
### Sección 3) Prueba de Funcionalidad y Referencias
 
 - [3.0) Prueba de Funcionalidad.](#30-prueba-de-funcionalidad-)
 - [3.1) Referencias.](#31-referencias-)
	  

</details>


<br>

## Sección 1) Descripción, Tecnologías y Dependencias 


### 1.0) Descripción [🔝](#índice-) 

<details>
 <summary>Ver</summary>
 
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


### 1.1) Ejecución del Proyecto [🔝](#índice-)

<details>
  <summary>Ver</summary>
  
 <br>  
  
* Descargamos [MongoDB compass](https://www.mongodb.com/it-it/products/compass) para ejecutar los servicios de Mongodb.
* Abrimos una terminal y clonamos el [repo de base de datos](https://github.com/andresWeitzel/db_ElectroThings_MongoDB).
* Creamos una collection e importamos el doc json (toda la data de nuestra db). Para más info. consultar repositorio respectivo
* Descargamos [Spring tool suite 4](https://spring.io/tools)
* Desde sts u otro ide abrimos una terminal y creamos un entorno de trabajo o workspace.
* Clonar el Proyecto
```git
git clone https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB
```
* Click der sobre el proyecto --> Run as --> Spring Boot App
* Ya tenemos corriendo nuestra api desde tomcat. El url de la app para la ejecución de los endpoint's lo visualizamos desde consola.

<br>

</details>


### 1.2) Patrones de Diseño [🔝](#índice-)

<details>
 <summary>Ver</summary>
 
 <br>

| **Patrón de Diseño** | **Finalidad** |               
| ------------- | ------------- |
| [DAO](https://www.oscarblancarteblog.com/2018/12/10/data-access-object-dao-pattern/) | Uso de interfaces entre la aplicación y el almacenamiento de datos. |
| [MVC](https://keepcoding.io/blog/que-es-el-patron-de-arquitectura-mvvm/) | Separación y Representación de los Datos, Manejo de errores, Escalabilidad, etc  |
| [DTO](https://www.oscarblancarteblog.com/2018/11/30/data-transfer-object-dto-patron-diseno/) | Transferencia de objectos separando la capa de acceso a datos  |
| [Dependency Inyection](https://stackify.com/dependency-injection/) | Uso de funcionalidades requeridas de clases a través de inyección |

<br>

</details>




### 1.3) Tecnologías [🔝](#índice-)

<details>
 <summary>Ver</summary>
 
 <br>

| **Tecnologías** | **Versión** | **Finalidad** |           
| ------------- | ------------- | ------------- |
| [Java](https://docs.oracle.com/en/) |  12.0.2 | JDK |
| [Spring Tool Suite 4](https://spring.io/blog/2021/06/21/spring-tools-4-11-0-released) | 4.9.0  | IDE |
| [Spring Boot](https://spring.io/) |  2.6.4  | Framework |
| [Spring Boot Data JPA](https://spring.io/projects/spring-data-jpa)  | 2.6.3 | Mapeo de objetos y persistencia en la db |
| [Spring Validation](https://www.baeldung.com/spring-boot-bean-validation)  | 2.7 | Anotations para Validaciones |
| [Spring Security](https://spring.io/projects/spring-security)  | 2.6.7 | Módulo de Seguridad de Spring |
| [Json Web Token](https://jwt.io/)  | 0.9.1 | Manejo de Token's de Seguridad |
| [Lombok](https://projectlombok.org/) | 1.18.22 | Automatización de Código | 
| [Open-Api y UI Swagger](https://www.openapis.org/) | 1.6.4 | Documentación de la Api | 
| [UI Swagger](https://swagger.io/tools/swagger-ui/) | 1.6.4 | Visualización y Gestión de la Api | 
| [Maven](https://maven.apache.org/) |  4.0.0 | Gestor de Proyectos |
| [Postman](https://www.postman.com/) | 9.1.1 | Visualización y Gestión de la Api | 
| [MongoDB](https://www.mongodb.com/) | 5.0 | Base de Datos |
| [MongoDB Compass](https://www.mongodb.com/products/compass)	| 1.31.2 |	Gestor para MongoDB |
| [CMD](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/cmd) | 10 | Símbolo del Sistema para linea de comandos | 
| [GNU bash / Terminal](https://www.gnu.org/software/bash/) | 4.4.23  | Bash / Terminal para el manejo e implementación de Git. |
| [Git](https://git-scm.com/) | 2.29.1  | Control de Versiones |

<br>

</details>





### 1.4) Dependencias Maven [🔝](#índice-)

<details>
 <summary>Ver</summary>
 
 <br>

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


<br>

</details>




### 1.5) Descargas [🔝](#índice-)

<details>
 <summary>Ver</summary>
 
 <br>

* [Java-JDK 12](https://www.oracle.com/java/technologies/javase/jdk12-archive-downloads.html)
* [Spring Tool Suite 4](https://spring.io/tools)
* [Lombok](https://projectlombok.org/download )
* [Open UI](https://open-ui.org/)
* [Postman](https://www.postman.com/downloads/)
* [MongoDB](https://www.mongodb.com/try/download/community)
* [MongoDB Compass](https://www.mongodb.com/try/download/compass)
* [Git](https://git-scm.com/downloads)

<br>

</details>



<br>

## Sección 2) EndPoints y Recursos

### 2.0) EndPoints [🔝](#índice-)

<details>
 <summary>Ver</summary>
 
#### Autenticación
* http://localhost:8098/api/v1/auth/login (Autenticación)
* http://localhost:8098/api/v1/auth/signin (Registro)
  
#### Gestión de Productos
* http://localhost:8098/api/v1/productos/ (Agregar)
* http://localhost:8098/api/v1/productos/{id} (Editar)
* http://localhost:8098/api/v1/productos/{id} (Eliminar)
* http://localhost:8098/api/v1/productos/listado (Listado Paginado)
* http://localhost:8098/api/v1/productos/last-producto (Último Producto)
* http://localhost:8098/api/v1/productos/listado-filter/{filtro} (Listado paginado con filtro)
* http://localhost:8098/api/v1/productos/codigo/{codigo} (Listado paginado según su código)
* http://localhost:8098/api/v1/productos/nombre/{nombre} (Listado paginado según su nombre)
* http://localhost:8098/api/v1/productos/descripcion/{descripcion} (Listado paginado según su descripción)
* http://localhost:8098/api/v1/productos/categoria/{categoria} (Listado paginado según su categoria)
* Revisar Swagger para visualizar el resto de los endpoints 

#### Administración de Usuarios  
* http://localhost:8098/api/v1/admin/usuarios/ (Agregar)
* http://localhost:8098/api/v1/admin/usuarios/{id} (Editar)
* http://localhost:8098/api/v1/admin/usuarios/{id} (Eliminar)
* http://localhost:8098/api/v1/admin/usuarios/listado (Listado)
  


<br>

</details>


### 2.1) Recursos y Servicios [🔝](#índice-)

<details>
 <summary>Ver</summary>

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
 
</br>

#### Documentación Gráfica Acotada por razones de simplificación de documentación. Visualizar los videos desde la PlayList acerca de las Pruebas de Funcionalidad de la aplicación.


</details>


<br>

## Sección 3) Prueba de Funcionalidad y Referencias


### 3.0) Prueba de Funcionalidad [🔝](#índice-)

<details>
 <summary>Ver</summary>

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

 <br>

</details>


### 3.1) Referencias [🔝](#índice-)

<details>
 <summary>Ver</summary>

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

 <br>

</details>
