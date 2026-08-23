# Enterprise Financial Analytics Platform

An evolving enterprise-style full-stack portfolio project combining customer management, financial analytics, reporting, and data-driven decision support.

> **Project status:** Active development. The repository is being built incrementally; features described as planned are not yet presented as complete.

## Goals

- Demonstrate scalable Angular + Java/Spring Boot application architecture.
- Build REST APIs backed by PostgreSQL.
- Add financial transaction analytics and KPI dashboards.
- Apply accessibility, validation, testing, security, and maintainable engineering practices.
- Package the application for reproducible local development and later cloud deployment.

## Current Implementation

- Spring Boot backend skeleton (Java 17)
- PostgreSQL configuration through environment variables
- Customer domain model and REST CRUD endpoints
- Initial relational schema for customers and financial transactions
- Angular frontend workspace dependencies initialized
- Docker Compose PostgreSQL development service

## Planned Architecture

```text
Angular Web Application
        |
        | REST/JSON
        v
Spring Boot API
        |
        | JPA
        v
PostgreSQL
```

## Technology Stack

**Frontend:** Angular, TypeScript, RxJS (UI implementation in progress)  
**Backend:** Java 17, Spring Boot, Spring Web, Spring Data JPA, Bean Validation  
**Database:** PostgreSQL  
**DevOps:** Git/GitHub, Docker Compose; CI/CD planned  
**Testing:** Spring Boot Test; frontend/backend test suites planned

## Repository Structure

```text
backend/       Spring Boot REST API
database/      Database schema and future migration/data assets
frontend/      Angular web application
docs/          Architecture and product documentation (planned)
```

## Current API

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET | `/api/customers` | List customers |
| GET | `/api/customers/{id}` | Retrieve a customer |
| POST | `/api/customers` | Create a customer |
| PUT | `/api/customers/{id}` | Update a customer |
| DELETE | `/api/customers/{id}` | Delete a customer |

## Roadmap

1. Complete Angular application configuration and accessible dashboard shell.
2. Add customer management UI and API integration.
3. Implement financial transaction domain and analytics APIs.
4. Add KPI cards, trend visualizations, filters, and reporting.
5. Add authentication and role-based authorization.
6. Add automated tests and GitHub Actions CI.
7. Containerize the complete application and document deployment.

## Engineering Approach

This repository is intentionally developed in stages. Each milestone will add working functionality, tests, documentation, and measurable improvements rather than presenting placeholder features as finished work.

## License

MIT
