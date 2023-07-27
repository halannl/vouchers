# Vouchers
API Vouchers - Prova técnica

## Arquitetura

- Java 17
- Spring Boot 3.1.2
- Spring Data - MongoDB
- Spring Security
- Lombok 1.18.28
- Log4j (slf4j)
- Maven 3.9.3
- Swagger 3

## Instruções

A instalação dos módulos é feita via maven pelo seguinte comando na raiz do projeto:

- mvn clean install

Após instalação bem sucedida dos 3 módulos, acesse o diretório do projeto web "cd vouchers-web"
e execute o seguinte comando para iniciar a aplicação:

- mvn spring-boot:run

A aplicação se iniciará localmente na porta 8080, acesse a api pelo Swagger pelo seguinte endereço:

- http://localhost:8080/documentation

Os dados de acesso são "admin" para usuário e "admin" para senha, conforme configurado na classe 
SecurityConfiguration

Observação: Assume-se que existe uma instância de MongoDB rodando localmente

## Uso

A interface de usuário do Swagger fornece instruções para uso dos endpoints disponíveis. <br>
Basicamente, usuários e ofertas especiais devem ser previamente inseridos para que se 
possa inserir vouchers.

Aqui vou fornecer alguns dados de testes (Request body - json) que podem ser usados pra testar os endpoints:

#### /usuarios (PUT)

- {"email": "pedro@email.com", "nome": "Pedro Silva"}
- {"email": "ana@email.com", "nome": "Ana Machado"}

#### /ofertasespeciais (PUT)

- {"codigo": "RENNER40", "descricao": "Desconto de 40% lojas Renner", "descontoPercentual": 40}
- {"codigo": "LOJAMER25", "descricao": "Desconto de 25% lojas Americanas", "descontoPercentual": 25}

#### /vouchers (PUT)

- {"codigoOfertaEspecial": "RENNER40", "emailUsuario": "pedro@email.com", "validade": "2023-08-27T14:49:39.210Z"}
- {"codigoOfertaEspecial": "LOJAMER25", "emailUsuario": "pedro@email.com", "validade": "2023-09-27T14:49:39.210Z"}

