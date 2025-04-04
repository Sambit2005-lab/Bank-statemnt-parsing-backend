# Bank-statemnt-parsing-backend
# **Bank Statement Parser API**  
*AI-Powered PDF Processing with Spring Boot*  

[![Java 17](https://img.shields.io/badge/Java-17-blue)](https://openjdk.org/)
[![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.1.5-brightgreen)](https://spring.io/projects/spring-boot)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.28-pink)](https://projectlombok.org/)

## **📌 Table of Contents**
1. [Features](#-features)
2. [Tech Stack](#-tech-stack)
3. [Installation](#-installation)
4. [Lombok Setup](#-lombok-configuration)
5. [API Documentation](#-api-documentation)
6. [Project Structure](#-project-structure)
7. [Troubleshooting](#-troubleshooting)
8. [Roadmap](#-roadmap)

---

## **✨ Features**
- **PDF Text Extraction** using Apache PDFBox
- **LLM Integration** (OpenAI GPT-4) for structured data parsing
- **Multi-Source Input**:
  - File upload via API
  - Local file path processing
- **Password Generation** from user data (Firstname, DOB, Account Type)
- **Production-Ready**:
  - Redis caching
  - Async processing
  - Swagger documentation

---

## **🛠 Tech Stack**
| Component          | Technology |
|--------------------|------------|
| Backend Framework  | Spring Boot 3.1 |
| PDF Processing     | Apache PDFBox 3.0 |
| LLM Integration    | OpenAI GPT-4 |
| API Documentation  | Swagger UI |
| Build Tool         | Maven |
| Testing            | JUnit 5, Mockito |

---

## **🚀 Installation**
### **Prerequisites**
- Java 17+
- Maven 3.8+
- OpenAI API Key

### **Steps**
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/bank-statement-parser.git
   cd bank-statement-parser
   ```

2. **Configure environment**:
   ```bash
   echo "openai.api.key=your_api_key_here" > src/main/resources/application.properties
   ```

3. **Build and run**:
   ```bash
   mvn clean install
   java -jar target/bank-statement-api.jar
   ```

4. **Verify**:
   ```bash
   curl http://localhost:8080/actuator/health
   ```

---

## **⚙ Lombok Configuration**
### **IDE Setup**
#### **IntelliJ IDEA**
1. Install Lombok plugin:
   - `File → Settings → Plugins` → Search "Lombok" → Install
2. Enable annotation processing:
   - `Settings → Build → Compiler → Annotation Processors` → ✔ Enable
3. **Restart IDE**

#### **Eclipse**
1. Download [lombok.jar](https://projectlombok.org/download)
2. Run the installer
3. Restart Eclipse

### **Verification**
Check if this compiles:
```java
@Data // Lombok annotation
public class Test {
    private String value;
}

// In main method:
Test test = new Test();
test.setValue("works"); // Should autocomplete
```

---

## **📚 API Documentation**
Access Swagger UI at:  
`http://localhost:8080/swagger-ui.html`

**Key Endpoints**:
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/parse` | POST | Process PDF file |
| `/api/generate-password` | POST | Generate secure password |

**Sample Request**:
```bash
curl -X POST -F "file=@statement.pdf" http://localhost:8080/api/parse
```

---

## **📂 Project Structure**
```
src/
├── main/
│   ├── java/
│   │   └── com/bank/statement/
│   │       ├── config/       # Spring configurations
│   │       ├── controller/   # REST endpoints
│   │       ├── service/      # Business logic
│   │       ├── util/         # Helpers (PDF, Password)
│   │       └── Application.java
│   └── resources/
│       ├── prompts/          # LLM templates
│       └── application.properties
test/                       # Unit & integration tests
docs/                       # Architecture diagrams
```

---

## **🔧 Troubleshooting**
### **Lombok Issues**
| Error | Solution |
|-------|----------|
| "Cannot find symbol" | 1. Enable annotation processing<br>2. `mvn clean compile` |
| IDE not recognizing getters | Reinstall Lombok plugin |

### **Common Errors**
```bash
# OpenAI Connection Issues
export HTTP_PROXY=http://your.proxy:port

# PDF Parsing Failures
Check file encoding: `file -i statement.pdf`
```

---

## **🗺 Roadmap**
- [ ] Add Azure Blob Storage support
- [ ] Multi-language PDF handling
- [ ] Claude AI fallback integration

---

## **📜 License**
MIT License - See [LICENSE](LICENSE)

