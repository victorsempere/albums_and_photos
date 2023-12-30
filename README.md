# Introducción

Microservicio en SpringBoot con 3 endpoints:
* albums-and-photos/db -> Devuelve la lista de álbumes junto con las fotos que contiene a partir de la información almacenada en base de datos.
* albums-and-photos/mem -> Devuelve la lista de álbumes junto con las fotos que contiene directamente desde memoria.
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

Para la parte de testing utilizo JUnit + Mockito. 

También utilizo OpenAPI como ejemplo de definición de un servicio de la capa de infraestructura. Para los tests utilizo la librería Jackson que viene en el framework de Spring, para poder leer de disco los ficheros de pruebas.

También tenemos la opción de utilizar Docker para arrancar el servicio dentro de un contenedor y poder validar el funcionamiento del microservicio con diferentes máquinas virtuales. Nos permite también depurar el artefacto desplegado.

# Patrones de diseño

Algunos de los patrones de diseño que utilizo en el desarrolo, son:
* Adaptadores y puertos
* Factorías
* Interfaces
* Decoradores

# Principios utilizados

Vamos a utilizar el principio DDD (Domain Driven Design), para realizar el diseño de la aplicación, sobre la arquitectura hexagonal. Dejando el dominio y la lógico de nuestro negocio, aislado y desacoplado del exterior. La parte de infraestructura que conecta con los datos externos para obtener los álbumes y las fotos, lo he definido utilizando OpenAPI junto con el plugin de maven que genera el cliente Java para el API definida en el fichero openapi.yaml.

Para el desarrollo, opto por la metodología TDD (Test Driven Development), gracias a la cual, tenemos un código que ofrece garantías porque tiene que tener cubiertas las funcionalidades por tests automáticos. consigo que antes de pasar a desarrollar el código tengamos que definir las pruebas que garantizarán que nuestro desarrollo cumple con las especificaciones que se espera y por lo tanto, al desarrollar, tendremos una guía que nos indica si nuestro software cumple lo esperado o no, además de aportar confianza en el software ya que siempre se encontrará validado de forma automática, lo que nos permite iterar en el desarrollo introduciendo mejoras, de forma evolutiva reduciendo el riesgo de introducir errores al evolucionar el sistema.

TODO: Hablar de los principios SOLID

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

## Entorno de pruebas
# TODO: REVISAR POR QUÉ NO CONECTA
La consola web de la base de datos H2 que utiliza el microservicio se encuentra disponible en la ruta h2-console. Por ejemplo al ejecutar desde VSCode, acceder a través de: http://localhost:8080/h2-console

La configuración de los parámetros de acceso, son (ver el fichero application-test.properties de la carpeta microservice/src/test/resources):
* Driver class: org.h2.Driver
* JDBC URL: jdbc:h2:./target/albums-and-photos-database-tests
* User name: sa
* Password: {sin contraseña}
