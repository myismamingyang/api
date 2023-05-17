package Connection.kafka;

import org.apache.kafka.clients.producer.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class kafkaWordProducer {
    public static void main(String[] args) throws Exception {
        //TODO 0.准备连接参数--不需要记
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092");
        props.put("acks", "all");
        props.put("retries", 2);
        props.put("retries.backoff.ms", 20);
        props.put("buffer.memory", 33554432);
        props.put("batch.size", 16384);
        props.put("linger.ms", 25);
        props.put("max.request.size", 163840);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //TODO 1.创建Kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd=HH:mm:ss");
        //TODO 2.同步发送/推送数据/消息/记录到Kafka
//        for (int i = 0; i < 10; i++) {
//            ProducerRecord<String, String> record = new ProducerRecord<>("test",  3,"mykey",simpleDateFormat.format(date)+ "_value" + i);
//            RecordMetadata metadata = kafkaProducer.send(record).get();
//            System.out.println("消息已经同步发送成功一条:" +
//                    "topic:"+metadata.topic()+
//                    " partition:"+metadata.partition()+
//                    " offset:"+metadata.offset());
//        }
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/source/data/word.txt"));
        String str = null;
        while ((str = bufferedReader.readLine())!=null) {
            ProducerRecord<String, String> record =
                    new ProducerRecord<>("word", 3, "word",str.toString());
            RecordMetadata metadata = kafkaProducer.send(record).get();
            System.out.println("消息已经同步发送成功一条:" +
                    "topic:"+metadata.topic()+
                    " partition:"+metadata.partition()+
                   " offset:"+metadata.offset()+
                    " value:"+ str);
        }

        System.out.println("消息已经同步发送成功");
        kafkaProducer.close();
        bufferedReader.close();
    }
}
//kafka-console-consumer.sh --zookeeper node1:2181  --topic test --from-beginning