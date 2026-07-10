# API Automation Framework Journey
### Author: Abhishek Soni
### Framework: Rest Assured + TestNG + Maven + Docker + AWS

---

# Day 1

## Created Maven Project

### Why?

To create a standard Java project structure managed by Maven.

---

## Selected Archetype

```
org.apache.maven.archetypes
maven-archetype-quickstart
```

### Why?

- Standard Maven project
- Generates default folder structure
- Used by almost every Java project

Generated

```
src/main/java
src/test/java
pom.xml
```

---

# Updated Java Version

Added

```xml
<properties>

    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>

</properties>
```

### Why?

Without this Maven uses default Java compatibility.

We explicitly tell Maven to compile using Java 17.

---

# Added Dependencies

## Rest Assured

Purpose

```
API Automation
```

Scope

```
test
```

---

## TestNG

Purpose

```
Test Execution
Assertions
Annotations
Parallel Execution
```

Scope

```
test
```

---

# Maven Lifecycle

```
mvn compile

↓

mvn test

↓

mvn package

↓

mvn install

↓

mvn deploy
```

### Important

Running

```
mvn deploy
```

automatically executes

```
compile

↓

test

↓

package

↓

install

↓

deploy
```

---

# Static Import

Instead of

```java
RestAssured.given()
```

Used

```java
import static io.restassured.RestAssured.*;

given()
```

### Why?

Improves readability.

No performance improvement.

---

# Fluent Interface

Instead of

```java
RequestSpecification x

RequestSpecification y

RequestSpecification z
```

Used

```java
given()
.header()
.body()
.post()
```

Reason

Every method returns another object that exposes the next method.

---

# RequestSpecification

RequestSpecification is an interface.

```
RequestSpecification
        ↑
RequestSpecificationImpl
```

RestAssured internally creates the implementation.

---

# Method Chaining Rule

Wrong

```
Method chaining works if every method returns something.
```

Correct

```
Method chaining works when every method returns an object that exposes the next callable method.
```

---

# Service Layer

Swagger

```
Authentication

Login

Signup

Forgot Password
```

↓

AuthenticationService

```
login()

signup()

forgotPassword()
```

Reason

Business APIs are grouped together.

Benefits

- Maintainable
- Reusable
- Scalable

---

# Git

## Initialize Repository

```bash
git init
```

Purpose

Creates

```
.git
```

folder.

---

## Check Status

```bash
git status
```

Purpose

Shows

- Modified files
- Staged files
- Untracked files

---

## Stage Everything

```bash
git add .
```

Purpose

Moves changes from

Working Directory

↓

Staging Area

---

## Stage Single File

```bash
git add pom.xml
```

---

## Remove From Stage

```bash
git restore --staged pom.xml
```

Purpose

Removes file from staging.

Working file remains unchanged.

---

## Remove Everything From Stage

```bash
git restore --staged .
```

Purpose

Undo

```
git add .
```

---

## Remove Tracked File From Git Index

```bash
git rm --cached fileName
```

Example

```bash
git rm --cached restassured/.classpath
```

Purpose

Stops Git tracking the file.

Keeps the file locally.

---

## Remove Folder From Git Index

```bash
git rm -r --cached folderName
```

Example

```bash
git rm -r --cached restassured/.settings
```

---

## Commit

```bash
git commit -m "Initial API Framework"
```

Purpose

Creates a snapshot inside

```
.git/objects
```

Updates

```
HEAD

Branch

Commit History
```

---

## Push

```bash
git push origin main
```

Purpose

Uploads local commits to GitHub.

---

## Pull

```bash
git pull origin main
```

Purpose

Downloads latest changes.

---

# .gitignore

Purpose

Ignore generated files.

Example

```
target/

test-output/

.classpath

.project

.settings/
```

Reason

These files are machine-specific.

---

# Eclipse Shortcuts

Organize Imports

```
Ctrl + Shift + O
```

Format Code

```
Ctrl + Shift + F
```

Rename Variable

```
Alt + Shift + R
```

Open Declaration

```
F3
```

Quick Fix

```
Ctrl + 1
```

Run Test

```
Ctrl + F11
```

Debug

```
F11
```

---

# Interview Notes

Q

Why Static Import?

A

Cleaner code.

Improves readability.

---

Q

Why Service Layer?

A

Separates business logic from test logic.

Improves scalability.

---

Q

Why RequestSpecification Interface?

A

Programs to an interface rather than implementation.

Supports abstraction.

---

Q

Why Maven?

A

Dependency Management

Build Management

Project Standardization

---

# Real Company Folder Structure

```
Framework

│

├── src

├── docs

├── services

├── models

├── utilities

├── testdata

├── config

├── reports

├── docker

├── Jenkinsfile

├── Dockerfile

├── docker-compose.yml

├── pom.xml

└── README.md
```

---

# Things Learned

✅ Maven

✅ Java 17

✅ Rest Assured

✅ TestNG

✅ Static Import

✅ Fluent Interface

✅ RequestSpecification

✅ Method Chaining

✅ Git Basics

✅ Service Layer Design

---

# Coming Next

- Request Builder
- POJO
- Serialization
- Deserialization
- Jackson
- Token Management
- Base Service
- Request Handler
- Logging
- Reporting
- Schema Validation
- Docker
- Jenkins
- AWS ECS
- GitHub Actions
- Enterprise Framework