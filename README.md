# Creacion de proyecto base

Para la creacion de la estructura base del proyecto es importante saber las dependencias que deben ser importadas desde el inicio. 

Para la creacion del proyecto utilizamos el inicializador de proyecto online que nos proporciona spring:

        https://start.spring.io/

En esta web podemos configurar los parametros iniciales de nuestro poryecto como lo es el nombre, tipo de empaquetado, tipo de gestor de dependencias, version de java y de spring boot y muchas mas. Por otro lado tambien nos permite seleccionar las dependencias iniciales de las cuales la mas importante es la de spring webflux, la cual nos permitira trabajar con webflux. Las otras dependencias seleccionadas son dependencias que en el transcurso del proyecto se utilizaran.

## Creacion de estructura de capas
Para este proyecto utilizaremos una arquitectura de capas (Layer Architecture) la cual consiste en tener capas que separen las responsabilidades en nuestro proyecto. Para este proyecto base utilizaremos la tres capas principales que se tienen en cuenta la cuales son:

-   Model: Capa relacionada al modelo, es la que se encarga de contener todas las clases que representa el modelo de nuestro poryecto junto con las clases de pesistencia y acceso a datos.
-   Service: Capa relacionada a los servicios, es la que se encarga de contener toda la logica de negocio que se debe desarrollar para lograr con las necesidades que plantea el negocio.
-   Controller: Capa relacionada a los controladores, es la que se encarga de exponer los entry points o las rutas de acceso a nuestro servicios. No debe contener ningun tipo de logica. simplementa la exposicion de las rutas.

Es comun que la separacion de estas capas se haga por paquetes. Esto es lo que haremos inicialmente y posteriormente en sus respectiva ramas haremos los desarrollos correspondientes.
