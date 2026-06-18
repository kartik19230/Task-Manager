# Task Manager 

## Overview

Task Manager is a Spring Boot MVC application built as a learning project to understand the core concepts of the Spring ecosystem before starting larger projects.

The goal of this project was not to build a production-grade application, but to gain practical experience with:

* Spring MVC
* Spring Data JPA
* Bean Validation
* Session Management
* Interceptors
* Pagination
* Sorting
* JPQL
* MVC Architecture

---

## Project Objectives

This project was created to learn:

* How Spring MVC processes requests and responses
* How IOC and Dependency Injection work
* How Controllers, Services, and Repositories interact
* How Hibernate manages entities
* How Spring Data JPA simplifies database operations
* How validation is handled using DTOs
* How sessions and interceptors can be used for authentication

---

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL

### Frontend

* JSP
* JSTL
* Spring Form Tags

### Utilities

* Lombok
* Maven
* Bean Validation (JSR-380)
* BCrypt Password Encoder

---

## Project Structure

```text
src/main/java

controller/
service/
repository/
entity/
dto/
config/
interceptor/
exception/
```

---

# Features

## Authentication Module

### User Registration

Features:

* Registration Form
* DTO Validation
* Duplicate Email Check
* Password Encoding
* User Persistence

### User Login

Features:

* Credential Validation
* Password Verification
* Session Creation
* Error Handling

### Logout

Features:

* Session Invalidation
* User Logout

---

## Session Management

Implemented session-based authentication.

```java
session.setAttribute("user", user);
```

Logged-in users remain authenticated until logout or session expiration.

---

## Login Interceptor

Protected routes using a custom Spring MVC interceptor.

Protected Endpoints:

```text
/user/**
/task/**
```

The interceptor checks whether a valid user session exists before allowing access.

---

## Task Management Module

### Create Task

Users can create new tasks.

Task fields:

```text
Title
Description
Completed
Created Date
```

---

### View Tasks

Users can view only their own tasks.

Implemented using:

```java
findByUser(...)
```

---

### Update Task

Users can edit:

* Title
* Description

Authorization checks ensure users can update only their own tasks.

---

### Delete Task

Users can delete their tasks.

Authorization checks prevent access to tasks owned by other users.

---

### Toggle Task Completion

Users can mark tasks as:

```text
Completed
Incomplete
```

Implemented using:

```java
task.setCompleted(!task.isCompleted());
```

---

# Validation

Implemented Bean Validation using DTOs.

Annotations used:

```java
@NotBlank
@Email
@Size
@Valid
```

Validation errors are captured through:

```java
BindingResult
```

and displayed directly on JSP pages.

---

# Entity Relationships

## User → Task

Relationship:

```java
@ManyToOne
private Users user;
```

A user can own multiple tasks.

Each task belongs to one user.

---

# Spring Data JPA Concepts Implemented

## JpaRepository

Repository layer built using:

```java
JpaRepository
```

Benefits:

* CRUD operations
* Pagination support
* Sorting support
* Query generation

---

## Derived Queries

Implemented custom repository methods such as:

```java
findByUser(...)
```

```java
findByUserOrderByCreatedDateAsc(...)
```

Spring automatically generates SQL based on method names.

---

## Pagination

Implemented server-side pagination using:

```java
Page
Pageable
PageRequest
```

Example:

```java
PageRequest.of(page, size);
```

Benefits:

* Efficient database access
* Reduced memory usage
* Better scalability

---

## Sorting

Implemented sorting using:

```java
Sort.by(...)
```

Examples:

* Incomplete tasks first
* Newest tasks first

---

## JPQL

Implemented custom JPQL query using:

```java
@Query(...)
```

Example:

```java
SELECT t
FROM Task t
WHERE t.user = :user
ORDER BY t.completed ASC,
         t.createdDate DESC
```

Learned:

* Entity-based querying
* Named parameters
* Custom query creation

---

# Security Concepts Learned

Although Spring Security was intentionally avoided, the project covers important security fundamentals:

### Password Hashing

Implemented using BCrypt:

```java
passwordEncoder.encode(...)
passwordEncoder.matches(...)
```

### Authentication

Implemented through:

* Login
* Sessions
* Interceptor

### Authorization

Implemented ownership checks before:

* Update Task
* Delete Task
* Toggle Completion

Users cannot modify tasks belonging to other users.

---

# MVC Request Flow

```text
Browser
    ↓
DispatcherServlet
    ↓
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
    ↓
Repository
    ↓
Service
    ↓
Controller
    ↓
Model
    ↓
View Resolver
    ↓
JSP
    ↓
Browser
```

---

# Concepts Learned

## Spring Core

* IOC (Inversion of Control)
* Dependency Injection
* Spring Beans
* Constructor Injection
* Bean Lifecycle Basics

## Spring MVC

* Controllers
* Request Mapping
* Model
* View Resolution
* JSP Integration

## Validation

* DTO Validation
* Bean Validation
* BindingResult

## Persistence

* JPA
* Hibernate
* Entity Relationships
* Derived Queries
* JPQL
* Pagination
* Sorting

## Web Concepts

* Sessions
* Interceptors
* Authentication
* Authorization
* PRG (Post-Redirect-Get)

---

# Learning Outcomes

After completing this project, I gained practical experience with:

* Building layered Spring applications
* Designing Controller-Service-Repository architecture
* Managing user authentication using sessions
* Implementing CRUD operations with Spring Data JPA
* Applying validation using DTOs
* Working with pagination and sorting
* Writing JPQL queries
* Understanding Spring MVC request lifecycle

This project serves as a foundation for larger Spring Boot applications and future projects such as EventHub.
