# Introduccion

En este proyecto se evidenciara la forma de crear una API con programacion reactiva utilizando Spring Webflux. Para esto utilizaremos como peristencia una BD NoSQL en Mongo, esta BD estar contenerizada en Docker para no hacer ningunt tipo de instalacion de servidor en nuestro PC. Para la construccion y ejecucion de este contenedor se dejaran los comando necesarios, pero es responsabilidad de cada uno investigar y conocer como funciona docker como herramienta de contenerizacion.

# Levantamiento de contenedor de MongoDB
Lo primero que se debe realizar es la descarga de la imagen necesaria para crear el contendor.

    docker pull mongo

A continuacion se debe ejecutar el comando para crear el contendor el cual contendra mongo que nos permitira persistir la informacion de la API:

    docker run --name mymongodb -p 27027:27017 -e MONGO_INITDB_ROOT_USERNAME=daetruji -e MONGO_INITDB_ROOT_PASSWORD=123456 -d mongo

en el comando anterior se esta indicando que se cree una contendor llamado "mymongodb" donde exponemos el puerto interno del contendor (27017) al puerto de nuestra maquina (27027), esto nos permite la conexion al mongo desde nuestra maquina. Por otro lado les pasamos los parametros de ROOT_USERNAME y ROOT_PASSWORD para especificar que credenciales de conexion son necesarias para la conexion a nuestra BD y por ultimo indicamos que el contendor se ejecute en detach (-d) o en segundo plano, esto nos permite no tener ocupada la consola con la ejecucion del contendor y todo se estara ejecutando en segundo plano, y la ultima parte del comando es precismente el nombre de la imagen con la version que queremos para la construccion del contenedor, en este caso tomara la ultima version de mongo para la construccion. Existe muchos mas parametros que podemos indicar como volumenes y demas pero eso ya es responsabilidad de cada uno para habilitra todas estas opciones segun lo que se necesite.

Una vez ejecutado este comando ya se puede utilizar alguna herramienta para la conexion y visualizacion de las BDs, para esto podemos utilzar studio 3T o MongoDB Compass como GUI.
