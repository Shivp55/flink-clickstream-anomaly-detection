#!/bin/bash
kafka-topics --create \
  --topic clickstream_events \
  --bootstrap-server kafka:29092 \
  --partitions 3 \
  --replication-factor 1
