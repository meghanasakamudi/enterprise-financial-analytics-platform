# Enterprise Financial Analytics Platform

A simple full-stack portfolio project for customer management and financial analytics.

![Build and Test](https://github.com/meghanasakamudi/enterprise-financial-analytics-platform/actions/workflows/ci.yml/badge.svg)

## Tech Stack

- Angular + TypeScript
- Java 17 + Spring Boot
- PostgreSQL
- Docker Compose
- GitHub Actions

## Features

- Customer management
- Revenue and expense transactions
- Financial KPIs such as revenue, net income, expenses, and operating margin
- Six-month revenue trend
- Customer search
- Basic responsive dashboard
- REST API integration between Angular and Spring Boot

## Architecture

```text
Angular Frontend
      |
      | REST API
      v
Spring Boot Backend
      |
      | JPA
      v
PostgreSQL
```

## Run Locally

### 1. Start PostgreSQL

```bash
docker compose up -d
```

### 2. Start the backend

```bash
cd backend
mvn spring-boot:run
```

Backend: `http://localhost:8080`

### 3. Start the frontend

```bash
cd frontend
npm install
npm start
```

Frontend: `http://localhost:4200`

## Optional Demo Data

`database/demo-data.sql` contains fictional sample customers and transactions for local testing.

## Main API Endpoints

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET / POST | `/api/customers` | List or create customers |
| PUT / DELETE | `/api/customers/{id}` | Update or delete a customer |
| GET / POST | `/api/transactions` | List or create transactions |
| GET | `/api/analytics/summary` | Financial KPI summary |
| GET | `/api/analytics/monthly` | Monthly revenue trend |

## Status

Core portfolio functionality is implemented and the repository is validated through GitHub Actions. Additional features may be added gradually.

## License

MIT
