# 🍽️ Caderno de Receitas Digital

Sistema desenvolvido em **Java**, utilizando **JDBC** e **MySQL**, que permite o gerenciamento completo de receitas culinárias por meio de um CRUD (Create, Read, Update e Delete).

O projeto foi desenvolvido como atividade da formação **Jovem Programador**, aplicando conceitos de programação orientada a objetos, banco de dados relacional e persistência de dados.

---

## 📖 Sobre o Projeto

O **Caderno de Receitas Digital** permite que cada usuário realize seu cadastro, faça login e gerencie suas próprias receitas.

Cada receita pode conter:

- 📝 Título
- ⏱ Tempo de preparo
- 🍽 Número de porções
- 📂 Categoria
- ⭐ Nível de dificuldade
- 🥕 Ingredientes
- 🔪 Utensílios
- 👨‍🍳 Modo de preparo
- 📅 Data de criação
- 👤 Autor da receita

---

# ✨ Funcionalidades

## 👤 Usuários

- Cadastro de usuários
- Login com autenticação
- Validação de usuário existente
- Associação das receitas ao usuário

---

## 📖 Receitas

- ➕ Cadastrar receitas
- 🔍 Pesquisar receitas
- ✏️ Editar receitas
- 🗑 Excluir receitas

---

## 🥕 Ingredientes

Durante o cadastro da receita é possível:

- Adicionar vários ingredientes
- Definir quantidade
- Definir unidade de medida
- Reaproveitar ingredientes já cadastrados

Também é possível:

- Alterar ingredientes
- Adicionar novos ingredientes
- Remover ingredientes

---

## 🔪 Utensílios

Cada receita pode possuir diversos utensílios.

Também é possível:

- Adicionar
- Alterar
- Remover

---

# 🛠 Tecnologias Utilizadas

- Java
- JDBC
- MySQL
- SQL
- IntelliJ IDEA (compatível com Eclipse)

---

# 🗃 Banco de Dados

Banco utilizado:

```sql
caderno_receita
```

### Principais tabelas

- usuario
- receita
- preparo
- ingredientes
- ingrediente_receita
- ferramentas
- ferramenta_receita
- categoria
- complexidade

---

# 📂 Estrutura do Projeto

```
📦 Caderno de Receitas
 ├── Aula17.java
 ├── Banco de Dados
 ├── README.md
 └── mysql-connector-j
```

---

# 🚀 Como Executar

## 1️⃣ Clone o repositório

```bash
git clone https://github.com/SEU_USUARIO/caderno-receitas.git
```

---

## 2️⃣ Abra o projeto

Abra o projeto em sua IDE de preferência.

Exemplos:

- IntelliJ IDEA
- Eclipse
- NetBeans

---

## 3️⃣ Crie o banco de dados

Crie um banco chamado:

```sql
CREATE DATABASE caderno_receita;
```

Depois execute o script SQL contendo todas as tabelas do projeto.

---

## 4️⃣ Configure a conexão

No código altere as informações caso necessário:

```java
String url = "jdbc:mysql://127.0.0.1:3306/caderno_receita";
String user = "root";
String password = "";
```

---

## 5️⃣ Adicione o Driver JDBC

Adicione ao projeto o MySQL Connector/J.

---

## 6️⃣ Execute

Execute a classe principal:

```text
Aula17.java
```

---

# 📋 Menu Principal

```text
==== Caderno Receitas da Vó Nadir ====

1 - Adicionar Receita
2 - Pesquisar Receitas
3 - Atualizar Receita
4 - Deletar Receita
0 - Sair
```

---

# 💾 Funcionalidades do CRUD

| Operação | Descrição |
|----------|-----------|
| Create | Cadastro de receitas |
| Read | Pesquisa de receitas |
| Update | Atualização das informações |
| Delete | Exclusão de receitas |

---

# 📚 Conceitos Aplicados

- Programação Orientada a Objetos (POO)
- JDBC
- SQL
- Banco de Dados Relacional
- CRUD
- PreparedStatement
- ResultSet
- Chaves Primárias
- Chaves Estrangeiras
- Relacionamentos N:N
- Validação de entrada de dados

---

# 🎯 Objetivo

Desenvolver um sistema completo de gerenciamento de receitas utilizando Java e MySQL, colocando em prática os conhecimentos adquiridos durante a formação **Jovem Programador**.

---

# 👨‍💻 Autor

**Jhan Serafim, Rafael Warmling e Thawani Votri**

Projeto desenvolvido para fins acadêmicos durante a formação **Jovem Programador**.

---

## ⭐ Caso tenha gostado do projeto

Se este projeto foi útil para você, deixe uma ⭐ no repositório!
