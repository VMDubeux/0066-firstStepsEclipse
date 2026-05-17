# 🌲 My Java Automation Project

Este projeto utiliza **Java** e **JUnit** para automatizar os testes das funcionalidades de **Login** e **Informações Pessoais do Usuário** (User Personal Info). A automação foi desenvolvida e estruturada para ser executada diretamente pelo **Eclipse IDE**, permitindo a validação ágil dos fluxos de comportamento da aplicação.

---

## 🛠️ Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:
* **Java Development Kit (JDK)** (Versão 11 ou superior recomendada)
* **Eclipse IDE** (Eclipse IDE for Java Developers)
* **Mvn** (Caso o projeto utilize Maven) ou as dependências do JUnit integradas ao Build Path.

> [!IMPORTANT]  
> É necessário que as variáveis de ambiente `JAVA_HOME` e o `PATH` do sistema estejam configurados corretamente para o funcionamento do compilador Java.

---

## 🚀 Configuração no Eclipse

Siga os passos abaixo para importar e rodar o projeto no seu ambiente de desenvolvimento:

1. Abra o **Eclipse IDE**.
2. Vá em `File` > `Import...`
3. Escolha `Existing Projects into Workspace` (ou `Existing Maven Projects`, caso aplique) e clique em **Next**.
4. Selecione o diretório raiz deste projeto e clique em **Finish**.
5. Certifique-se de que a biblioteca do **JUnit 5** (ou JUnit 4) está adicionada ao Build Path do projeto.

---

## 🧪 Executando os Testes

Para rodar os testes automatizados de Login e Informações Pessoais:

* No painel *Package Explorer*, navegue até a classe de teste desejada.
* Clique com o botão direito sobre o arquivo de teste (ou sobre a pasta de testes para rodar todos).
* Selecione **Run As** > **JUnit Test**.
* Acompanhe os resultados detalhados (passou/falhou) na aba nativa do **JUnit** no Eclipse.