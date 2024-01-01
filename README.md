# Introducción

Microservicio en SpringBoot con 3 endpoints:
* albums-and-photos/db -> Elimina la información que hubiera en memoria. Realiza una carga de datos de la web. Devuelve la lista de álbumes junto con las fotos que contiene a partir de la información almacenada en base de datos.
* albums-and-photos/mem -> Elimina la información que hubiera en memoria. Realiza una carga de datos de la web. Devuelve la lista de álbumes junto con las fotos que contiene directamente desde memoria.
* db-albums -> Devuelve los datos almacenados en la tabla Album de la base de datos H2
* db-photos -> Devuelve las fotos almacenadas en la tabla Photo de la base de datos H2

Extracción de datos de albums y photos:
* “albums”: https://jsonplaceholder.typicode.com/albums
* “photos”: https://jsonplaceholder.typicode.com/photos

# Arquitecturas

Sigo una arquitectura Hexagonal o patrón de puertos y adaptadores. La idea que hay detrás es la de tener un código lo más desacoplado posible, que minimice el tiempo que necesitemos para poder evolucionar, mantener y testear el código.
Con esta arquitectura, dejamos en la capa más externa y por lo tanto más alejada de la lógica de nuestro negocio, cosas como el motor de base de datos, sistemas de comunicación, frontend, etcétera. Lo que nos permite construir un software que sea fácilmente testeable, escalable, mantenible y robusto. Nos permite adaptar más rápidamente nuestro software a nuevos motores de base de datos, frameworks, protocolos de comunicación, interfaces de usuario, etcétera.

# Tecnologías

Proyecto Maven, utilizando Java 17 + Spring (Spring-Boot, Spring-Data, Spring-Cache). 

Para la parte de testing utilizo JUnit + Mockito. Además de utilizar el JaCoCo para validar la cobertura del código (Ejecutar mvn package y se nos generará en target/site una web con los resultados de la cobertura)

También utilizo OpenAPI como ejemplo de definición de un servicio de la capa de infraestructura. Para los tests utilizo la librería Jackson que viene en el framework de Spring, para poder leer de disco los ficheros de pruebas. Al utilizar el plugin org.openapitools:openapi-generator-maven-plugin, sonar dará errores y warnings que se irán corrigiendo al actualizar la versión del plugin pero que se nos salen de nuestro control.

Tenemos disponibles los fichero Dockerfile, docker-compose.yml y docker-compose-debug.yml para realizar un despliegue en una imagen Docker del microservicio y poder ejecutar/depurar en un entorno aislado.

# Paradigmas de programación

Para este desarrollo he utilizado los 3 paradigmas de programación: programación estructurada, programación orientada a objetos y programación funcional.

# Patrones de diseño

Algunos de los patrones de diseño que utilizo en el desarrolo, son:
* Adaptadores y puertos
* Factorías
* Interfaces
* Decoradores

# Principios utilizados

Vamos a utilizar el principio DDD (Domain Driven Design), para realizar el diseño de la aplicación, sobre la arquitectura hexagonal. Dejando el dominio y la lógico de nuestro negocio, aislado y desacoplado del exterior. La parte de infraestructura que conecta con los datos externos para obtener los álbumes y las fotos, lo he definido utilizando OpenAPI junto con el plugin de maven que genera el cliente Java para el API definida en el fichero openapi.yaml.

Para el desarrollo, opto por la metodología TDD (Test Driven Development), gracias a la cual, tenemos un código que ofrece garantías de cumplir las funcionalidades esperadas por el código dado que se desarrollan primero los tests y a partir de ellos se desarrolla el código. No he definido los tests para todos los niveles de la aplicación, desarrollé los tests para la capa de aplicación con los requisitos funcionales a partir del enunciado del ejercicio. Después he añadido más tests, como por ejemplo los tests de carga o tests unitarios.

He tenido en cuenta los principios SOLID a la hora de realizar la implementación:
S (Single Responsibility Principle): Cada módulo debe tener únicamente a un actor como responsable de sus cambios. En su nivel más bajo afecta a las funciones y clases. Pero si pasamos al nivel de componentes, nos ayuda a organizar las clases en los distintos componentes, ya que las clases que se vean afectadas por el mismo actor, deberían compartir componente. Y si finalmente, pasamos al nivel de arquitectura, nos ayuda a definir los límites de la arquitectura.

O (Open-Closed Principle): Este es el motor de la arquitectura de los sistemas. Un sistema debe quedar cerrado para su modificación y abierto para su extensión. El sistema se define a partir de interfaces que pueden tener diferentes implementaciones, que pueden ser utilizadas de forma indistinta. La interface debe quedar cerrada a modificaciones pero se pueden añadir elementos a la interface. 

L (Liskov Substitution Principle): Este principio aplica a nivel de clases y objetos, e indica que si una clase hereda de otra, deberíamos poder utilizar esa clase como si fuera su padre sin necesidad de conocer sus diferencias. Deberíamos poder ampliar el uso de este concepto al nivel de arquitectura. 

I (Interface Segregation Principle): Este principio nos dice que las interfaces deben contenter únicamente los métodos necesarios, es decir, todas los métodos de una interface deben tener sentido para todas las implementaciones de la interface. Esto garantiza que el sistema esté desacoplado.

D (Dependency Inversion Principle): Este principio dicta que un sistema debe estar separado en módulos. Que las dependencias entre los módulos siempre deben viajar desde los niveles más altos (framework, bases de datos, ...) hacia los más bajos (dominio, lógica de negocio), pero nunca al revés. Además la comunicación entre los diferentes módulos deben estar definidas a través de interfaces, garantizando así, que no se acoplan.



# Tipos de datos
Antes de utilizar alguna implementación de un tipo Lista, validar si implementa la interface RandomAcces. Si no se implementa, el rendimiento puede caer en picado a una complejidad cuadrática. El tipo HashMap también ofrece tiempos constantes para el acceso a los datos aunque no garantiza el orden.

Voy a utilizar tipos inmutables para los objetos del dominio y para los DTO del cliente externo del que obtenemos los datos de albums y photos. Esto no cumple 100% los principios Clean Code porque tenemos un constructor con más de 4 parámetros pero en este caso prefiero saltarme esa restricción para obtener ventajas a la hora de reducir riesgos al manipular las instancias de forma concurrente. Además, sólo existe un punto localizado donde se tiene que utilizar ese constructor.

## TreeSet
Complejidad: O(log(n))
* Añadir
* Eliminar
* Contiene

Synchronizado: No. Utilizar: SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));

Nulos: No

Duplicados: No

## ArrayList
Duplicados: Si

Synchronizado: No. Utilizar Collections.synchronizedList()

Complejidad: O(n)
* Insertar elementos

Complejidad: Tiempo constante
* size
* isEmpty
* get
* set
* iterator
* listIterator

Fuente: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html

# Vulnerabilidades detectadas en dependencias por el analizador de dependencias de Red Hat

* CVE-2018-10054 - Grave - Execución de código
* CVE-2022-45868 - Medio - Exposición de información

# Consola H2
## Entorno de ejecución
La consola web de la base de datos H2 que utiliza el microservicio se encuentra disponible en la ruta h2-console. Por ejemplo al ejecutar desde VSCode, acceder a través de: http://localhost:8080/h2-console

La configuración de los parámetros de acceso es (ver el fichero application.properties de la carpeta microservice/src/main/resources):
* Driver class: org.h2.Driver
* JDBC URL: jdbc:h2:./target/albums-and-photos-database
* User name: sa
* Password: {sin contraseña}

# Pendiente de resolver

* Warnings en clases de test por estar marcadas como public. En teoría con JUnit 5 ya no sería necesario marcarlas como public si las clases de Test están en el mismo paquete que la clase a validar. Sin embargo el comando mvn test no ejecuta los tests que tienen visiblidad por defecto. Como dato para buscar la solución. Al mostrar el pom completo con los valores por defecto, veo que se está incluyendo la depedencia con junit:junit versión 4 aunque si muestro el dependency:tree no aparece por ningún lado el artefacto junit:junit.
* Acceso a la consola web de H2 durante la ejecución de los tests

# Fuentes de datos

Los principios usados y descritos han sido sacados de los libros de Robert C. Martin (https://en.wikipedia.org/wiki/Robert_C._Martin): Clean Code y Clean Architecture.
Para tomar las decisiones de los tipos de datos, he utilizado la documentación de Java (https://docs.oracle.com/en/java/javase/17/docs/api).