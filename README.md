# Bergerie Management API

Backend application developed with **Java and Quarkus** as part of the IN510 course at Grenoble INP – Esisar.

The project models and exposes a REST API for managing a sheepfold (*bergerie*), including sheep, bells, enclosures and shepherd dogs.

The main goal is to practice backend application design with persistence, REST services, DTOs, object mapping and API documentation.

## Features

- Sheep management through a REST API
- Retrieve all sheep or a sheep by identifier
- Filter sheep born after a given date
- Create, partially update and delete sheep
- Persistence with Hibernate ORM and Panache
- Data validation with Hibernate Validator
- DTO ↔ Entity mapping with MapStruct
- JSON serialization with Jackson
- API exploration and testing through Swagger UI
- H2 in-memory database for development

Further domain relationships are progressively introduced:
- Sheep ↔ Bell
- Sheep ↔ Enclosure
- Sheep ↔ Shepherd dogs

## Tech Stack

- Java
- Quarkus
- Maven
- Jakarta REST
- Hibernate ORM with Panache
- Hibernate Validator
- H2 Database
- MapStruct
- Jackson
- OpenAPI / Swagger

## Project Structure

```text
src/main/java/fr/esisar/bergerie/
└── mouton/
    ├── Mouton.java
    ├── MoutonResource.java
    ├── MoutonMapper.java
    └── DTOs
