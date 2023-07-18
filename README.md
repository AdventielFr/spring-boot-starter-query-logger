# spring-boot-starter-query-logger

Starter pour spring-boot permettant de logger le nomre de requêtes effectuées sur la base de données lors de l'appel d'un endpoint d'une API.

```
2023-07-12 21:44:48,451 INFO [http-nio-8080-exec-1] f.a.s.s.HibernateQueryLogger [HibernateQueryLogger.java:41] Number of queries executed: 737 [ ] 
2023-07-12 21:44:48,451 INFO [http-nio-8080-exec-1] f.a.s.s.HibernateQueryLogger [HibernateQueryLogger.java:42] Time spent: 369 ms [ ] 
```

## Utilisation

Ajouter la dépendance à votre _pom.xml_

```xml
<dependency>
    <groupId>fr.adventiel.spring-boot</groupId>
    <artifactId>spring-boot-starter-query-logger</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
