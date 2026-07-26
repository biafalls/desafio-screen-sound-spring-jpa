# 🎵 Screen Sound Spring JPA

Aplicação desenvolvida como desafio prático do curso **Java: Persistência de Dados e Consultas com Spring Data JPA**.

O projeto consiste em um sistema de gerenciamento musical executado via **Command Line Interface (CLI)**, permitindo cadastrar artistas, álbuns e músicas, realizar consultas utilizando Spring Data JPA e persistir os dados em um banco PostgreSQL.

---

# ✨ Funcionalidades

- 🎤 Cadastro de artistas
- 💿 Cadastro de álbuns
- 🎵 Cadastro de músicas
- 🔗 Associação entre artistas, álbuns e músicas
- 🔍 Pesquisas utilizando Derived Queries
- 📄 Consultas com JPQL
- 🗃️ Consultas utilizando SQL Nativo
- 🤖 Integração com a API do ChatGPT
- 💾 Persistência de dados com PostgreSQL

---

# 🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Jackson
- API ChatGPT
- IntelliJ IDEA

---

# 🗄️ Modelagem da Aplicação

O projeto foi desenvolvido utilizando três entidades principais.

```text
Artista
   │              
   ▼              
 Album            
   │              
   ▼              
 Música 
```

### Relacionamentos

- Um artista pode possuir vários álbuns;
- Um álbum pertence a um único artista;
- Um álbum pode possuir diversas músicas;
- Cada música pertence a um único álbum.

---

# 📂 Estrutura do Projeto

```text
src
└── main
    ├── java
    │   ├── models
    │   ├── repository
    │   ├── service
    │   ├── principal
    │   └── ScreenSoundApplication
    │
    └── resources
```

---

# 🚀 Como Executar

Clone o repositório:

```bash
git clone https://github.com/biafalls/desafio-screen-sound-spring-jpa.git
```

Acesse a pasta do projeto:

```bash
cd desafio-screen-sound-spring-jpa
```

Configure o PostgreSQL no arquivo `application.properties` (ou utilizando variáveis de ambiente) e execute a aplicação pela sua IDE ou através do Maven.

---

# 📚 Conceitos Aplicados

Durante o desenvolvimento deste desafio foram utilizados:

### Persistência de Dados

- Spring Data JPA
- Hibernate
- PostgreSQL
- ORM

### Mapeamento

- Entidades
- Relacionamentos
- Enum
- Cascade
- Fetch Types

### Consultas

- JpaRepository
- Derived Queries
- JPQL
- SQL Nativo

### Java

- Streams API
- Optional
- Records
- Lambdas
- Method References

### Integração

- Consumo de API REST
- Desserialização de JSON
- Persistência das respostas da API
  
---

# 📖 Material de Apoio

Este projeto foi desenvolvido como prática dos conteúdos estudados durante a formação.

Caso queira consultar explicações detalhadas sobre os conceitos utilizados neste desafio, acesse o repositório abaixo:

### 📚 Exercícios Spring Data JPA

➡️ **https://github.com/biafalls/exercicios-spring-data-jpa**

Lá você encontrará uma documentação completa sobre:

- Persistência de Dados
- JDBC, JPA e Hibernate
- ORM
- PostgreSQL
- Mapeamento de Entidades
- Relacionamentos
- Injeção de Dependência
- Repositories
- Derived Queries
- JPQL
- SQL Nativo
- Enum
- Integração com APIs

---
[![GitHub](https://img.shields.io/badge/GitHub-biafalls-181717?style=for-the-badge&logo=github)](https://github.com/biafalls)
