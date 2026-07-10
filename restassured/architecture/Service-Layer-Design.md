# Service Layer Design in API Automation Framework

> "A scalable framework separates **business logic** from **test logic**."

---

# Why do we need a Service Layer?

A beginner API test usually looks like this:

```java
@Test
public void loginTest() {

    given()
        .baseUri("http://64.227.160.186:8080")
        .header("Content-Type","application/json")
        .body(loginRequest)
        .post("/api/auth/login");

}
```

Although this works, it is **not suitable for enterprise projects** because:

- Base URL is hardcoded.
- Endpoint is hardcoded.
- Headers are repeated.
- Request construction is repeated.
- Business logic exists inside the test.

This creates high maintenance when the project grows.

---

# The Problem

Imagine the project contains:

- 5 APIs
- 50 APIs
- 500 APIs

Suppose tomorrow developers change

```
/api/auth/login
```

to

```
/api/auth/sso-login
```

If every test directly calls the endpoint,

```
LoginTest
SignupTest
ForgotPasswordTest
UserTest
AdminTest
...
```

then dozens (or hundreds) of test files need modification.

This violates the **Single Responsibility Principle (SRP)**.

---

# Better Design

Instead of calling APIs directly inside tests,

we create **Service Classes**.

Example:

```
AuthenticationService
```

The service is responsible for communicating with Authentication APIs.

---

# Swagger to Service Mapping

Swagger

```
Authentication

POST /login
POST /signup
POST /forgot-password
POST /reset-password
```

becomes

```
AuthenticationService

login()

signup()

forgotPassword()

resetPassword()
```

Think of each Swagger section as a Java class.

Each endpoint becomes a method inside that class.

---

# Folder Structure

```
src
│
├── services
│      AuthenticationService.java
│      UserService.java
│      AccountService.java
│      PaymentService.java
│
├── models
│      LoginRequest.java
│      LoginResponse.java
│
├── tests
│      LoginTest.java
│      UserTest.java
│
└── utilities
```

---

# Authentication Service

```java
public class AuthenticationService {

    public Response login(LoginRequest request){

        return given()
                .body(request)
                .post("/api/auth/login");
    }

    public Response signup(SignupRequest request){

        return given()
                .body(request)
                .post("/api/auth/signup");
    }

}
```

Notice that endpoints exist only inside the service.

---

# Test Class

```java
@Test
public void verifyUserCanLogin(){

    AuthenticationService auth =
            new AuthenticationService();

    Response response =
            auth.login(loginRequest);

    Assert.assertEquals(response.statusCode(),200);

}
```

The test only describes **what** should happen.

The service knows **how** it happens.

---

# Benefits

## 1. Better Maintainability

Endpoint changes occur only in one place.

Old

```
/api/auth/login
```

New

```
/api/auth/sso-login
```

Only AuthenticationService changes.

No test modification required.

---

## 2. Better Readability

Instead of

```java
given()
.body(...)
.post("/api/auth/login");
```

tests become

```java
authenticationService.login(request);
```

Much easier to understand.

---

## 3. Better Reusability

Multiple test cases can reuse the same service method.

Example

```
Login Test

Admin Test

User Test

Forgot Password Test
```

All can call

```
authenticationService.login()
```

---

## 4. Low Coupling

Tests do not know:

- Endpoint
- Headers
- Authentication
- Base URL

They only know the business action.

---

## 5. High Cohesion

Authentication-related APIs stay together.

```
AuthenticationService

login()

signup()

forgotPassword()

resetPassword()
```

User APIs stay together.

```
UserService

getProfile()

updateProfile()

deleteProfile()
```

Every class has one business responsibility.

---

# OOP Analogy

Imagine Swagger as Java classes.

```
Authentication
```

↓

```
AuthenticationService
```

Endpoints

```
login

signup

forgotPassword

resetPassword
```

↓

Methods

```
login()

signup()

forgotPassword()

resetPassword()
```

Exactly like Object-Oriented Programming.

---

# Design Principle

```
Swagger Module
        ↓
Service Class
        ↓
API Method
        ↓
Test Class
```

The test never calls the endpoint directly.

---

# Interview Answer

**Q: Why do you create a Service Layer?**

Answer:

> I encapsulate all APIs belonging to the same business functionality inside a Service class. This centralizes endpoint management, improves reusability, reduces duplication, and minimizes maintenance because endpoint or authentication changes are handled in one place rather than across hundreds of test cases.

---

# Key Advantages

✅ Scalable

✅ Reusable

✅ Maintainable

✅ Readable

✅ Low Coupling

✅ High Cohesion

✅ Enterprise Ready

---

# Easy Way to Remember

```
Swagger
        ↓
Business Module
        ↓
Service Class
        ↓
Methods
        ↓
Tests
```

Example

```
Authentication
        ↓
AuthenticationService
        ↓
login()

signup()

forgotPassword()

resetPassword()
        ↓
LoginTest
SignupTest
ForgotPasswordTest
```

---

# Golden Rule

> **Tests should verify business behavior, not know implementation details.**