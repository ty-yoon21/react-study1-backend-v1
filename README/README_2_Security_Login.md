Spring Security, Login
------


# 1. Build Gradle
```yaml
	//implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-data-redis'
	implementation group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.2'
	runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.2'
	runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.2'
```
Gradle Tab - 코끼리 우클릭 - reload gradle project --> 라이브러리 다운

# 2. Redis
```shell
sudo docker search redis
sudo docker pull redis
sudo docker image ls
sudo docker run --name local-redis -p 6379:6379 -d redis redis-server --appendonly yes
# docker run -d -p 16379:6379 --name redis6 redis:6.0 --appendonly yes --port 6379 --bind "0.0.0.0"

sudo docker create network redis-network
sudo docker network redis-network local-redis
sudo docker run -it --network redis-network --rm redis redis-cli -h local-redis

https://jistol.github.io/docker/2017/09/01/docker-redis/
: redis

https://ppaksang.tistory.com/17
https://www.daleseo.com/docker-networks/
: 도커 네트워크

https://info-lab.tistory.com/42
: redis bind

```
