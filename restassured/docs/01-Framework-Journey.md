# API Automation Framework Journey
## Day 2 – Building an Enterprise Service Layer

Author: Abhishek Soni

---

# Objective

Today's objective was to transform a basic Rest Assured test into an enterprise-grade framework by introducing proper architecture, abstraction, wrappers, reusable components, and Service Object Model (SOM).

Instead of simply writing API tests, the goal is to build a scalable framework that can easily support hundreds of APIs.

---

# Problem with Traditional API Scripts

Initially our API looked like this

```java
given()
.baseUri(...)
.header(...)
.body(...)
.post(...)
```

Although this works for one API, it becomes difficult to maintain when the application grows.

Example

```
5 APIs

↓

50 APIs

↓

500 APIs
```

Every script repeats

• Base URL

• Headers

• Content Type

• Authentication

• given()

• Logging

• Request creation

This violates

DRY (Don't Repeat Yourself)

Single Responsibility Principle

Maintainability

Scalability

---

# Thinking Like an Automation Architect

Instead of thinking

"I have Login API"

We started thinking

"What is the architecture?"

Automation frameworks should be designed exactly like software applications.

---

# Understanding Swagger

Swagger

```
Authentication

    Login

    Signup

    Forgot Password

    Reset Password

User

    Create User

    Delete User

Account

    Create Account

Transaction

    Transfer Money
```

Instead of writing tests directly,

every controller becomes one Service Class.

---

Mapping

Swagger Controller

↓

Java Service Class

```
Authentication
```

↓

```
AuthenticationService
```

Methods become

```
login()

signup()

forgotPassword()

resetPassword()
```

Similarly

```
User Management
```

↓

```
UserService
```

---

# Service Object Model (SOM)

This architecture is called

Service Object Model

Exactly like

Page Object Model

is used for Selenium.

Difference

POM

↓

Models UI Pages

SOM

↓

Models API Services

---

# Wrapper Concept

One of the biggest architectural improvements was introducing a Wrapper.

Instead of calling Rest Assured directly

```
given()
```

everywhere,

we created

```
BaseService
```

which wraps Rest Assured.

Think of it exactly like BrowserUtil in Selenium.

BrowserUtil wraps WebDriver.

BaseService wraps Rest Assured.

---

Interview Explanation

"I created a wrapper over Rest Assured so that business services never directly depend on Rest Assured APIs."

---

# Responsibilities of BaseService

BaseService is responsible for

✔ Creating RequestSpecification

✔ Configuring Base URI

✔ Configuring Content Type

✔ Configuring Common Headers

✔ Sending HTTP Requests

✔ Returning Response

BaseService is NOT responsible for

❌ Login

❌ Signup

❌ Create User

❌ Delete User

Those belong to Service Layer.

---

# Package Structure

Enterprise package structure

```
com.soni.automation.api

    base

        BaseService

    service

        AuthenticationService

        UserService

        AccountService

        AdminService

        TransactionService

        KycService

    model

        LoginRequest

        LoginResponse

    config

    util

    tests
```

Reason

Every package has one responsibility.

---

# BaseService

Created

```
BaseService.java
```

Responsibilities

Initialize

RequestSpecification

Store Base URI

Configure Content Type

Provide reusable HTTP methods

---

# BASE_URI

Created

```java
private static final String BASE_URI
```

Why private?

Encapsulation

Only BaseService should access it.

Why static?

Only one copy should exist for entire class.

Every object shares it.

Why final?

Base URI should never change accidentally.

---

Difference

BASE_URI

```
http://localhost:8080
```

BASE_URL

```
http://localhost:8080/api/auth/login
```

Rest Assured internally uses

```
baseUri()
```

therefore naming it BASE_URI matches Rest Assured terminology.

---

# Why RequestSpecification is NOT static

Created

```java
private RequestSpecification requestSpec;
```

Reason

Every object gets its own RequestSpecification.

This allows each Service object to customize its own request if needed.

---

# Constructor

Created

```java
public BaseService()
```

Constructor initializes

```java
requestSpec
```

using

```java
given()
.baseUri(BASE_URI)
.contentType(ContentType.JSON)
```

Meaning

Whenever a Service object is created,

RequestSpecification is automatically ready.

---

# Wrapper Method

Created

```java
protected Response post(String payload, String endpoint)
```

Purpose

Hide Rest Assured implementation.

Instead of writing

```java
given()
.body(payload)
.post(endpoint)
```

everywhere,

Service classes simply call

```java
post(payload, endpoint)
```

Benefits

Cleaner code

Reusable

Centralized request execution

Easy logging

Easy reporting

Easy retry mechanism

---

# AuthenticationService

Next architectural component

```
AuthenticationService
```

Responsibilities

```
login()

signup()

forgotPassword()

resetPassword()
```

AuthenticationService extends BaseService.

Therefore it automatically inherits

RequestSpecification

Base URI

POST

GET

PUT

DELETE

---

Architecture

```
AuthenticationTest

        │

        ▼

AuthenticationService

        │

extends

        │

        ▼

BaseService

        │

        ▼

Rest Assured

        │

        ▼

HTTP Request

        │

        ▼

Server
```

---

# LoginRequest POJO

Created

```
LoginRequest.java
```

Purpose

Instead of writing JSON manually,

Java object will represent the request.

Later

Jackson

will automatically serialize it into JSON.

---

# Enterprise Design Principles Learned

DRY

Don't Repeat Yourself

---

Single Responsibility Principle

Every class has one responsibility.

---

Abstraction

Hide Rest Assured implementation.

---

Encapsulation

Hide framework internals.

---

Inheritance

AuthenticationService inherits common behavior from BaseService.

---

Wrapper Pattern

BaseService wraps Rest Assured.

---

Service Object Model

Business services become Java classes.

---

# Interview Questions

Q

Why did you introduce BaseService?

A

To centralize all common HTTP request configuration and hide Rest Assured implementation behind a reusable wrapper.

---

Q

Why Wrapper?

A

Wrapper reduces duplicate code, improves maintainability, and isolates framework changes from business services.

---

Q

Why Service Layer?

A

To organize APIs by business domain rather than writing requests directly inside test classes.

---

Q

Why Constructor?

A

Constructor initializes RequestSpecification automatically whenever a Service object is created.

---

Q

Why BASE_URI is static final?

A

There is only one Base URI for the application.

Making it static creates one shared copy.

Making it final prevents accidental modification.

---

# Framework Progress

✅ Understood Swagger Architecture

✅ Introduced Service Object Model

✅ Designed Enterprise Package Structure

✅ Created BaseService

✅ Wrapped Rest Assured

✅ Initialized RequestSpecification

✅ Created Wrapper POST Method

✅ Planned AuthenticationService

✅ Created LoginRequest POJO

---

# Next Day

Create AuthenticationService

Implement Login()

Serialization

Jackson

Response Models

ConfigManager

Environment Support

Logging

Assertions

Schema Validation