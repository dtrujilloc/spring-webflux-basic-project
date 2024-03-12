# Creacion de proyecto base

Para la creacion de la estructura base del proyecto es importante saber las dependencias que deben ser importadas desde el inicio. 

Para la creacion del proyecto utilizamos el inicializador de proyecto online que nos proporciona spring:

        https://start.spring.io/

En esta web podemos configurar los parametros iniciales de nuestro poryecto como lo es el nombre, tipo de empaquetado, 
tipo de gestor de dependencias, version de java y de spring boot y muchas mas. Por otro lado tambien nos permite seleccionar 
las dependencias iniciales de las cuales la mas importante es la de spring webflux, la cual nos permitira trabajar con webflux. 
Las otras dependencias seleccionadas son dependencias que en el transcurso del proyecto se utilizaran.

## Creacion de estructura de capas
Para este proyecto utilizaremos una arquitectura de capas (Layer Architecture) la cual consiste en tener capas que separen 
las responsabilidades en nuestro proyecto. Para este proyecto base utilizaremos la tres capas principales que se tienen en 
cuenta la cuales son:

-   Model: Capa relacionada al modelo, es la que se encarga de contener todas las clases que representa el modelo de nuestro 
poryecto junto con las clases de pesistencia y acceso a datos.
-   Service: Capa relacionada a los servicios, es la que se encarga de contener toda la logica de negocio que se debe desarrollar 
para lograr con las necesidades que plantea el negocio.
-   Controller: Capa relacionada a los controladores, es la que se encarga de exponer los entry points o las rutas de acceso a 
nuestro servicios. No debe contener ningun tipo de logica. simplementa la exposicion de las rutas.

Es comun que la separacion de estas capas se haga por paquetes. Esto es lo que haremos inicialmente y posteriormente en sus 
respectiva ramas haremos los desarrollos correspondientes.

### 1. Capa de Modelo
Esta capa contiene todas los conceptos relacionados al modelo del negocio, por ejemplo las entidades (conceptos basicos), 
relacion entre entidades, repositorios (objetos que nos permiten realizar operacion con las BDs), DAO (objetos que nos permiten acceder a la data). Basicamente en esta capa se encapsula todo lo relacionado a la data.

Veamos que se encapsula en cada uno de los paquetes:

- Document: inicialmente es importante conocer los conceptos que se transformaran en entidades, de tal forma que representaran 
nuestro negocio. En este caso tenemos los coceptos de Producto y Categoria, que basicamente representan la relacion de que un 
producto puede ser de una categoria y una categoria puede ser multiples productos. Cabe resaltar que como se estan trabajando 
con mongoDB, una BD NoSQL, aqui las entidades o tablas son conocidas como Colecciones y un registro es conocido como Documento, 
dentro del documento se encuentran cada uno de los campos que hacen relacion a las columnas de la tablas. Este Documento es la 
representacion de un objeto Json donde se puede ver que cada campo y valor son representados por una llave-valor. 

- Repository: Por otro lado estan los repositorios que hacen uso de la API de acceso a datos para MongoDB, es como la API de 
JPA para spring boot pero en este caso para Webflux y para MongoDB, esta dependencia lo que nos permite es la construccion de 
repositorio con el objetivo de tener acceso a nuestra BD de una manera rapida, sencilla y transparente. Notar como con solo la 
extencion de una clase podemos tener acceso a todos los metodos suficientes y necesarios para realizar un CRUD completo. 
Tambien es posible construir operaciones utiliznado la sintaxis del nombre del metodo o incluso especificar la operacion por 
medio de una query utilizando la sintaxis normal como para hacer una query en mongoDB, Podemos ver algunos ejemplos en el 
Repositorio de Producto donde especificamos unas querys que tienen el mismo proposito o resultado pero la sintaxis es tanto
por metodo (con el nombre del metodo se construye la query automaticamente, esto se hace utilizando las palabras reservadas) y 
por query (se especifica la query en el metodo por medio de etiquetas).

Por otro lado tambien en el archivo de resources/application.yml se configuro la conexion a la BD. en este archivo se pueden 
configurar multiples aspectos de nuestra aplicacion (puertos, conexiones a BD, nombre de aplicacion, etc).

Y tambien en la clase InitDataMongoDBConfiguration en el paquete de configuration, se realizo la configuracion inicial de 
los datos de prueba iniciales, esto debido a que para mongoDB no existe un archivo init como lo hay para diferentes BBDD 
relacionales. Lo que se realiza es una clase de configuracion donde se utiliza el repository para guardar datos iniciales.

### 1. Capa de Service
En este capa se construyen todas las clases que estan relacionadas con la logica de negocio y/o reglas de negocio. Por lo 
general las clases que contienen esta logica son los Services pero hacen uso de diferentes clases que permiten lograr todo 
el proceso como mapeadores, validadores y mas.

Para este caso se utilizaron algunas reglas de negocio como:
- Categoria:
  1. Las categorias siempren tienen que ser guardadas en la BD con el nombre en mayuscula.
- Productos:
  1. Los productos tienen que ser guardados siempre con el nombre en miniscula.
  2. Los productos no pueden ser guardados si no existe la categoria en la BD.
  3. No se puede repetir el nombre de un producto en la BD. tiene que ser unico.

Las reglas anteriores estan plasmadas en el servicio de cada uno. donde tambien se hizo la transformacion de los datos a unos
DTOs de respuesta, y para esto se utilizaron clases de mapeo para separar las responsabilidades. Tambien hay que resaltar que
en estas clases de Servicios se hace uso de las clases de repositorio para poder realizar operaciones con la DATA.