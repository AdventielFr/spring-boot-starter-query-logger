# spring-boot-starter-query-logger

Starter for spring-boot allows to log the number of requests made to the database when calling an endpoint of an API.

```
Call to: CustomerController.findAllFilteredByName(..) with arguments: [Gaillard].
Number of queries executed: 75
Time spent: 23 ms [ ]  
```

## Usage

Add the dependency to your _pom.xml_

```xml
<dependency>
    <groupId>io.github.adventielfr</groupId>
    <artifactId>spring-boot-starter-query-logger</artifactId>
    <version>2.0.0</version>
</dependency>
```

Enable it with the following property in your _application.properties_ file:

```properties
adventiel.hibernatequerylogger.enabled=true
```

## Compatibility

For now, the library is only compatible with spring 2.x, due to some breaking changes in Hibernate 6.
A version for Spring 3.x is in progress.
