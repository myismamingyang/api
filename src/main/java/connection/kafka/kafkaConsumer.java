package connection.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class kafkaConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node2:9092");
        props.put("group.id", "grouptest");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        //props.put("auto,offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer0 = new KafkaConsumer<>(props);

        consumer0.subscribe(Arrays.asList("kafkaToMysql"));
        while (true) {
            ConsumerRecords<String, String> records = consumer0.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf(
                        "partiton = %d, offset = %d, key = %s, value = %s%n"
                        , record.partition()
                        , record.offset()
                        , record.key()
                        , record.value());
            }
        }
    }
}
