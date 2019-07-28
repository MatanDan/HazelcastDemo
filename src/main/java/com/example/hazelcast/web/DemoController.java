package com.example.hazelcast.web;

import com.example.hazelcast.config.HazelcastConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Matan on 05/06/2019.
 */
@RestController
public class DemoController {

    private Long countId = 0L;

    @Autowired
    private HazelcastConfig hazelcastConfig;

    @GetMapping(value = "hi")
    public Map<Long, String> hello() {
        return hazelcastConfig.getAlertsCache().getAll(hazelcastConfig.getAlertsCache().keySet());
    }

    @GetMapping(value = "new")
    public Long putNew() {
        countId++;
        hazelcastConfig.getAlertsCache().putAsync(countId, "Alert" + countId.toString());
        return countId;
    }

    @GetMapping(value = "update")
    public Long updateExisting(@RequestParam Long alertId) {
        hazelcastConfig.getAlertsCache().putAsync(alertId, "I am from update!");
        return alertId;
    }

}
