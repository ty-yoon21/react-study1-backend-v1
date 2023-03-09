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

# 3. Security
```java
// Spring security 5.7 ver... WebSecurityConfigurerAdapter deprecated...
// spring security + jwt
// https://velog.io/@junho5336/SpringBoot-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-with.-SpringSecurity-JWT

// Redis + JWT Refresh Token
// https://wildeveloperetrain.tistory.com/59

// JWT 적용 전략
// https://velog.io/@yaytomato/%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%90%EC%84%9C-%EC%95%88%EC%A0%84%ED%95%98%EA%B2%8C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%B2%98%EB%A6%AC%ED%95%98%EA%B8%B0
// https://velog.io/@0307kwon/JWT%EB%8A%94-%EC%96%B4%EB%94%94%EC%97%90-%EC%A0%80%EC%9E%A5%ED%95%B4%EC%95%BC%ED%95%A0%EA%B9%8C-localStorage-vs-cookie
// XSS, CSRF, CORS, Cookie, Session, Token
```

### 적용순서
1. Front에서 로그인, 회원등록 페이지 생성
2. Back단에서 Security설정, JWT설정, 로그인/회원등록 로직 구현
3. JWT Refresh Token 설정

### 상세
1. Front에서 로그인, 회원등록 페이지 생성
2. Back단에서 Security설정, JWT설정, 로그인/회원등록 로직 구현
```
1. security/config/SecurityConfig.java
--> Spring Security 설정 (+ WebSecurity, JWT Provider, JwtAuthenticationFilter 적용)
2. security/jwt/JwtAuthenticationFilter.java
--> token 검증 및 권한정보 획득
3. security/jwt/JwtEntryPoint.java
--> JWT 관련 에러 처리
4. security/jwt/JwtProvider.java
--> JwtAuthenticationFilter.java에서 하는 실제적인 utils 기능
--> 토큰생성, 검증, 권한정보획득, 
```
