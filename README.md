# Integration Trust Engine · Governance

Plataforma de governança, observabilidade e gerenciamento de integrações críticas entre sistemas financeiros, desenvolvida com foco em confiabilidade operacional, telemetria em tempo real e segurança de comunicação.

🔗 **Demonstração (Front-end):** https://thamyres127.github.io/integration-trust-engine/  
💻 **Repositório (GitHub):** https://github.com  
⚙️ **API Back-end (Render):** https://onrender.com

---

## 🎯 Objetivo do Projeto

Centralizar o controle de integrações entre sistemas bancários e serviços externos, permitindo acompanhar estados operacionais, latência, volume de eventos e políticas de segurança em uma interface única — servindo tanto para demonstração autônoma em tempo real quanto para gestão prática de rotas pelo usuário.

---

## ✨ Funcionalidades

* **Dashboard Executivo:** Indicadores em tempo real de total de integrações, rotas ativas, itens em revisão, latência média e histórico de eventos.
* **Modo Autônomo (Auto-Pilot):** A interface já inicia populada com rotas bancárias padrão (SPI Pix BACEN, Motor Antifraude, Open Finance) e executa ciclos automáticos de telemetria e logs sincronizados com o horário real, com opção de pausar ou retomar a qualquer momento.
* **Gestão de Integrações (CRUD):** Cadastro de novas integrações, edição de parâmetros/status e exclusão direta com persistência local e integração REST.
* **Certificados mTLS:** Seção dedicada à visualização e governança de chaves públicas, handshake mTLS e validade de certificados (exibição demonstrativa).
* **Monitoramento & Logs:** Console em tempo real com registro de eventos, verificações de saúde e status de *Circuit Breaker*.

---

## 🛠️ Tecnologias Utilizadas

* **Back-end:** Java 21, Spring Boot, Maven, API REST
* **Banco de Dados:** PostgreSQL (hospedado em nuvem via Neon)
* **Front-end:** HTML5, CSS3, JavaScript (Vanilla ES6+)
* **Infraestrutura & Deploy:** Docker, Render (API) e GitHub Pages (Interface Web)

---

## 📂 Estrutura do Projeto

```text
integration-trust-engine/
├── database/          # Scripts SQL e modelagem das tabelas de governança
├── prototype/         # Protótipos iniciais e estudos de interface
├── src/               # Código-fonte da API Back-end (Java 21 + Spring Boot)
├── Dockerfile         # Configuração de container para deploy em nuvem
├── index.html         # Interface Web completa (Dashboard, CRUD e Modo Autônomo)
├── pom.xml            # Dependências e configurações do Maven
└── README.md          # Documentação do projeto
```

---

## 🚀 Como Executar o Back-end Localmente

### Pré-requisitos
* Java 21+
* Maven 3.9+
* PostgreSQL (local ou instância em nuvem como Neon)

### 1. Clonar o repositório
```bash
git clone https://github.com.git
cd integration-trust-engine
```

### 2. Configurar as variáveis de ambiente
Defina as credenciais do banco PostgreSQL no seu ambiente ou no arquivo `application.properties`:
```env
DATABASE_URL=jdbc:postgresql://seu-host-neon.aws.neon.tech/neondb?sslmode=require
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
```

### 3. Executar a aplicação
```bash
mvn spring-boot:run
```

---

## 📌 Status do Projeto

* **Concluído:** API REST em Java/Spring Boot, persistência em PostgreSQL (Neon), deploy em nuvem (Render + GitHub Pages), CRUD completo de integrações, modo autônomo de telemetria e logs em tempo real.
* **Em evolução:** Integração nativa com rotacionamento dinâmico de certificados mTLS reais e alertas automatizados via mensageria.
