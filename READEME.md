# Welcome SpringBoot Back-End for React Front

## How to Create (From Nothing)
0. Download IntelliJ
1. visit spring Initializer  
https://start.spring.io/  
```
1) Gradle-Groovy
2) java
3) Spring Boot 2.7.8
(We use java 11, if you choose Spring Boot 3.*.*, you have to version up jdk
4) Dependencies
: Spring Boot DevTools
: Spring Web
: Lombok
: Spring Data JPA
: Spring Data JDBC
: MySQL Driver
: MyBatis Framework
: Spring Security
: OAuth2 Resource Server
: Spring Configuration Processor
: Spinrg Boot Actuator
: Spring Batch
```
2. commit test
3. controller.api >
4. service.app >

## Project Directory
```
📦react
 ┣ 📜.gitignore
 ┣ 📜README.md
 ┣ 📜build.gradle                   ### Dependency Injection ###
 ┣ 📜gradlew
 ┣ 📜gradlew.bat
 ┗ 📜settings.gradle
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┗ 📜application.yaml
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂portal
 ┃ ┃ ┃ ┃ ┃ ┗ 📂react
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AppDbConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NamingStrategy.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜QueryDslConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂api
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exceptions
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlreadyExistException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂persistence
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂key
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuPk.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenu.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SystemMenuRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SystemMenuRepositoryCustom.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AppQuerydslRepositorySupport.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ItemsModifiedResponseVO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂temp
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomAuthenticationProvider.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SystemMenuService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DateUtils.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JsonResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReactApplication.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ServletInitializer.java
 ┃ ┣ 📂querydsl
 ┃ ┃ ┗ 📂java
 ┃ ┃ ┣ 📂generated
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂portal
 ┃ ┃ ┃ ┃ ┃ ┗ 📂react
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂persistence
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜QSystemMenu.java
 ┃ ┗ 📂test
 ┃ ┃ ┗ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂portal
 ┃ ┃ ┃ ┃ ┃ ┗ 📂react
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReactApplicationTests.java

```


## Project Directory - Including Comment
```
📦react
 ┣ 📜.gitignore
 ┣ 📜README.md
 ┣ 📜build.gradle                                       ### (1) Dependency Injection ###
# build.gradle 파일 분석
https://galid1.tistory.com/196
# gradle이 동작하는 순서
https://kotlinworld.com/318
# querydsl 설정 및 내용
https://may9noy.tistory.com/854
 ┣ 📜gradlew
 ┣ 📜gradlew.bat
 ┗ 📜settings.gradle
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┗ 📜application.yaml                             ### (2) Spring Boot Settings ###
# https://jeong-pro.tistory.com/159
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂portal
 ┃ ┃ ┃ ┃ ┃ ┗ 📂react
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂persistence
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂key
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuPk.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenu.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SystemMenuRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SystemMenuRepositoryCustom.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AppQuerydslRepositorySupport.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂api
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SystemMenuService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SystemMenuServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AppDbConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NamingStrategy.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜QueryDslConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exceptions
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlreadyExistException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ItemsModifiedResponseVO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂temp
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomAuthenticationProvider.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DateUtils.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JsonResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReactApplication.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ServletInitializer.java
 ┃ ┣ 📂querydsl
 ┃ ┃ ┗ 📂java
 ┃ ┃ ┣ 📂generated
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂portal
 ┃ ┃ ┃ ┃ ┃ ┗ 📂react
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂persistence
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂system
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜QSystemMenu.java
 ┃ ┗ 📂test
 ┃ ┃ ┗ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂portal
 ┃ ┃ ┃ ┃ ┃ ┗ 📂react
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReactApplicationTests.java

```

## 궁금한 점
1. h2 setting... 정리하자
2. Service (Interface), ServiceImpl (implements)
3. controller
```
3.1 RestController
3.2 RequestMapping
3.3 Controller찾아가는 방법

```