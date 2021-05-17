# DivulgaTudo
A agência Divulga Tudo precisa de um programa para gerenciar os seus anúncios online. O objetivo dos anúncios faz parte de uma campanha nas redes sociais. O sistema de gerenciamento permitirá a gestão do anúncio e o rastreio dos resultados da campanha.

CADASTRO DE ANÚNCIOS

O sistema foi criado em linguagem Java, utilizando Spring Framework, Hibernate e o H2 Database para realizar a persistência dos dados. Antes de executar o programa será necessário mudar o caminho de onde o arquivo da persistência dos dados irá ser salvo: 
- Arquivo: anuncio> src > main > resources > application.propreties
- Propriedade: spring.datasource.url

Para executar o programa, é necessário abrir o projeto em uma IDE, durante o desenvolvimento do projeto foi utilizada a IntelliJ. Após importar o projeto basta executá-lo.

A API REST criada possui 6 endpoints:

•	lista - GET(http://localhost:8080/anuncios): Lista todos os anúncios cadastrados, se adicionado o parâmetro “cliente”, com o nome de algum cliente que possua anúncio(s) cadastrado(s), o sistema lista apenas os anúncios desse cliente.

•	cadastrar - POST(http://localhost:8080/anuncios): Cadastra um novo anúncio no sistema. É possível teste esse endpoint através do Postman (https://www.postman.com/), por exemplo, onde você consegue enviar um arquivo JSON que represente um anúncio.

•	valorTotalInvestido - GET (http://localhost:8080/anuncios/total/investido/{id} / http://localhost:8080/anuncios/total/investido/{id}/{periodo}): Retorna o valor total investido em um anúncio. O parâmetro "id" é o identificador do anúncio e o parâmetro "periodo", é utilizado como filtro, que representa uma quantidade de dias dentro da quantidade total de dias do anúncio.

•	qtdMaxVisualizacoes - GET (http://localhost:8080/anuncios/visualizacoes/{id} / http://localhost:8080/anuncios/visualizacoes/{id}/{periodo}): Retorna a quantidade máxima de visualizações de um anúncio. O parâmetro "id" é o identificador do anúncio e o parâmetro "periodo", é utilizado como filtro, que representa uma quantidade de dias dentro da quantidade total de dias do anúncio.

•	qtdMaxCliques - GET (http://localhost:8080/anuncios/cliques/{id} / http://localhost:8080/anuncios/cliques/{id}/{periodo}): Retorna a quantidade máxima de cliques de um anúncio. O parâmetro "id" é o identificador do anúncio e o parâmetro "periodo", é utilizado como filtro, que representa uma quantidade de dias dentro da quantidade total de dias do anúncio.

•	qtdMaxCompartilhamentos - GET (http://localhost:8080/anuncios/compartilhamentos/{id} / http://localhost:8080/anuncios/compartilhamentos/{id}/{periodo}): Retorna a quantidade máxima de compartilhamentos de um anúncio. O parâmetro "id" é o identificador do anúncio e o parâmetro "periodo", é utilizado como filtro, que representa uma quantidade de dias dentro da quantidade total de dias do anúncio.
