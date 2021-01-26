//
//package com.river.common.config;
//
//import com.google.common.collect.Maps;
//import lombok.Data;
//import lombok.experimental.Accessors;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//
//import java.io.Serializable;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Properties;
//
///**
// * The union config entity for kafka producer and consumer
// *
// *
// * @version 1.0.0, 2018-05-25 14:33
// * @since 1.0.0, 2018-05-25 14:33
// */
//@Data
//@Accessors(chain = true)
//public class KafkaConfig implements Serializable {
//    private static final long serialVersionUID = -8540901102584845405L;
//
//    private String topic;
//
//    private String brokerList;
//
//    private String groupId;
//
//    private Map<String, String> properties = Maps.newHashMap();
//
//    private int parallelism = 1;
//
//    private boolean enable = true;
//
//
//    /**
//     * Flink use kafka java client
//     */
//    public Properties createProductProperties() {
//        Objects.requireNonNull(brokerList, "bootstrap.servers" + " couldn't be null");
//        Properties producerProperties = new Properties();
//        producerProperties.put(ProducerConfig.ACKS_CONFIG, "1");
//        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
//        producerProperties.putAll(properties);
//        return producerProperties;
//    }
//
//    public Properties createConsumerProperties() {
//        Objects.requireNonNull(brokerList, "bootstrap.servers" + " couldn't be null");
//        Objects.requireNonNull(groupId, "group id couldn't be null");
//
//        Properties consumerProperties = new Properties();
//        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
//        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        consumerProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
//        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        consumerProperties.putAll(properties);
//        return consumerProperties;
//    }
//}
