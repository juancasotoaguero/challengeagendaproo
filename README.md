# Proyecto : rabbitMq example

Es una aplicación hecha en java con spring boot, usa RabbitMq como broker para uno de los flujos. Dentro del repositorio se encuentra definido un pipeline para ejecucion de los tests unitarios y despliegue continuo a aws cuando se realicen push a la rama Master.

# Apis disponibles 
POST:  /api/v1/auth/signup = Crear usuario

POST:  /api/v1/auth/signin = Iniciar sesion

POST:  /api/v1/product/ = Crear un producto

PATCH: /api/v1/product/{id} = Modificar un producto

GET:   /api/v1/product/ = Listar todos los productos

GET:   /api/v1/product/{id} = Obtener un producto por id

GET:   /api/v1/product/byName = Listar los productos filrando por nombre

GET:   /api/v1/category_product/productByCategory = Traer detalle de cantidad de productos por categoria

DELETE:/api/v1/product/{id} = Eliminar un producto

# Prerequisitos
1- Java 17

2- Spring boot 3.3.2

3- Docker

4- RabbitMQ : https://www.rabbitmq.com/docs/download

# Instalación, ejecución y pruebas 
1- Clonar repositorio

2- Abrir la terminal en la raiz del proyecto dentro de ./challenge

3- Levantar servidor rabbitmq con el siquiente comando:

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management

4- Abrir otra ventana de la terminar y compilar el proyecto :  ./mvnw clean install (windows) ó mvn clean install

5- Ejecutar el proyecto :  ./mvnw spring-boot:run (windows) ó mvn spring-boot:run  por defecto la app se ejecuta en el puerto 8080

6- Abrir el archivo de la coleccion para postman que se encuentra en esta ubicación https://github.com/juancasotoaguero/challengeagendaproo/tree/develop/Postman  el archivo : 

apiChallengeAgendaPro.postman_collection.json 

Este ya tiene los endpoint configurados para ejecutar localmente 
![image](https://github.com/user-attachments/assets/f7a84362-062c-4320-a89d-4e13bf9ccd3c)

7-Los endpoints se encuentran ordenados, primeramente para crear usuario y luego para iniciar sesión, resaltado amarillo y los demás endpoints en recuadro rojo, deben tener autenticación.
![image](https://github.com/user-attachments/assets/06d39637-f3cf-45ee-8365-cc3fd9acda1b)

8- La autenticacion se realiza copiando el token generado en el endpoint signin en la sección de Authorization Bearer Token.

![image](https://github.com/user-attachments/assets/6af75881-65f9-44f4-a813-ae74ff160cd3)
![image](https://github.com/user-attachments/assets/95d0782b-f6ae-43c0-9e93-23d305232895)

# Instancia en AWS

La aplicación se encuentra desplegada en una instancia ec2 de aws: 
http://18.118.254.207:8080/swagger-ui/index.html#/

También se encuentra el archivo para importar en el postman y probar los endpoint desplegados en la instancia aws

