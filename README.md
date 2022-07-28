# Publish-subscribe con RabbitMQ
 
 Este proyecto está diseñado para poderse implementar de la siguientes formas:
 - Varias maquinas en una sola simplemente simuladas como terminales separadas.
 - Varias maquinas ubicadas en la misma red.
 - Varias maquinas que no se encuentran en la misma red.
 - funciona su implementación en windows tanto en una distribución de linux como Ubuntu por ejemplo.
 * * *
 * Primero instalamos docker y habilitamos WSL2 tutorial recomendado: https://www.youtube.com/watch?v=_et7H0EQ8fY 
 * Ya teniendo todo docker funcionando, pasamos a instalar una imagen comunitaria de RabbitMQ en docker
 ### latest RabbitMQ 3.10
 ~~~
 docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management
 ~~~
 * * *
 * Recomendación: trabajar con visual studio code
 # Extensiones y herramientas necesarias
 - .NET en el equipo https://dotnet.microsoft.com/en-us/download/dotnet-framework
 - .NET Extension Pack (VisualStudioCode)
 - .NET install tools for extension authors (VisualStudioCode)
 - C# (VisualStudioCode)
 - python en el equipo https://www.python.org/downloads/
 - python (VisualStudioCode)
 - en una consola cmd instalar la libreria pika para python con el comando(a veces es python o py dependiendo de la version de python instalada): 
 ~~~
 python -m pip install pika --upgrade
 ~~~
 - java en el equipo https://www.java.com/es/download/ie_manual.jsp
 - java (VisualStudioCode) https://www.youtube.com/watch?v=AvOIyjBY_mk
 * * *
 * Clonar el repositorio
 * * * 
 - En este proyecto se contempla lo siguiente:
     - Un broker que viene siendo la imagen de RabbitMQ en docker
     - un publicador y recibidor en cada lenguaje
     - un topic/tema llamado hello
 * * *
 Nota: tener encendida la imagen en docker
 
 # Simular diferentes maquinas en el mismo equipo a traves de diversas terminales
 
 Para esto seguir las siguientes instrucciones:
 
 - Podemos iniciar con los archivos C#, abrimos una terminal y ubicamos la ruta en la carpeta Send
 ![image](https://user-images.githubusercontent.com/67379041/181574479-ca61879e-4980-44e7-9eff-7283c9b5cdbc.png)
 
   y aanalogamente hacemos lo anterior para otra terminal con la carpeta Receive.

 - ahora en cada terminal ejecutamos el siguiente comando: dotnet add package RabbitMQ.Client
 - despues el comando: dotnet restore
 - ahora en la terminales ejecutamos dotnet run y ya con esto hemos simulado un publish-subscribe en C#, se puede en Send escribir cualquier cosa y enviar con enter, para "cerrar la sesion de este publicador es escribiendo exit".
 - ¡EnHoraBuena! ya tenemos un pub-sub en C#
 -Ahora vamos a conectar los otros pub-sub en los otros lenguajes.
 - abrimos los archivos python Send.py & Receive.py, visual nos ofrece en la parte superior derecha una opción para ejecutarlos.
 ![image](https://user-images.githubusercontent.com/67379041/181583193-d5dcff44-2169-4e7e-8a1d-534ed5961fca.png)
 - ¡EnHoraBuena! ya tenemos un pub-sub en python
 - Ahora abrimos los Emisor y receptor de JAVA y los ejecutamos similar que en python.
 - ¡EnHoraBuena! ya tenemos un pub-sub en JAVA
 - con todo lo anterior si enviamos desde el publicador/emisor de un lenguaje, llegará a uno de los suscriptores/receptores de los otros lenguajes, probad.
 
 # Equipos/maquinas en la misma red
 
 - como son tres lenguajes, se puede una maquina por lenguaje, dando en total 3 maquinas, pero si quieres probar con más, puede correr los mismos lenguajes en más maquinas o intentar montar un pub-sub en otro lenguaje para otra maquina.
 
- para lo que es en la misma red o en diferentes redes necesitamos que uno de los equipos involucrados se convierta en un servidor local-web, para esto instalamos https://www.appserv.org/en/download/ durante la instalación de este saldrá si queremos instalar MySQL y pgadmin, esto es opcional por si interesa una base de datos ya sea para una pagina o algo por el estilo.
 
- para llevar a cabo en la misma red, necesitamos verificar que los equipos se puedan comunicar entre ellos a traves de sus IP publicas, para esto en windows abrimos un cmd y ejecutamos ipconfig, aqui nos saldrá diversa información, si estamos por wifi buscamos el apartado wifi y apuntamos la ipv4 que nos arroja, analogamente si es por ethernet. En linux es una terminal y ejecutar ifconfig

- ya teniendo las ip's publicas de todos los equipos involucrados, vamos a verificar conexion, para esto vamos a suponer que tenemos los equipos 1,2,3, entonces para saber si el equipo 1 se puede comunicar con los otros, abrimos un cmd en este equipo y ejecutamos ping IPdeOtroEquipo debe salir paquetes enviados tanto y recibidos en lo posible la misma cantidad o mayor a cero.

- si no funciona, significa que el firewall de los equipos no deja realizar está comunicación, para esto es necesario desactivarlo para la ejecución de este proyecto, ya despues volverlo a activar https://www.youtube.com/watch?v=i_XM1gAK90o

- si ya instalamos el servidor local y el ping funciona entre los equipos, ahora vamos a los archivos send y receive de cada lenguaje y vamos a encontrar un codigo como el siguiente: 
~~~
% Python
connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))

% C#
var factory = new ConnectionFactory() {HostName = "localhost"};

% Java
ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
~~~

lo que vamos a hacer es reemplazar la cadena de texto 'localhost' en cada lenguaje por la IP publica del equipo que está ejecutando el servidor local 'IPservidorLocal'

- ya con esto es ejecutar cada archivo y listo - ¡EnHoraBuena! ya tenemos pubs-subs en diferentes maquinas ubicadas en la misma red.
- Nota: si llegado al caso viniste directamente a esta sección de instalar pub-sub en diferentes maquinas, debes en los archivos de C# realizar los primeros pasos de la sección "Simular diferentes maquinas en el mismo equipo a traves de diversas terminales" de ejecutar unos comandos en los archivos de C#.

# Equipos/maquinas ubicadas en distintas partes del planeta

- para esto hacemos lo mismo que la anterior sección "Equipos/maquinas en la misma red", a diferencia que vamos a tener los archivos de cada lenguaje separados en diferentes equipos.

- para llevar esto necesitamos en cada equipo instalar el programa https://www.vpn.net/ 
- despues de instalar, todos los equipos ingresan y encienden el hamachi, a lo que les pide una cuenta o crearla si no la tienen, ya aqui es esperar que el equipo que tiene el servidor local haga lo siguiente:
    - enciende hamachi
    - en la barra menu encuentra la opcion red/network, y le da crear una red a lo que pide un ID y contraseña, el ID es un nombre, palabra, numeros, lo que quiera al  igual que la contraseña.
- ya con lo anterior ahora los otros equipos le dan tambien en red pero unirse a una existente e ingresar las credenciales que puso el que creó la red.
- ahora para que los equipos se comuniquen, debemos desactivar el firewall de windows para hamachi (mientras se ejecuta el proyecto, ya despues volvemos a activarlo) en cada equipo https://support.logmeininc.com/central/help/resolving-hamachi-request-timed-out-for-windows-firewall#:~:text=At%20the%20top%20of%20the,clear%20the%20checkbox%20for%20Hamachi.
- para probar si hay conexion entre equipos, en hamachi encontramos la red y los equipos conectados, podemos darle doble clic en un equipo y esto abrira una terminal donde le hace ping a ese equipo, no debe salir Request Timed Out en todas, si sale una o algo no pasa nada, pero no en todas porque entonces algo hicimos mal.
- ahora en el equipo del servidor junto al boton encender/apagar del hamachi vamos a ver que nos da una IPv4/IPv6, copiamos la ipv4 y está va a ser la ip publica a la que se van a conectar todos de la siguiente forma: 
    - si ya instalamos el servidor local y el ping funciona entre los equipos, ahora vamos a los archivos send y receive de cada lenguaje y vamos a encontrar un codigo como el siguiente: 
~~~
% Python
connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))

% C#
var factory = new ConnectionFactory() {HostName = "localhost"};

% Java
ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
~~~

lo que vamos a hacer es reemplazar la cadena de texto 'localhost' en cada lenguaje por la IP publica del equipo que está ejecutando el servidor local 'IPservidorLocal'

* * *

listo, esto ha sido todo para los tres diferentes modos, muchas gracias por hacer uso de este proyecto, ¡Adios! nos volveremos a ver en otra ocasión.
