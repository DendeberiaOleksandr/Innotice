package org.innotice.kafka.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class KafkaProperties {

    private String bootstrapServers;
    private String groupId;

    @Value("${org.innotice.kafka.bootstrap-servers}")
    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    @Value("${org.innotice.kafka.group-id:innotice}")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
