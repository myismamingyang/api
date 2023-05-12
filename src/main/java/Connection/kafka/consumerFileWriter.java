package Connection.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class consumerFileWriter {
    public static void main(String[] args) throws IOException {
        // 配置 Kafka 消费者属性
        Properties props = new Properties();
        props.put("bootstrap.servers", "node1:9092");
        props.put("group.id", "grouptest");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto,offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("kafkaToMysql"));
        System.out.println("消费 Kafka 消息并写入本地文件");
        FileWriter fw = new FileWriter("E:\\opt\\develop\\java\\javaProjects\\QQ110_Projects\\api\\src\\main\\out\\kafkaToMysql_demo.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("将消息写入文件");
                System.out.printf("partiton = %d, offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());

                String message = String.format("partiton = %d, offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
                bw.write(message);
                bw.flush();
            }
        }
    }
}

