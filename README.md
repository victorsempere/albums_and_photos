# Introducción

Microservicio en SpringBoot con 3 endpoints:
* albums-and-photos/db -> Elimina la información que hubiera en memoria. Realiza una carga de datos de la web. Devuelve la lista de álbumes junto con las fotos que contiene a partir de la información almacenada en base de datos.
* albums-and-photos/mem -> Elimina la información que hubiera en memoria. Realiza una carga de datos de la web. Devuelve la lista de álbumes junto con las fotos que contiene directamente desde memoria.
* db-albums -> Devuelve los datos almacenados en la tabla Album de la base de datos H2
* db-photos -> Devuelve las fotos almacenadas en la tabla Photo de la base de datos H2

Extracción de datos de albums y photos:
* “albums”: https://jsonplaceholder.typicode.com/albums
* “photos”: https://jsonplaceholder.typicode.com/photos

# Arquitectura

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
Voy a utilizar tipos inmutables para los objetos del dominio y para los DTO del cliente externo del que obtenemos los datos de albums y photos. Esto no cumple 100% los principios Clean Code porque tenemos un constructor con más de 4 parámetros pero en este caso prefiero saltarme esa restricción para obtener ventajas a la hora de reducir riesgos al manipular las instancias de forma concurrente. Además, sólo existe un punto localizado donde se tiene que utilizar ese constructor.

## ArrayList
Duplicados: Si

Synchronizado: No. Utilizar Collections.synchronizedList()

Complejidad: O(1) (pero O(n) cuando se tiene que redimensionar la lista)
* Insertar elementos

Complejidad: Tiempo constante
* size
* isEmpty
* get
* set
* iterator
* listIterator

Fuente: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html

# Bibliografía

Los principios usados y descritos han sido sacados de los libros de Robert C. Martin (https://en.wikipedia.org/wiki/Robert_C._Martin): Clean Code y Clean Architecture.
Para tomar las decisiones de los tipos de datos, he utilizado la documentación de Java (https://docs.oracle.com/en/java/javase/17/docs/api).

# Proyecto
## Estructura del proyecto

Este proyecto es un proyecto modular de tipo Maven. En la raíz del proyecto, tenemos el fichero pom.xml principal que declara los módulos de los que se compone el proyecto.

A continuación, vamos a echar un vistazo a las carpetas del proyecto:
* .vscode
* external_clients
* microservice

### Carpeta: .vscode
El entorno de desarrollo que he utilizado es VSCode y he dejado configurado un lanzador para poder ejecutar/depurar el microservicio desde el IDE de forma sencilla. El lanzador se llama: AlbumsAndPhotosApplication.

Antes de poder ejecutar el microservicio tenemos que realizar una compilación completa, ya que el proyecto Maven está compuesto de 2 módulos y el módulo del microservicio depende de encontrar en el repositorio local, el artefacto del otro módulo. Para esto he dejado una tarea de VSCode que lanza la instalación del proyecto. La tarea se llama "Compile Project" y se ejecuta en VSCode accediendo desde la paleta de comandos, que accedemos con la combinación de teclas: Ctrl + Sft + P y seleccionando la opción: "Tasks Run Task"

### Carpeta: external_clients
Dentro de esta carpeta, es donde tendrán que ir los clientes que se utilicen en la capa de infraestructura del microservicio, para el acceso a datos externos. En nuestro caso, sólo tenemos un cliente, para el cual definimos el API en un fichero con formato OpenAPI y generamos un fichero pom.xml para utilizar el plugin org.openapitools:openapi-generator-maven-plugin para a partir de la definición del API autogenerar el cógido del cliente para Java.

Esta opción de desarrollo tiene un problema con el plugin de Sonar. Salvo que descartemos las carpetas autogeneradas, del análisis, vamos a ver errores de Sonar que no podemos hacer nada con ellos ya que vienen del código autogenerado por el plugin de Maven. 

Aunque, en caso de necesidad, el plugin si que nos permite poder ajustar el código autogenerado. Esta opción no es trivial pero nos permitiría corregir posibles bugs mientras estos bugs se corrigen.

### Carpeta: microservice
Dentro de esta carpeta está la implementación de nuestro microservicio, cumpliendo la funcionalidad que se indica en la introducción.

El microservicio, está estructurado utilizando el principio DDD. A continuación, defino las diferentes capas:

* Dominio: Es la capa más interna y que con menos frecuencia debería cambiar. Define los modelos de datos con los que se trabaja, interfaces de servicios sobre los que nos apoyamos para ejecutar las funciones esperadas. Por ejemplo en esta capa para tener en cuenta la L de SOLID he utilizado la interface Collection que nos permitirá en la capa de infraestructura, tener diferentes implementaciones utilizando tipos de datos diferentes en función de las necesidades.

* Aplicación: En esta capa se desarrolla la lógica de negocio a partir de los servicios y objetos del dominio. Los servicios del dominio son interfaces sin implementación. Esta la dejamos para una capa superior de forma que no acoplemos la lógica de negocio, por ejemplo, con el motor de base de datos. Esto nos permite cambiar el motor sin implicaciones graves y minimizando el esfuerzo

* Infraestructura: En esta última capa es donde tenemos las implementaciones de los interfaces del dominio y donde se decide los frameworks a utilizar, el motor de base de datos, conectores que se incluyen para comunicarse con el microservicio, .... Es la capa más externa y la primera a la que llegan los microservicios externos cuando quieren comunicar con nosotros.

Este principio DDD nos permite aplicar los principios SOLID que hacen de nuestro sistema, un sistema fácil de adaptar y evolucionar.

El desarrollo lo he hecho utilizando la metodología TDD. Y aunque no he partido de definir un conjunto exhaustivo que cubra el 100% del código, si que empecé el desarrollo al tener definidos los tests que valida el servicio de la capa de aplicación donde se encuentra la lógica del negocio.

El siguiente paso siguiendo la filosofía que describe Robert C. Martin del diseño emergente, sería ir iterando para aumentar el número de tests para llevar la cobertura hasta el 100% siguiendo los 4 principios siguientes:

1. Se tienen que ejecutar todos los tests en cada compilación y tienen que dar OK.
2. Eliminar duplicados (utilizando herramientas como Sonar, podemos detectar los duplicados)
3. El código escrito tiene que contar la tarea que realiza
4. Minimizar el número de clases y métodos

## Compilación

Para la compilación del proyecto, bastará con ejecutar desde la carpeta raíz del proyecto, en un terminal, el comando Maven:

```ps1
mvn install
```

En caso de querer compilar habilitando el uso de las métricas, el comando sería:

```ps1
mvn install -Pdev
```

En el perfil de dev, he dejado unas dependencias adicionales que el proyecto no tendrá en caso de estar compilado para desplegar.

Al terminar la ejecución del comando, tendremos en el repositorio local de Maven los artefactos de los 2 módulos del proyecto y estará todo listo para poder ejecutarlo. Si ejecutamos el comando en el terminal, VSCode puede necesitar tiempo para cargar los ficheros .class generados y reconocer lso ficheros fuentes.

## Ejecución

Tenemos diferentes opciones de ejecución:

### VSCode

Utilizar el lanzador que tenemos configurado en el fichero launch.json y que podemos seleccionar en la sección EJECUCIÓN Y DEPURACIÓN del IDE VSCode. Hay que tener en cuenta que antes de poder ejecutar tenemos que compilar el proyecto con la tarea "Compile Project" que está definida en el fichero tasks.json.

La compilación del proyecto es necesario para que se instale el artefacto del cliente que conecta con el API a la que tenemos que acceder para obtener los datos sobre los que tenemos que trabajar.

### Terminal

Los pasos para ejecutar el proyecto desde una terminal, son:

1. Abrir un terminal en la raíz del proyecto

2. Ejecutar el comando: 

```ps1 
mvn install
```

3. Ejecutar el comando: 

```ps1
mvn spring-boot:run -pl microservice
```

### Docker

Para ejecutar el sistema utilizando Docker:

1. Arrancar el servidor de contenedores

2. Desde VSCode:

    1. Hacer clic sobre el fichero docker-compose.yml y seleccionar la opción: Compose Restart

3. Desde Terminal:

    1. Desde la carpeta raíz del proyecto

    2. Si el contenedor está corriendo, ejecutar: 

```ps1
docker compose -f "microservice\docker-compose.yml" down
```
    3. Una vez que el contenedor está parado, ejecutar:

```ps1
docker compose -f "microservice\docker-compose.yml" up -d --build
```

## Cobertura de código

Para validar la cobertura del código utilizamos el plugin de JaCoCo para Maven. El plugin se ejecuta en la fase de empaquetado por lo que si ejecutamos el comando:

```ps1
mvn package
```

Al finalizar la tarea, en la carpeta target/site/jacaco encontramos una web con el resultado del análisis del código para validar la cobertura que se ha realizado con los tests. 

## Vulnerabilidades detectadas en dependencias por el analizador de dependencias de Red Hat

* CVE-2018-10054 - Grave - Execución de código
* CVE-2022-45868 - Medio - Exposición de información

## Consola H2
### Entorno de ejecución
La consola web de la base de datos H2 que utiliza el microservicio se encuentra disponible en la ruta h2-console. Por ejemplo al ejecutar desde VSCode, acceder a través de: http://localhost:8080/h2-console

La configuración de los parámetros de acceso es (ver el fichero application.properties de la carpeta microservice/src/main/resources):
* Driver class: org.h2.Driver
* JDBC URL: jdbc:h2:./target/albums-and-photos-database
* User name: sa
* Password: {sin contraseña}

## Pendiente de resolver

* Warnings en clases de test por estar marcadas como public. En teoría con JUnit 5 ya no sería necesario marcarlas como public si las clases de Test están en el mismo paquete que la clase a validar. Sin embargo el comando mvn test no ejecuta los tests que tienen visiblidad por defecto. Como dato para buscar la solución. Al mostrar el pom completo con los valores por defecto, veo que se está incluyendo la depedencia con junit:junit versión 4 aunque si muestro el dependency:tree no aparece por ningún lado el artefacto junit:junit.
* Acceso a la consola web de H2 durante la ejecución de los tests
* Configuración del entorno de evaluación de métricas
