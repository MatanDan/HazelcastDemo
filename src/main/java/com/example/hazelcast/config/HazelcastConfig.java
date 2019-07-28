package com.example.hazelcast.config;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.IMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matan on 10/06/2019.
 */
@Component
public class HazelcastConfig {
    private HazelcastInstance hazelcastInstance;

    @PostConstruct
    public void initCaches() {
        Config config = new Config();

        // Hazelcast configuration
        config.setInstanceName("MatanNA");
        Map<String, ExecutorConfig> executors = new HashMap<>();
        executors.put("alerts-exec", new ExecutorConfig().setPoolSize(1000).setQueueCapacity(0));
        config.setExecutorConfigs(executors);

        ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig();
        managementCenterConfig.setEnabled(true);
        managementCenterConfig.setUrl("http://localhost:8081/hazelcast-mancenter");
        config.setManagementCenterConfig(managementCenterConfig);

        hazelcastInstance = Hazelcast.newHazelcastInstance(config);
    }

    public IMap<Long, String> getAlertsCache() {
        return hazelcastInstance.getMap("alerts");
    }

    public IExecutorService getAlertsExecutor() {
        return hazelcastInstance.getExecutorService("alerts-exec");
    }
}
