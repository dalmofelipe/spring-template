<h2 align="center">Spring Template</h2>

<p align="center">Repositório de referência para projetos em Spring Boot!</p>

---

### Running DEV

É iniciado na porta ```8000``` com ```SQLite```. Não será necessário variáveis de ambiente.

```sh
$ spring-template> ./gradlew clean bootRun

# Para rodar os tests not terminal
$ spring-template> ./gradlew test
```

---

### Running TEST

É iniciado na porta ```8090``` com ```MySQL```. Configurar variáveis de ambiente em ```./envs/test.env.sh``` no LINUX ou ```./envs/test.env.ps1``` para o WINDOWS. 

```sh
$ spring-template> .\envs\test.env.sh

$ spring-template> ./gradlew clean bootRun
```

Por padrão, o banco de dados no ambiente de **TEST** é totalmente controlado pela **Spring Data JPA**. Os arquivos de configuração do DB deste ambiente, estão desabilitados no ```application.properties```, confira as flags ```spring.flyway.enabled=false``` e ```spring.jpa.hibernate.ddl-auto=update```.

---

### Running PROD

É iniciado na porta ```8080``` com ```PostgreSQL```. Configurar variáveis de ambiente em ```./envs/prod.env.sh``` no LINUX ou ```./envs/prod.env.ps1``` para o WINDOWS. 

```sh
$ spring-template> .\envs\prod.env.sh

$ spring-template> ./gradlew clean bootRun
```

Para executar os testes em ambientes de ```TEST``` E ```PROD```, será necessário informar as variáveis de ambiente para as rotinas de testes.

---

<br>

### Todo

- [x] Requests - Body - Query - Param
- [x] Domain - Dto / Records - Enums - Entidades / Models
- [x] Databases - SQlite - PostgreSQL - MySQL
- [x] Environments - applications.properties - dev - prod - test
- [x] schema.sql - data.sql - Flyway Migrations
- [x] .env / sh - env settings
- [x] docker-compose para bases de dados
- [x] Tratamento de erros e validações
- [x] Documentação com Swagger Spring Boot 3 + Swagger + OpenAPI 3 SpringDoc
- [ ] Requisições Externas - Webclient - FeingClient - RestTemplate - JsonNode
- [x] Teste JUnit RestTemplate JsonNode
- [ ] Teste Mockito
- [ ] MongoDB
- [ ] GraphQL

<br>

### Tofix

- [ ] Corrigir migrations do postgres para o tipo enum USER_ROLE
