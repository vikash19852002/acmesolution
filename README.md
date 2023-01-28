# AcmeSolution 

##Read Me First
The following was discovered as part of building this project:

- Build with Java 17, Spring Boot, JPA, H2 DB
- Build pom.xml to download all necessary libraries and packages.
- All test case would be run when you perform "mvn clean install"

##Coverage
- It covers get balance of an Account and Transfer balance actions from one account to another.
- It contains basic validation to get Balance and Transfer Balance.
- Concurrent transactions with same account are handled with help of Optimistic Locking concept (Version field in DB)
- It initializes the H2 Database and load initial data at first run.
- H2 DB is used to persist the Database on the Disk, so when the application is restarted, it reads old values.
- It covers the basic functionalities and can be enhanced in the future.

##API Methods

###Get Balance method
- URL: http://localhost:8080/acme/api/getBalance/88888888
- Method: GET

###Transfer Balance method
- URL: http://localhost:8080/acme/api/transfer
- Method: POST
- Body: {"fromAccount" : "88888888", "toAccount" : "12345678", "amount": 10, "currency" : "HKD"}
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

