package org.framework.rodolfo.freire.git.asuna.cloud.kafka.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConfigurationConsumer {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private String autoCommitInterval;
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;
    @Value("${spring.kafka.consumer.max-poll-records}")
    private String maxPollRecords;
    @Value("${spring.kafka.consumer.fetch-min-size}")
    private String minBytes;
    @Value("${spring.kafka.consumer.fetch-max-wait}")
    private String maxTime;
    @Value("${spring.kafka.consumer.fetch-max-bytes}")
    private String maxBytes;
    @Value("${spring.kafka.consumer.request-timeout-ms}")
    private String timeOut;
    @Value("${spring.kafka.consumer.retry-backoff-ms}")
    private int retryBackoffMs;
    @Value("${spring.kafka.consumer.max-partition-fetch-bytes}")
    private String maxPartitionFetchBytes;
    @Value("${spring.kafka.consumer.max-poll-interval}")
    private String maxPollInterval;

    public Map<String, Object> consumerJsonConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.autoOffsetReset);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, this.maxPollRecords);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, this.maxPollInterval);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, this.enableAutoCommit);
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, this.maxBytes);
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, this.timeOut);
        props.put(ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, this.retryBackoffMs);
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, this.maxTime);
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, this.maxPartitionFetchBytes);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    public ConsumerFactory<String, String> consumerJsonFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerJsonConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(String.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerJsonFactory());
        factory.setConcurrency(1);
        factory.setCommonErrorHandler(
                new DefaultErrorHandler(
                        recover(),
                        new FixedBackOff(0, 2L)
                )
        );
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }

    private Map<String, Object> producerJsonConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "60000");
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, this.retryBackoffMs);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    private DeadLetterPublishingRecoverer recover() {
        return new DeadLetterPublishingRecoverer(getEventKafkaTemplate(),
                (record, ex) -> new TopicPartition("", -1));
    }

    private KafkaOperations<String, String> getEventKafkaTemplate() { // producer to DLQ
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerJsonConfigs()));
    }

}
