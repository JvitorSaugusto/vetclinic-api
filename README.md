# VetClinic API 🐾

> API RESTful desenvolvida em Java com Spring Boot para o gerenciamento de uma clínica veterinária, permitindo o cadastro de tutores, pets e autenticação de usuários com JWT.

## ✨ Funcionalidades

-   🔐 **Autenticação Segura:** Sistema de registro e login de usuários com autenticação via JSON Web Tokens (JWT).
-   👨‍👩‍👧‍👦 **Gerenciamento de Tutores:** Operações CRUD (Create, Read, Update, Delete) completas para os tutores dos pets.
-   🐶 **Gerenciamento de Pets:** Operações CRUD completas para os pets, com vínculo direto aos seus tutores.
-   ✅ **Validação:** Validação de dados de entrada para garantir a integridade das informações.
-   🛡️ **Tratamento de Exceções:** Handler global para padronizar as respostas de erro da API.

## 🧪 Testes

O projeto possui uma cobertura de testes para garantir a estabilidade e o correto funcionamento da API.

-   **Testes de Unidade:** Utilizados para testar as camadas de serviço (`Service`) de forma isolada, com o auxílio do Mockito para simular o comportamento das dependências.
-   **Testes de Integração:** Utilizados para testar a camada de persistência com o banco de dados (`@DataJpaTest`) e os endpoints da API (`@SpringBootTest`), garantindo que todos os componentes funcionam corretamente em conjunto.


## 🛠️ Tecnologias Utilizadas

-   **Java 17**
-   **Spring Boot 3**
    -   Spring Web
    -   Spring Security
    -   Spring Data JPA
    -   Validation
-   **Maven** (Gerenciador de dependências)
-   **MySQL** (Banco de dados de desenvolvimento)
-   **H2 Database** (Banco de dados para testes)
-   **Docker** e **Docker Compose** (Para o ambiente de desenvolvimento)
-   **JWT (JSON Web Tokens)** (Para autenticação)
-   **JUnit 5** e **Mockito** (Para testes unitários e de integração)

## 🚀 Como Rodar o Projeto Localmente

Siga os passos abaixo para executar a API em seu ambiente local.

**Pré-requisitos:**
-   Java 17 (ou superior)
-   Maven 3.8 (ou superior)
-   Docker e Docker Compose

**Passo a passo:**

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/vetclinic-api.git](https://github.com/seu-usuario/vetclinic-api.git)
    cd vetclinic-api
    ```

2.  **Inicie o banco de dados com Docker:**
    O `docker-compose.yml` na raiz do projeto irá configurar e iniciar um container MySQL.
    ```bash
    docker-compose up -d
    ```

3.  **Execute a aplicação Spring Boot:**
    Use o Maven para iniciar a aplicação.
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Pronto!** A API estará disponível em `http://localhost:8080`.

## 📋 Endpoints da API

Abaixo estão os principais endpoints disponíveis.

#### Autenticação (`/auth`)
-   `POST /auth/register`: Registra um novo usuário.
-   `POST /auth/login`: Autentica um usuário e retorna um token JWT.

#### Tutores (`/tutor`)
-   `POST /tutor/register`: Cadastra um novo tutor.
-   `GET /tutor`: Lista todos os tutores.
-   `GET /tutor/{id}`: Detalha um tutor específico.
-   `GET /tutor/{id}/pets`: Lista todos os pets de um tutor.
-   `PUT /tutor/update/{id}`: Atualiza os dados de um tutor.
-   `DELETE /tutor/delete/{id}`: Remove um tutor.

#### Pets (`/pet`)
-   `POST /pet/register`: Cadastra um novo pet.
-   `GET /pet`: Lista todos os pets.
-   `GET /pet/detail/{id}`: Detalha um pet específico.
-   `PUT /pet/update/{id}`: Atualiza os dados de um pet.
-   `DELETE /pet/delete/{id}`: Remove um pet.
