# Sistema de Gerenciamento de Eventos

Este é um projeto de exemplo para um sistema de gerenciamento de eventos usando Maven, Spring Boot e MySQL.

Obs: para atribuir um admin apenas manualmente.
## Funcionalidades

### Cadastro de Usuários

- **Cadastro de Usuário:**
  - Método: `POST`
  - Endpoint: `/api/users`
  - Descrição: Cadastra um novo usuário no sistema.

- **Listagem de Usuários:**
  - Método: `GET`
  - Endpoint: `/api/users`
  - Descrição: Obtém a lista de todos os usuários.
  
- **Detalhes do Usuário:**
  - Método: `GET`
  - Endpoint: `/api/users/{id}`
  - Descrição: Obtém detalhes específicos de um usuário pelo ID.

- **Atualização de Usuário:**
  - Método: `PUT`
  - Endpoint: `/api/users/{id}`
  - Descrição: Atualiza as informações de um usuário existente pelo ID.

- **Exclusão de Usuário:**
  - Método: `DELETE`
  - Endpoint: `/api/users/{id}`
  - Descrição: Exclui um usuário do sistema pelo ID.

### Cadastro de Eventos

- **Cadastro de Evento:**
  - Método: `POST`
  - Endpoint: `/api/events`
  - Descrição: Cadastra um novo evento no sistema.

- **Listagem de Eventos:**
  - Método: `GET`
  - Endpoint: `/api/events`
  - Descrição: Obtém a lista de todos os eventos.

- **Detalhes do Evento:**
  - Método: `GET`
  - Endpoint: `/api/events/{id}`
  - Descrição: Obtém detalhes específicos de um evento pelo ID.

- **Atualização de Evento:**
  - Método: `PUT`
  - Endpoint: `/api/events/{id}`
  - Descrição: Atualiza as informações de um evento existente pelo ID.

- **Exclusão de Evento:**
  - Método: `DELETE`
  - Endpoint: `/api/events/{id}`
  - Descrição: Exclui um evento do sistema pelo ID.

### Inscrições em Eventos

- **Inscrição em Evento:**
  - Método: `POST`
  - Endpoint: `/api/events/{id}/register`
  - Descrição: Permite que um usuário se inscreva em um evento específico.

- **Listagem de Inscrições do Usuário:**
  - Método: `GET`
  - Endpoint: `/api/users/{userId}/registrations`
  - Descrição: Obtém a lista de eventos em que um usuário está inscrito.

- **Cancelamento de Inscrição:**
  - Método: `DELETE`
  - Endpoint: `/api/users/{userId}/registrations/{eventId}`
  - Descrição: Permite que um usuário cancele sua inscrição em um evento específico.

## Pré-requisitos

- Java 8 ou superior
- Maven
- MySQL
