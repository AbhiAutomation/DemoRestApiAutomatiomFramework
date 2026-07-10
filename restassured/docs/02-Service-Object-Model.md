# Service Object Model (SOM)
### Enterprise API Automation Design Pattern

**Author:** Abhishek Soni

---

# What is Service Object Model (SOM)?

Service Object Model (SOM) is an API automation design pattern where each business service (or Swagger/OpenAPI controller) is represented by a dedicated Java class.

Instead of writing HTTP requests directly inside test classes, all API operations belonging to a business domain are encapsulated inside a Service class.

Just as Selenium uses the **Page Object Model (POM)** to model web pages, API automation uses the **Service Object Model (SOM)** to model backend services.

---

# Simple Definition (Interview)

> Service Object Model is a framework design pattern where every business service or API module is represented as a Java class containing reusable methods for interacting with that service.

---

# Why Do We Need SOM?

Imagine a banking application with 400+ APIs.

Swagger

```
Authentication
    Login
    Logout
    Signup
    Forgot Password

User
    Create User
    Update User
    Delete User

Account
    Open Account
    Close Account

Transaction
    Transfer Money
    Check Balance

KYC
    Upload PAN
    Verify Aadhaar
```

If every API request is written directly inside test classes:

```
AuthenticationTest

given()
.baseUri(...)
.header(...)
.body(...)
.post(...)

---------------------------------

UserTest

given()
.baseUri(...)
.header(...)
.body(...)
.post(...)

---------------------------------

TransactionTest

given()
.baseUri(...)
.header(...)
.body(...)
.post(...)
```

Problems

- Duplicate code
- Hardcoded URLs
- Difficult maintenance
- No abstraction
- Poor scalability

---

# Solution

Create one Service class for every business module.

```
AuthenticationService

login()

logout()

signup()

forgotPassword()

-------------------------

UserService

createUser()

deleteUser()

updateUser()

-------------------------

TransactionService

transferMoney()

checkBalance()

-------------------------

KycService

uploadPan()

verifyAadhaar()
```

Each service knows only its own APIs.

---

# Relationship with Swagger

Swagger Controller

↓

Java Service Class

Example

```
Authentication Controller
```

↓

```
AuthenticationService
```

Endpoints

```
POST /login

POST /signup

POST /logout
```

become

```
login()

signup()

logout()
```

This creates a one-to-one mapping between Swagger and Java.

---

# Architecture

```
                 Test Layer

        AuthenticationTest

                 │

                 ▼

        AuthenticationService

                 │

                 ▼

            BaseService

                 │

                 ▼

           Rest Assured

                 │

                 ▼

             HTTP Client

                 │

                 ▼

             Application
```

---

# Why BaseService?

Every service requires

- Base URL
- Headers
- Authentication
- RequestSpecification
- Logging
- HTTP Methods

Instead of repeating these in every service,

they are centralized inside

```
BaseService
```

AuthenticationService extends BaseService

UserService extends BaseService

AccountService extends BaseService

TransactionService extends BaseService

---

# Enterprise Package Structure

```
com.soni.automation.api

│

├── base

│      BaseService

│

├── service

│      AuthenticationService

│      UserService

│      AccountService

│      TransactionService

│      KycService

│

├── model

│      request

│      response

│

├── config

│

├── utils

│

└── tests
```

---

# Benefits in Real IT Industry

## 1. High Maintainability

Suppose Login API changes.

Old

```
/api/auth/login
```

New

```
/api/auth/sso-login
```

Without SOM

Every test must change.

With SOM

Only

```
AuthenticationService
```

changes.

Tests remain untouched.

---

## 2. Better Reusability

Instead of writing

```
given()

.body()

.post()
```

50 times,

write

```
authenticationService.login()
```

once.

Every test reuses it.

---

## 3. Better Readability

Instead of

```java
given()
.body(payload)
.post("/api/auth/login");
```

the test becomes

```java
authenticationService.login(payload);
```

The second version is easier to understand.

---

## 4. Business-Oriented Code

Tests describe business behavior instead of HTTP details.

Example

Bad

```
POST /api/auth/login
```

Good

```
login()
```

Business language is easier for developers, testers, and stakeholders.

---

## 5. Easier Refactoring

Suppose authentication changes from

```
JWT Login
```

to

```
SSO Login
```

Only AuthenticationService changes.

Every existing test continues to work.

---

## 6. Supports Microservice Architecture

Modern applications usually contain many services.

```
Authentication

User

Account

Payment

Notification

KYC

Order

Inventory

Shipping

Audit

Report
```

Each service gets its own Java class.

```
AuthenticationService

UserService

AccountService

PaymentService

NotificationService

...
```

This naturally matches microservice architecture.

---

## 7. Better Team Collaboration

Large teams work on different services.

Example

Developer A

Authentication

Developer B

Payments

Developer C

Orders

QA Team

AuthenticationService

PaymentService

OrderService

There are fewer merge conflicts because everyone works on separate service classes.

---

## 8. Modular Execution

Manager says

"Run only Authentication regression."

Simply execute

```
AuthenticationTest
```

Only AuthenticationService methods are used.

Other services remain untouched.

---

## 9. Easier Debugging

When Login fails,

the problem is usually inside

```
AuthenticationService
```

instead of searching hundreds of test files.

---

## 10. Better Scalability

Framework starts with

```
10 APIs
```

Later

```
100 APIs
```

Later

```
500 APIs
```

SOM continues to work without architectural changes.

---

## 11. Centralized API Changes

Common changes such as

- Authentication
- Headers
- Logging
- Retry
- Timeout

can be implemented once inside BaseService.

Every service automatically benefits.

---

## 12. Cleaner Test Cases

Instead of

```java
given()

.baseUri(...)

.header(...)

.body(...)

.post(...)
```

write

```java
authenticationService.login(loginRequest);
```

Tests focus on verification rather than request construction.

---

# Comparison

| Traditional Script | Service Object Model |
|--------------------|----------------------|
| HTTP details inside tests | Business methods inside services |
| Duplicate code | Reusable methods |
| Hard to maintain | Easy to maintain |
| Hardcoded URLs | Centralized endpoints |
| Poor scalability | Highly scalable |
| Tight coupling | Loose coupling |
| Difficult refactoring | Easy refactoring |

---

# Difference Between POM and SOM

| Page Object Model | Service Object Model |
|-------------------|----------------------|
| UI Automation | API Automation |
| Models web pages | Models business services |
| Uses Selenium/Playwright | Uses Rest Assured |
| Page methods | Service methods |
| Click(), EnterText() | login(), createUser(), transferMoney() |

---

# Real Companies Using Similar Architecture

Although organizations may use different names (API Client, Service Layer, Resource Layer, API Wrapper, Client SDK), the underlying concept is widely adopted in enterprise software.

Examples include teams building automation around:

- Spring Boot microservices
- REST APIs
- Banking applications
- Insurance platforms
- E-commerce systems
- Telecom applications
- Healthcare platforms

Frameworks are commonly organized into service classes such as `AuthenticationService`, `UserService`, and `PaymentService`, backed by a shared base layer.

---

# Interview Answer (30 Seconds)

> "I designed the API automation framework using the Service Object Model. Every Swagger controller is represented by a dedicated Service class, such as AuthenticationService or UserService. These service classes inherit common HTTP behavior from BaseService, while encapsulating only business-specific operations. This separation improves maintainability, scalability, reusability, and makes the framework easier to extend as new APIs or microservices are added."

---

# Key Takeaways

✅ Maps Swagger controllers to Java service classes

✅ Separates business logic from HTTP implementation

✅ Encourages code reuse

✅ Reduces maintenance effort

✅ Supports large-scale API automation

✅ Aligns well with microservice-based applications

✅ Improves readability and collaboration

✅ Simplifies refactoring when APIs evolve