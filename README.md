# TrackPOI - Backend Challenge
![Java Logo](https://www.vectorlogo.zone/logos/java/java-ar21.svg) 
![Spring Logo](https://www.vectorlogo.zone/logos/springio/springio-ar21.svg)

---

## Índice
1. [Introdução](#introdução)  
2. [Arquitetura do Sistema](#arquitetura-do-sistema)  
3. [Desenvolvimento](#desenvolvimento)  
   - [Estrutura do Projeto](#estrutura-do-projeto)  
   - [Funcionalidades](#funcionalidades)  
   - [Banco de Dados](#banco-de-dados)  
   - [Testes](#testes)  
4. [System Design](#system-design)  
5. [Conclusão](#conclusão)  
6. [Autor](#autor)  

---

## Introdução
O projeto **TrackPOI** é um backend desenvolvido em **Java + Spring Boot** com **MySQL**, criado como parte de um desafio de code interview.  
O objetivo é calcular e disponibilizar o tempo que veículos permanecem em determinados **POIs (Points of Interest)** e permitir consulta por veículo e data.

---

## Arquitetura do Sistema
- Tipo: **Arquitetura em Camadas (Layered Architecture / Monolítica)**  
- **Controller** → Exposição das APIs REST  
- **Service** → Lógica de negócio (cálculo de dwell time, ingestão de posições)  
- **Repository** → Acesso ao banco de dados via JPA/Hibernate  
- **Model** → Representação das entidades (POI, DwellEvent, Position)  

A arquitetura separa responsabilidades, garantindo modularidade, testabilidade e fácil manutenção.

---

## Desenvolvimento

### Estrutura do Projeto

```text
trackpoi/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mobi7/trackpoi/
│   │   │       ├── Mobi7TrackPoiChallengeApplication.java  # classe principal
│   │   │       ├── config/
│   │   │       │   ├── RedisConfig.java
│   │   │       │   ├── SwaggerConfig.java
│   │   │       │   └── WebConfig.java
│   │   │       ├── controller/
│   │   │       │   ├── DwellController.java
│   │   │       │   ├── DwellEngineController.java
│   │   │       │   ├── PoiController.java
│   │   │       │   └── VehicleController.java
│   │   │       ├── external/
│   │   │       │   ├── ApiPositionProvider.java
│   │   │       │   ├── LocalPositionProvider.java
│   │   │       │   └── PositionProvider.java
│   │   │       ├── model/
│   │   │       │   ├── DwellEvent.java
│   │   │       │   ├── Poi.java
│   │   │       │   └── Position.java
│   │   │       ├── repository/
│   │   │       │   ├── DwellEventRepository.java
│   │   │       │   ├── PoiRepository.java
│   │   │       │   └── PositionRepository.java
│   │   │       └── service/
│   │   │           ├── DwellEngineService.java
│   │   │           ├── PoiService.java
│   │   │           └── PositionIngestService.java
│   │   └── resources/  # configurações do Spring Boot
│   └── test/
│       └── java/
│           └── com/mobi7/trackpoi/
│               ├── Mobi7TrackPoiChallengeApplicationTests.java
│               └── unidade/
│                   └── PositionIngestServiceTest.java
├── pom.xml
├── target/  # arquivos compilados
└── mvnw, mvnw.cmd  # scripts do Maven
```
### Funcionalidades
- Captura e ingestão de posições de veículos via API externa.  
- Cadastro e consulta de POIs (nome, latitude, longitude, raio).  
- Cálculo do tempo que cada veículo permanece dentro de um POI (dwell time).  
- APIs REST para consultar os dados por veículo, data ou POI.  
- Página web (front-end) preparada para exibir resultados em tabela.  

### Banco de Dados
- **MySQL**: armazenando POIs, posições e dwell events.  
- Entidades principais:  
  - `Poi` → nome, latitude, longitude, raio  
  - `Position` → veículo, data, velocidade, ignição, latitude, longitude  
  - `DwellEvent` → tempo dentro do POI  
> O Redis está presente no projeto, mas não está em uso neste momento.

### Testes
- Testes unitários com **JUnit 5**.  
- Teste principal: `PositionIngestServiceTest` valida ingestão e processamento das posições.  
- Cobertura focada na camada de **Service** e **Repository**.

---

## System Design
**Fluxo de dados:**
<img width="1000" height="1000" alt="7d598767-e580-4fb2-8947-b39d5a26d5d3" src="https://github.com/user-attachments/assets/ee55bf11-8660-4f17-aef7-977c414ef7f6" />

- Cada camada é responsável por uma única responsabilidade.  
- O cálculo de dwell time é feito considerando o raio do POI e as posições do veículo.  
- Serviço externo para ingestão garante separação entre coleta e processamento de dados.

---

## Conclusão
O backend TrackPOI oferece uma solução robusta para cálculo de tempo de permanência de veículos em POIs.  
- Segue **boas práticas de modularização** e **design patterns** básicos do Spring Boot.  
- Estrutura preparada para escalabilidade futura, incluindo integração com front-end Angular e possíveis caches com Redis.

---

## Autor
- **Nome:** Sansão Dembué Vieira  
- **Email:** vieirasansao42@gmail.com  
- **GitHub:** [https://github.com/sansaovieira](https://github.com/sansaovieira)  
- **LInkedin:** [https://linkedin.com/sansaovieira](https://www.linkedin.com/in/sansaovieira/)  
- **Descrição:** Desenvolvedor Java Fullstack, especialista em Spring Boot, Hibernate e MySQL.

