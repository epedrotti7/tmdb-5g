# TMDB 5G

Essa é uma aplicacao onde um usuário pode pesquisar pelo titulo de um filme que será consultado na api do TMDB, e então poderá analisar informações de um determinado filme, marcar ele como já assistido, filme favorito ou que pretende assistir.

## Tecnologias Utilizadas

- **Java 11**: [https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html] Java
- **Spring Boot**: [https://spring.io/projects/spring-framework] Framework para criação de API robusta em Java
- **Hibernate**: [https://hibernate.org/] ORM para persistencia de dados em aplicações Java
- **MySQL**: [https://www.mysql.com/] Banco de dados SQL para persistencia
- **Docker**: [https://www.docker.com/] Ferramenta para conteinerização da aplicacão
- **NGinx**: [https://nginx.org/en/] Ferramenta para servir nossos arquivos estáticos do frontend
 
## Além disso estamos realizando requests para a API do The Movie DB

- https://www.themoviedb.org/?language=pt-BR


# Para rodar a aplicação

1. Primeiro, é necessário ter o docker e o docker compose instalados.

2. Crie uma conta na plataforma do TMDB, crie um projeto e gere um bearer token, essa chave será nossa autenticação para realizarmos chamadas a api do tmdb.

3. Com a chave em maos, insira esse valor dentro do nosso arquivo .env que se encontra na raiz do projeto, insira o valor na váriavel:

```bash
BEARER_TOKEN_TMDB=
```

4. Após isso, precisamos rodar 2 comandos para deixar nosso arquivo sh executável, acesse a raiz do projeto e rode o seguinte comando:

```bash
chmod +x api/wait-for-it.sh
```
E então

```bash
chmod +x start.sh                             
```
E então execute o nosso script sh

```bash
./start.sh                             
```
E por fim, basta acessar a plataforma no endereço:

```bash
http://localhost                            
```

E realizar o teste da plataforma.