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