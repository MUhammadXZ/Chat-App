package com.example.Chat.App;
import static org.assertj.core.api.Assertions.assertThat;


import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "test-topic" }, brokerProperties = {"log.dirs=target/embedded-kafka-logs"})
public class KafkaIntegrationTest {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Test
    public void testKafkaIntegration() throws InterruptedException {
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(bootstrapServers);
        ProducerFactory<String, String> pf = new DefaultKafkaProducerFactory<>(producerProps);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(pf);
        template.setDefaultTopic("test-topic");

        template.sendDefault("key", "value");

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", bootstrapServers);
        consumerProps.put("key.deserializer", StringDeserializer.class);
        consumerProps.put("value.deserializer", StringDeserializer.class);
        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(cf.createConsumer(), "test-topic");

        assertThat(record.value()).isEqualTo("value");
    }
}

