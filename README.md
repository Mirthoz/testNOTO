# testNOTO

# Transaction Processing System

## 📌 Description
This project includes two Spring Boot microservices:
- **transaction-producer** — generates transactions and sends them to the consumer service.
- **transaction-consumer** — receives transactions, processes them for fraud detection, and saves them to a remote MongoDB database.

## 🛠 Technologies
- **Java 17** (Spring Boot)
- **MongoDB** (for data storage)
- **Docker Compose** (for orchestrating the services)

## 🚀 Deployment Instructions

### 1️⃣ Prerequisites
Ensure you have the following installed:
- **Docker** and **Docker Compose**.

You can download and install Docker from: [https://www.docker.com/get-started](https://www.docker.com/get-started)

### 2️⃣ Clone the Repository
Clone the repository to your local machine:

```bash
git clone <repo-url>
cd <repo-folder>
```

### 3️⃣ Build and Run the Services with Docker Compose
To start the services with Docker Compose, run the following command:

```bash
docker-compose up --build
```

This will:
- Build the Docker images for `transaction-producer` and `transaction-consumer`.
- Start the containers for both services.

### 4️⃣ Access the Services
Once the containers are running, you can access them at:
- **transaction-producer**: `http://localhost:8081`
- **transaction-consumer**: `http://localhost:8082`

### 5️⃣ MongoDB Connection Configuration
The **transaction-consumer** microservice is configured to connect to a remote MongoDB cluster. The connection URI is specified in the `docker-compose.yml`:

```env
SPRING_DATA_MONGODB_URI=mongodb+srv://pavlovmiroslav92:Q9cIj0mveskCPAAT@cluster135.4xns2.mongodb.net
```

If you want to use a different MongoDB cluster or credentials, replace the MongoDB URI in the environment variable with your own connection string.

### 6️⃣ Run Tests
To run tests inside the containers, execute the following commands:

```bash
docker-compose exec transaction-producer ./mvnw test
docker-compose exec transaction-consumer ./mvnw test
```

### 7️⃣ Stopping the Containers
To stop all running containers, run:

```bash
docker-compose down
```

---

## 💡 Notes
- The microservices communicate over HTTP, with `transaction-producer` sending transactions to `transaction-consumer`.
- The **transaction-consumer** checks for fraud conditions and saves valid transactions to MongoDB.

---

## 📝 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
