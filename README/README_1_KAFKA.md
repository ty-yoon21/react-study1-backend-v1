Kafka
-------


# 1. Install In Mac
https://crazy-horse.tistory.com/57?category=1076666  
https://erjuer.tistory.com/89  
```
--vi ~/docker-compose.yml 
version: '2'
services:
  zookeeper:
    image: wurstmeister/zookeeper
    container_name: zookeeper
    ports:
      - "2181:2181"
    restart: unless-stopped

  kafka:
    image: wurstmeister/kafka
    container_name: kafka
    ports:
      - "9092:9092"
    environment:    
      KAFKA_ADVERTISED_HOST_NAME: localhost
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
    restart: unless-stopped
```


# 2. Kafka <-> SpringBoot 연동
https://velog.io/@taehodot/SpringBoot-%EC%B9%B4%ED%94%84%EC%B9%B4%EC%99%80-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EC%97%B0%EB%8F%99  

kafka-console-consumer.sh --topic reactPortalTopic --bootstrap-server localhost:9092 --from-beginning



# 3. Order of Dev.
```
1) Install Kafka
1.1) Install
git clone https://github.com/wurstmeister/kafka-docker
docker-compose -f docker-compose.yml up -d
1.2) Create TOPIC

2) Setting Kafka
2.1) build.gradle
implementation 'org.springframework.kafka:spring-kafka'
2.2) applictaion.yml

3) Develop Kafka Producer, Consumer

4) Add kafka to controller

5) Check kafks
docker exec  -i -t kafka bash
kafka-console-consumer.sh --topic reactPortalTopic --bootstrap-server localhost:9092 --from-beginning
```