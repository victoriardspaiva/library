# Projeto Library
![Static Badge](https://img.shields.io/badge/Spring%20boot-brightgreen?logo=spring%20boot&label=3.1.0&labelColor=gray&color=green&link=https%3A%2F%2Fspring.io%2Fprojects%2Fspring-boot)
![Static Badge](https://img.shields.io/badge/Java-black?logo=Java&label=20&labelColor=gray&color=orange&link=https%3A%2F%2Fwww.oracle.com%2Fjava%2Ftechnologies%2Fjavase%2Fjdk20-archive-downloads.html)
![Static Badge](https://img.shields.io/badge/MySQL-black?logo=MySQL&label=database&labelColor=white&color=blue&link=https%3A%2F%2Fwww.mysql.com%2F)
![Static Badge](https://img.shields.io/badge/maven-black?logo=Java&label=4.0.0&labelColor=gray&color=orange)





http://localhost:8080/swagger-ui/index.html






### 📑 Índice
---

- [Problemática](#-problemática)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Orientações](#-orientações)
   - [Pré-requisitos](#-pré-requisitos)
   - [Rodando o Back End (servidor)](#-rodando-o-back-end-servidor)
   - [Rotas, links e métodos](#--rotas-links-e-métodos)
- [Tratamento de erro](#-orientações)
- [Objetivos na implementação](#objetivos-na-implementação)


### ❓ Problemática
Para o gerenciamento e catatolagação de livros de sua biblioteca pessoal foi realizado projeto de uma API RESTful para por em prática estudo voltado para desenvolvimento backend, usando ecossistema Spring Framework.

### 💻 Tecnologias
---
As seguintes ferramentas foram usadas na construção do projeto:

- Sring Boot;
- Spring Data JPA;
- Swagger Open API



### 🧱 Arquitetura
---
```
├───📂src/
│   ├───📂controllers/
│   │   ├───placesController.js
│   │   └───userController.js
│   ├───📂database/
│   │   └───configMongo.js
│   ├───📂helpers/
│   │   └───auth.js
│   ├───📂middlewares/
│   │   └───auth.js
│   ├───📂models/
│   │   ├───placesSchema.js
│   │   └───userSchema.js
│   ├───📂routes/
│   │   ├───placesRouter.js
│   │   └───userRouter.js
│   ├───app.js 
│   └───index.js
├───.env
├───.env.example
├───.gitignore
├───package-lock.json
├───package.json
├───Procfile
├───README.md
└───server.js
```


### 📌 Orientações
---
### 📎 Pré-requisitos:

Antes de começar, você vai precisar ter instalado em sua máquina apenas as seguintes ferramentas:
* [Git](https://git-scm.com);
* [Docker](https://www.docker.com/);
* Além disto é bom ter um editor para trabalhar com o código como [VSCode](https://www.jetbrains.com/idea/).

#### 🎲 Rodando o Back End (servidor)

```bash
# Clone este repositório
$ git clone <https://github.com/victoriardspaiva/library>

# Acesse a pasta do projeto no terminal/cmd
$ cd library

# Instale as dependências
$ sudo docker-compose -f docker-compose.yml up -d

# Levante o servidor, e o Tomcat inciará na porta:8080 - acesse <http://localhost:8080> 
```
### 🚀  Rotas, links e métodos
---
A ferramenta de suporte de criação das requisição usada foi o [Postman](https://www.postman.com/), você pode usar a de sua preferência.

#### Variáveis de ambientes: 
É possivel criar variáveis que sejam visiveis para todo o escopo do projeto, existem alguns beneficios um dele é não deixar o valor exposto, além de poder ser reutilizado sempre que necessário.

Para criar a variavel é necessário na collection > aba Variables preencha na coluna variable da tabela o nome da variavel `URL` e em initial value e current value colocar o valor `http://localhost:9090`

> Na barra de URL `endpoint`, vamos preencher o nome da variavel entre dois cochetes `{{URL}}`.


## Recursos:

![assets/recurso.jpg](assets/recurso.jpg)

### Tratamento de erro
- [x] Pesquisa não encontrada

### Objetivos na implementação
- [x] Nenhuma query nativa
- [x] Documentação via Swagger
- [x] Cobertura de testes acima de 80%
- [x] Retornos usando de paginação
- [x] Exception Pattern
- [ ] 








