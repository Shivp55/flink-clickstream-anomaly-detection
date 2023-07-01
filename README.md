# Flink Clickstream Anomaly Detection

A complete streaming pipeline that uses **Kafka** and **Apache Flink** to detect unusual clickstream activity in real time.  
This project simulates a production-style event flow with windowing, aggregation, and simple anomaly alerts.

## 📌 Key Features
- Real-time ingestion through Kafka  
- JSON deserialization using a custom schema  
- Tumbling window analysis in Flink  
- Lightweight anomaly detector  
- Docker-based cluster with JobManager + TaskManager  
- Easy-to-run setup for local testing  
- Clean structure for portfolio demonstration  

## 📦 Tech Stack

| Component | Version | Purpose |
|----------|---------|---------|
| Apache Flink | 1.17.x | Stream processing |
| Apache Kafka | 7.5.x (Confluent) | Event ingestion |
| Zookeeper | 7.5.x | Kafka coordination |
| Java | 11+ | Flink job |
| Docker Compose | Latest | Local cluster |
| Maven | Build system | Packaging JAR |

## 📁 Project Structure

```
flink-clickstream-anomaly/
│── flink-job/
│   ├── src/main/java/com/shaishav/flink/
│   │   ├── model/ClickEvent.java
│   │   ├── util/JsonDeserializationSchema.java
│   │   ├── util/SimpleAnomalyDetector.java
│   │   └── ClickstreamAnomalyJob.java
│   └── target/flink-clickstream-anomaly-1.0-SNAPSHOT.jar
│
│── kafka/
│   └── create-topics.sh
│
│── docker-compose.yml
│── README.md
```

## ▶️ How to Run

### 1. Build the JAR
```bash
cd flink-job
mvn clean package -DskipTests
```

### 2. Start the Kafka + Flink cluster
```bash
docker compose up -d
```

### 3. Copy the JAR into the JobManager
```bash
docker cp flink-job/target/flink-clickstream-anomaly-1.0-SNAPSHOT.jar jobmanager:/opt/flink/usrlib/
```

### 4. Run the job
```bash
docker exec -it jobmanager flink run -c com.shaishav.flink.ClickstreamAnomalyJob /opt/flink/usrlib/flink-clickstream-anomaly-1.0-SNAPSHOT.jar
```

## ✍️ Author
Built and maintained by **Shaishav Parekh**.
