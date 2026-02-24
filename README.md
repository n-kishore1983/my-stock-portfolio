# My Stock Portfolio - Design Diagram

My Stock Portfolio is an AI-powered application that enables customers to interact with their stock portfolio using natural language.
It integrates with OpenAI's GPT-4o model to provide intelligent analysis and recommendations based on the customer's holdings.
The application fetches real-time stock price data from the API Ninjas stock price API and stores portfolio information in an H2 in-memory database.
Built on Spring Boot 3.5.11 with Spring AI, it follows a clean layered architecture with a REST API, service layer, and repository pattern.

## System Architecture

```mermaid
graph TD
    Client(["👤 Client"])

    subgraph Spring Boot Application
        PC["PortfolioController─────────────────POST /portfolio/ask"]
        PS["PortfolioService─────────────────askQuestion()"]
        TS["ToolsService─────────────────getPortfoliosForCustomer()\ngetStockDetails()"]
        PR["PortfolioRepository─────────────────findByCustomerId()\nfindByStockSymbol()findByCustomerName()"]
    end

    subgraph Database
        H2[("H2 In-Memory DB─────────────────PORTFOLIO TablePK: customer_id + stock_symbol")]
    end

    subgraph External Services
        OAI["☁️ OpenAI API─────────────────GPT-4o Model"]
        AN["☁️ API Ninjas─────────────────/v1/stockprice"]
    end

    Client -->|"POST /portfolio/ask"| PC
    PC --> PS
    PS -->|"chat prompt + tools"| OAI
    PS --> TS
    TS -->|"@Tool: get_customer_stocks"| PR
    TS -->|"@Tool: get_stock_details"| AN
    PR --> H2
```

---

## Sequence Diagram

```mermaid
sequenceDiagram
    actor Client
    participant PC as PortfolioController
    participant PS as PortfolioService
    participant AI as OpenAI (GPT-4o)
    participant TS as ToolsService
    participant DB as H2 Database
    participant API as API Ninjas

    Client->>PC: POST /portfolio/ask {"question"}
    PC->>PS: askQuestion(question)
    PS->>AI: prompt(question) + tools
    AI-->>TS: Tool call: get_customer_stocks(customerId)
    TS->>DB: findByCustomerId(customerId)
    DB-->>TS: List<Portfolio>
    TS-->>AI: Portfolio data
    AI-->>TS: Tool call: get_stock_details(ticker)
    TS->>API: GET /v1/stockprice?ticker=AAPL
    API-->>TS: Stock price data
    TS-->>AI: Stock price data
    AI-->>PS: AI analysis & recommendations
    PS-->>PC: response
    PC-->>Client: AI response
```

---

## Class Diagram

```mermaid
classDiagram
    class PortfolioController {
        -PortfolioService portfolioService
        +ask(String question) String
    }

    class PortfolioService {
        -ChatClient chatClient
        -ToolsService toolsService
        -String openAiApiKey
        +askQuestion(String question) String
    }

    class ToolsService {
        -PortfolioRepository portfolioRepository
        -RestClient.Builder restClientBuilder
        -String tickrApiKey
        -String tickrApiUrl
        +getPortfoliosForCustomer(Long customerId) List~Portfolio~
        +getStockDetails(String ticker) String
    }

    class PortfolioRepository {
        +findByCustomerId(Long) List~Portfolio~
        +findByStockSymbol(String) List~Portfolio~
        +findByCustomerName(String) List~Portfolio~
    }

    class Portfolio {
        +Long customerId
        +String stockSymbol
        +String customerName
        +Integer quantity
    }

    class PortfolioId {
        +Long customerId
        +String stockSymbol
    }

    PortfolioController --> PortfolioService
    PortfolioService --> ToolsService
    PortfolioRepository --> Portfolio
    Portfolio --> PortfolioId : uses @IdClass
    ToolsService --> PortfolioRepository
```

---

## Entity Relationship Diagram

```mermaid
erDiagram
    PORTFOLIO {
        bigint customer_id PK
        varchar stock_symbol PK
        varchar customer_name
        integer quantity
    }
```

---

## Data Flow

```mermaid
flowchart LR
    subgraph Input
        Q["User Question"]
    end

    subgraph Processing
        LLM["GPT-4o LLM"]
        T1["Tool:get_customer_stocks"]
        T2["Tool:get_stock_details"]
    end

    subgraph Data Sources
        DB[("H2 DB")]
        EXT["API Ninjas"]
    end

    subgraph Output
        R["AI Response\n& Recommendations"]
    end

    Q --> LLM
    LLM --> T1
    LLM --> T2
    T1 --> DB
    T2 --> EXT
    DB --> LLM
    EXT --> LLM
    LLM --> R
```

