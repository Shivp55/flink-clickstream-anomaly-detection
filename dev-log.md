# Dev Log
- Initial project setup and base structure.
- Configured core modules and updated folder structure.
- Added models and simple data structures.
- Implemented basic services and utilities.
- Added repository layer and persistence helpers.
- Integrated REST endpoints for core features.
- Added input validation and request error handlers.
- Implemented authentication flow and middleware.
- Created UI templates and connected API endpoints.
- Added performance tweaks and optimized response times.
2023-07-10T10:00:00  -  Add project dev log
2023-07-14T10:00:00  -  Document Kafka + Flink docker-compose setup
2023-07-18T10:00:00  -  Add clickstream_events topic notes
2023-07-23T10:00:00  -  Implement basic anomaly detection logic
2023-07-28T10:00:00  -  Tune windowing + aggregations
2023-08-02T10:00:00  -  Add logging for anomalous sessions
2023-08-08T10:00:00  -  Refine README and architecture notes
2023-08-14T10:00:00  -  Optimize performance and backpressure handling
2023-08-20T10:00:00  -  Add troubleshooting notes for Kafka + Flink
2023-08-27T10:00:00  -  Finalize project documentation
- [2023-07-01 10:00:00] Created initial Maven module and basic folder structure for the Flink clickstream anomaly detection pipeline.
- [2023-07-03 11:30:00] Introduced ClickstreamAnomalyJob with main entrypoint, basic stream environment setup, and placeholder topology.
- [2023-07-06 09:45:00] Wired FlinkKafkaConsumer with custom JsonDeserializationSchema to read clickstream events from Kafka.
- [2023-07-07 16:20:00] Implemented tumbling processing-time windows and simple anomaly detection based on event volume thresholds.
- [2023-07-09 14:10:00] Documented the approach for generating synthetic clickstream events for local dev and testing.
- [2023-07-10 13:00:00] Created docker-compose.yml with Kafka, Zookeeper, Flink JobManager, and TaskManager for local end-to-end runs.
- [2023-07-11 18:05:00] Standardized log messages around anomaly detection, window results, and Kafka offsets for easier debugging.
- [2023-07-11 19:55:00] Documented docker exec commands for copying the JAR to JobManager and submitting the Flink job.
- [2023-07-12 09:15:00] Captured step-by-step instructions for building the JAR, starting Docker services, and running the pipeline.
- [2023-07-12 15:40:00] Tuned window sizes and anomaly thresholds based on test runs to reduce noise and highlight meaningful spikes.
- [2023-07-13 10:25:00] Improved console output formatting for anomalies to make it easier to skim logs during debugging.
