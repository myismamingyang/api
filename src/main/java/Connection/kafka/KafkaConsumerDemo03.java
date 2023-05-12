package Connection.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Author itcast
 * Date 2020/12/24 10:21
 * Desc 演示Kafka消费者-自动提交
 */
public class KafkaConsumerDemo03 {
    public static void main(String[] args) {
        //TODO 0.准备连接参数
        Properties props = new Properties();
        //kafka集群地址
        props.put("bootstrap.servers", "node1:9092");
        //消费者组id,不设置会有默认的
        props.put("group.id", "group2");
        //是否开启自动提交
        props.put("enable.auto.commit", "true");
        //自动提交的时间间隔
        props.put("auto.commit.interval.ms", "1000");
        //偏移量重置规则:
        //earliest:如果有offset提交记录,就从记录位置开始消费,没有则从最早的位置开始消费
        //latest:如果有offset提交记录,就从记录位置开始消费,没有则从最后/新的位置开始消费
        //none:如果有offset提交记录,就从记录位置开始消费,没有则报错
        props.put("auto.offset.reset", "earliest");
        //kv反序列化规则
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //TODO 1.创建Kafka消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //TODO 2.订阅主题
        consumer.subscribe(Arrays.asList("kafkaPartition"));
        //TODO 3.持续消费
        while (true) {
            //TODO 4.获取消息(拉模式)
            ConsumerRecords<String, String> records = consumer.poll(1000);
            //TODO 5.处理消息
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("partiton = %d, offset = %d, key = %s, value = %s%n", record.partition(),record.offset(), record.key(), record.value());
            }
        }
    }
}
//kafka-console-producer.sh --broker-list node1:9092 --topic test
