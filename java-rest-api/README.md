# QA Completo - API REST Spring Boot

## Descrição
Este projeto é uma **API REST** desenvolvida com **Spring Boot** para gerenciar itens, suportando **respostas em JSON e XML**. Ele inclui testes unitários que cobrem **regras de negócio**, casos de sucesso e exceções.

---

## Funcionalidades / Endpoints

- **GET /api/items** → Lista todos os itens
- **POST /api/items** → Cria um novo item
- **GET /api/items/{id}** → Busca item por ID
- **GET /api/items/name/{name}** → Busca item por nome
- **GET /api/items/count** → Retorna a quantidade total de itens

---

## Regras de Negócio / Validações

- Não é permitido criar item com **nome vazio** ou **descrição vazia**
- Caso o item não seja encontrado, uma **exceção é lançada**
- IDs dos itens são gerados automaticamente para garantir **unicidade**

---

## Testes Unitários

- Teste criação de item válido
- Teste busca por ID e nome
- Teste contagem de itens
- Teste criação de item inválido (nome/descrição vazia)
- Teste item não encontrado

> Todos os testes são implementados usando **JUnit 5** e **MockMvc**.

---

## Tecnologias

- Java 17
- Spring Boot
- Maven
- JUnit 5

---

## Como Rodar o Projeto

### Pelo Maven:
```bash
mvn spring-boot:run