package Connection.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class myKafkaPartition implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer partitionCount = cluster.partitionCountForTopic(topic);
        int partitionNum = Math.abs(key.hashCode()) % partitionCount;
        return partitionNum;
        //return 0;
    }

    @Override
    public void close() { }

    @Override
    public void configure(Map<String, ?> configs) { }
}
