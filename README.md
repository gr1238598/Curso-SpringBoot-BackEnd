# 📦 Sistema de Pedidos – API REST com Spring Boot

## 📌 Visão Geral
Este projeto implementa um **sistema de pedidos** completo, com base no modelo conceitual abaixo, utilizando **Java com Spring Boot** no back-end e **MySQL** como banco de dados relacional.

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot**
  - Spring Web (API REST)
  - Spring Data JPA / Hibernate (ORM)
  - Spring Security (autenticação/autorização com JWT)
  - Bean Validation (validação de dados)
- **Banco de Dados MySQL**
- **Envio de e-mails** com **SMTP Google**
- **Maven** para gerenciamento de dependências

## ⚙️ Funcionalidades Técnicas
- Criação de uma **API REST** seguindo boas práticas do protocolo HTTP.  
- **Mapeamento objeto-relacional** com JPA/Hibernate baseado no modelo conceitual.  
- **Autenticação e autorização** via tokens JWT.  
- **Validação de dados** com Bean Validation.  
- **Tratamento centralizado de exceções**.  
- Configuração de **CORS** para integração com aplicações front-end.  
- **Envio de e-mails automáticos** (notificação de pedidos).  

## 📐 Modelo Conceitual
O sistema contempla as entidades principais:

- **Cliente** com endereços, telefones e pedidos.  
- **Produto** associado a **categorias**.  
- **Pedido** com **itens** relacionados a produtos.  
- **Pagamento** (abstrato) com herança para:
  - Pagamento com boleto  
  - Pagamento com cartão  
- Enumerações para:
  - **TipoCliente** (Pessoa Física / Jurídica)  
  - **EstadoPagamento** (Pendente, Quitado, Cancelado)  
- Estrutura geográfica com **Estado** e **Cidade**.  


