# Spring WebFlux y Apache Kafka - Caso de ejemplo

El objetivo es ilustrar el uso de Spring Webflux como framework para construir una aplicación reactiva que a su vez se comunique con otros microservicios utilizando Apache Kafka de forma asíncrona, haciendo uso del patrón pub-sub. 

Para cumplir con el enfoque anterior, se presenta una api desarrollada en Spring WebFlux, la cual se encargará de realizar un CRUD sobre los datos de facturas, almacenando dicha información en una base de datos embebida H2. 

Cada factura posee los siguientes campos:

- Rut Empresa
- Número de Factura
- Tipo de Factura
- Monto Total
- Fecha

Cada vez que se realice el registro de una nueva factura se enviará una notificación al tópico <ins>facturas</ins> en Apache Kafka, con los datos del rut y número de factura, de tal modo de que otro(s) microservicios puedan suscribirse a ese tópico y realizar operaciones con esos datos.

Para este ejemplo, un microservicio en nodejs se suscribirá al tópico y se encargará de recuperar los datos de la empresa, además de notificar vía mail a los usuarios finales.

Todo lo descrito anteriormente se ilustra en el siguiente diagrama:

![image](https://user-images.githubusercontent.com/13786553/182243164-e51003a5-b7bb-4029-acc9-c0a6f0d4af2d.png)

En este repositorio se encuentra el código fuente que soporta la parte de ingreso de facturas y el servidor de Apache Kafka, el cual requiere tener instalados docker y docker-compose de forma local. 

Una vez instalados, usted puede iniciar el servidor Apache Kafka situandose en el directorio principal que contiene el archivo docker-compose.yml y ejecutando el siguiente comando:

    docker-compose up -d
    
Lo anterior creará un volumen persistente en disco. Para detener el servidor Apache Kafka ingrese el siguiente comando:

    docker-compose down
    
Ahora puede ejecutar el servicio Spring WebFlux desde su IDE preferido (IntelliJ, Spring Tool Suite, etc), asegurese de tener instalada la versión 11 del JDK.
Una vez iniciado compruebe que se haya creado el tópico <ins>facturas</ins> en su servidor local de Apache Kafka

    docker exec -it kafka_kafka_1 /opt/bitnami/kafka/bin/kafka-topics.sh --list --bootstrap-server localhost:9092
    
Ahora realice el ingreso de alguna factura llamando al método Post con algún cliente como Postman, SoapUI, entre otros.
Compruebe que a medida que va registrando una factura su servidor Apache Kafka reciba los datos de facturas con el siguiente comando:

    docker exec -it kafka_kafka_1 /opt/bitnami/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic facturas --from-beginning
    

De esta forma se cumple la primera parte del diagrama, en el repositorio https://github.com/Marcelogu/facturas-consumer se incluye el proceso en NodeJs que se suscribe a ese servidor Apache Kafka y realiza el envío de mail.

