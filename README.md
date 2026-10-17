# UI & API Test Automation Framework – Green & Tasty Restaurant

This repository contains a **comprehensive test automation framework** for the **Green & Tasty Restaurant** application, covering both **Frontend UI** and **Backend API** testing. 
Built during EPAM project-based learning, this Maven-based Java framework leverages **Selenium WebDriver** and **Rest Assured**, structured with **Cucumber BDD** for clear requirement coverage, and orchestrated via **TestNG**.

---

## 🛠️ Tools & Technologies

| Category | Tools |
|-------|------|
| **Programming Language** | Java |
| **Build Management** | Maven |
| **UI Automation** | Selenium WebDriver, AShot (Screenshots) |
| **API Automation** | Rest Assured, JSON Schema Validator |
| **Testing Framework** | TestNG, Cucumber (BDD / Gherkin) |
| **Design Patterns** | Page Object Model (POM), Factory Pattern |
| **Reporting & Logging** | Allure Reports, Log4j2 |

---

## 🧪 Application & Test Coverage Summary

### UI Automation Suite
Evaluates the frontend application for functional accuracy, role-based access, and user flows.
- **Key User Journeys:**
  - User Login & Profile Registration
  - Menu Browsing & Viewing Restaurant Details
  - Table Reservations (Customer & Waiter Perspectives)
  - Automatic Role Assignment

### API Automation Suite
Validates RESTful endpoints for functionality, security, and payload integrity.
- **Key Modules Covered:**
  - **Authentication:** Login and Registration
  - **Locations & Menus:** Retrieving locations, menu browsing, popular/specialty dishes
  - **Reservations & Bookings:** Table availability, client bookings, cancellations, waiter workflows
  - **Feedback:** Customer and location-specific feedback
- **Validation Techniques:** Status code verification, JSON payload extraction, and strict JSON Schema validation.

---

## ⚙️ Framework Architecture & Features

### 🧠 Design Principles
- **Separation of Concerns:** Distinct execution suites for UI and API (`xmls/ui` and `xmls/api`), allowing independent or parallel execution.
- **Page Object Model (POM):** UI elements and actions are encapsulated in dedicated page classes (`CustomerDashboardPage`, `BookTablePage`, `SignInPage`, etc.) for maintainability.
- **WebDriver Factory:** Centralized browser management and initialization using a Factory design (`BrowserFactory`, `WebDriverManagerHandler`).
- **Data Serialization/Deserialization:** POJO models used extensively to map API request/response payloads (`UserLogin`, `ReservationRequest`, `Location`, etc.).

### 🚨 Error Handling & Stability
- Custom exception handling (`WebDriverNotFoundException`, `RateLimitException`, `AuthenticationException`) for clear failure diagnosis.
- Implementation of API filters (`ErrorHandlingFilter`, `RateLimitingFilter`, `RetryFilter`) for robust backend interactions.
- Dynamic explicit waits (`Waits.java`) to handle UI rendering delays and reduce flakiness.

---

## 📈 Execution Evidence & Reporting

### 🎯 Allure Reports
- Generates visually comprehensive HTML reports detailing step-by-step test execution.
- Attachments include API request/response payloads, status codes, and failure traces.

### 📸 AShot & Screenshots
- Automatic screenshot capturing on UI test failures (`ScreenshotsUtil.java`) for faster defect triage and visual validation.

### 📜 Log4j2 Logging
- Detailed runtime logging mapped via `log4j2.xml`.
- Tracks test execution flow, environmental configurations, and stack traces for streamlined debugging.

---

## ▶️ Test Execution 

The framework is driven by TestNG XML suites and Maven profiles. Test runners are categorized by scope (Smoke, Regression) and application layer (API, UI).

### Run API Tests
```bash
# Execute API Smoke Suite
mvn clean test -DsuiteXmlFile=xmls/api/smoke.xml

# Execute API Regression Suite
mvn clean test -DsuiteXmlFile=xmls/api/regression.xml
```

### Run UI Tests
```bash
# Execute UI Smoke Suite
mvn clean test -DsuiteXmlFile=xmls/ui/smoke.xml

# Execute UI Regression Suite
mvn clean test -DsuiteXmlFile=xmls/ui/regression.xml
```

### Generate Allure Report
```bash
mvn allure:serve
```

---

📌 *This repository serves as a robust, scalable blueprint for end-to-end BDD automation of modern web and API applications.*
