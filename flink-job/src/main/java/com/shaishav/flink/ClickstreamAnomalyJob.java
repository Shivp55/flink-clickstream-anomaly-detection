package com.shaishav.flink;

import com.shaishav.flink.model.ClickEvent;
import com.shaishav.flink.util.JsonDeserializationSchema;

import java.util.Properties;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

public class ClickstreamAnomalyJob {

    // Simple static threshold for anomalies
    private static final long ANOMALY_THRESHOLD = 200L;

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        // Kafka consumer properties
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "kafka:29092");
        props.setProperty("group.id", "flink-clickstream-anomaly");

        // Consume ClickEvent from Kafka
        FlinkKafkaConsumer<ClickEvent> consumer =
                new FlinkKafkaConsumer<>(
                        "clickstream_events",
                        new JsonDeserializationSchema(ClickEvent.class),
                        props
                );
        consumer.setStartFromLatest();

        DataStream<ClickEvent> events =
                env.addSource(consumer)
                   .name("clickstream-events");

        // Per-user windowed count + anomaly rule (inline, no helper object)
        DataStream<String> anomalies =
                events
                        .keyBy(ClickEvent::getUserId)
                        .window(TumblingProcessingTimeWindows.of(Time.minutes(1)))
                        .process(new ProcessWindowFunction<ClickEvent, String, String, TimeWindow>() {
                            @Override
                            public void process(
                                    String userId,
                                    Context ctx,
                                    Iterable<ClickEvent> elements,
                                    Collector<String> out
                            ) {
                                long count = 0L;
                                for (ClickEvent e : elements) {
                                    count++;
                                }

                                if (count > ANOMALY_THRESHOLD) {
                                    String msg = String.format(
                                            "ANOMALY user=%s events=%d window=[%d, %d)",
                                            userId,
                                            count,
                                            ctx.window().getStart(),
                                            ctx.window().getEnd()
                                    );
                                    out.collect(msg);
                                }
                            }
                        });

        // Print anomalies to JobManager logs
        anomalies.print().name("anomaly-prints");

        env.execute("Clickstream Anomaly Detection");
    }
}
