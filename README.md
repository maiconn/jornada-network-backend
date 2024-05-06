# Projeto Jornada Rede Social Para Vagas e Cursos - Backend

## Ferramentas Utilizadas: 
* Java 21
* Spring 3.2.5
* PostgreSQL v.16.1 (docker)
* Demais bibliotecas descritas no `build.gradle`
* Docker
* DBeaver

## Setup Banco de Dados
* Baixar docker e criar um container do postgres com o comando: `docker run --name pg-jornada -e POSTGRES_PASSWORD=<SUASENHA> -d postgres`
* Criar uma database chamada `jornada`
* Abra o DBeaver com o usuario `postgres` e a senha escolhida e faça a restauração do banco de dados em `./docs/base/v1.sql`

## Setup Java
* Clonar o projeto
* Baixar intellij community ou ultimate
* Abrir o projeto
* Criar um arquivo `application-local.properties` no caminho `src/main/resources/` (cópia do arquivo `application-example.properties`) para suas configurações locais
* Alterar as variáveis com as suas configurações locais
* Configurar variáveis de ambiente `-Dspring.profiles.active=local -DSENHA_PG=<SUA_SENHA> -Djwt.secret=<SECRET>` trocando sua senha do banco de dados e a senha de autenticação do servidor JWT.


## Usuários

### ADMIN: admin / admin
### USER: user / user