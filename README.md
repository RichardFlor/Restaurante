<h1 align="center">
  Gerenciadmento de Restaurante
</h1>


API desenvolvida durante estudos.


## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Flyway](https://www.baeldung.com/database-migrations-with-flyway)
- [Swagger](https://swagger.io)

## Práticas adotadas

- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- DB migrations
- SOLID
- POO


## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/restaurante-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080/api/v1/swagger-ui/index.html#).


<!-- ## API Endpoints


- Criar Usuario 
```
$ http POST :8080/v1/users

{
    "username": "Teste",
    "email": "teste@gmail.com",
    "password": "123"
}

```

- Listar Usuario
```
$ http GET :8080/v1/users


 {
      "userId": "eed48960-3b33-43a0-9973-c6f2379a744a",
      "username": "Teste",
      "email": "teste@gmail.com",
      "password": "123",
      "creationTimestamp": "2025-02-12T16:11:41.145879Z",
      "updateTimestamp": "2025-02-12T16:11:41.145879Z"
  }

```
- Listar Usuario por Id
```
$ http GET :8080/v1/users/eed48960-3b33-43a0-9973-c6f2379a744a


{
    "userId": "eed48960-3b33-43a0-9973-c6f2379a744a",
    "username": "Teste",
    "email": "teste@gmail.com",
    "password": "123",
    "creationTimestamp": "2025-02-12T16:11:41.145879Z",
    "updateTimestamp": "2025-02-12T16:11:41.145879Z"
}

```


- Atualizar Usuario
```
$ http PUT :8080/v1/users/eed48960-3b33-43a0-9973-c6f2379a744a


  {

      "username": "Teste 2 ",
      "email": "teste2@gmail.com",
      "password": "456"
  }

```

- Remover Usuario
```
http DELETE :8080/v1/users/eed48960-3b33-43a0-9973-c6f2379a744a

```
 -->
