%KAFKA_HOME%\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9093 --topic fraud-detection --partitions 5 --replication-factor 3 --config segment.bytes=1000000