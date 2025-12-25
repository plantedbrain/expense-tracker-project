# 💸 Expense Tracker – Full Stack Application

A **full-stack Expense Tracker application** that allows users to **record daily expenses and view them in real time**.  
The project demonstrates **frontend–backend integration**, **REST API design**, and **AWS DynamoDB usage** for persistent storage.

This project was built to understand **end-to-end application development and cloud-native architecture**.

---

## 🧠 What Problem This Project Solves

Managing daily expenses is often done manually or using complex tools.  
This project provides a **simple, lightweight expense tracking system** where:

- Users can add expenses with title, amount, and date
- Data is stored persistently in **AWS DynamoDB**
- Expenses are fetched dynamically via REST APIs
- The UI updates automatically after every change

---

## 🧱 Tech Stack

### Frontend
- React (Create React App)
- Axios
- HTML / CSS

### Backend
- Spring Boot
- REST APIs
- AWS SDK for DynamoDB
- Deployed on AWS Elastic Beanstalk

### Database
- **AWS DynamoDB (NoSQL)**
  - Scalable
  - Schema-less
  - Managed by AWS

---

## 🔧 Backend Overview (Spring Boot + DynamoDB)

### Responsibilities
- Expose REST APIs for expense management
- Persist and fetch data from DynamoDB
- Handle CORS and request validation

### API Endpoints

| Method | Endpoint | Description |
|------|--------|------------|
| GET | `/api/expenses` | Fetch all expenses |
| POST | `/api/expenses` | Add a new expense |

### Example Request Payload
```json
{
  "title": "Coffee",
  "amount": 150,
  "date": "2025-01-12"
}
```
---
## 🎨 Frontend Overview (React)
### Responsibilities
- Collect user input
- Display expenses in a table
- Call backend APIs using Axios
- Update UI automatically after data changes

### API Configuration (App.js)
```js
const API = "http://localhost:8080/api/expenses";
```
For deployed backend:
```js
const API = "http://<elastic-beanstalk-url>/api/expenses";
```
---
## ▶️ Run the Project Locally
### Backend
```bash
cd backend
mvn spring-boot:run
```
Runs On:
```arduino

http://localhost:8080

```
### Frontend
```bash
cd frontend
npm install
npm start
```
Runs On:
```arduino

http://localhost:3000

```
---
## 🏗️ Build Frontend for Deployment
```bash

npm run build

```
Output:
```powershell

build/
├── index.html
├── asset-manifest.json
└── static/

```
---
## 🛠️ Key Learnings from This Project
- Designing RESTful APIs
- Integrating React with Spring Boot
- Using AWS DynamoDB with Spring Boot
- Handling cross-origin requests (CORS)
- Deploying backend services on AWS

---
## 👨‍💻 Author
Harshvardhan Rao<br>
Java Backend & Full-Stack Developer<br>
Focused on building scalable, cloud-native systems 🚀
