package services;

import config.HazelcastConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Matan on 16/06/2019.
 */
@Service
public class BenchmarkService {

    @Autowired
    private HazelcastConfig hazelcastConfig;

    @PostConstruct
    public void OverloadCaches() {
        for (int i = 1; i <= 5; i++) {
            hazelcastConfig.getAlertsCache().putAsync(new Long(i), "Alert" + i);
        }
    }
}
