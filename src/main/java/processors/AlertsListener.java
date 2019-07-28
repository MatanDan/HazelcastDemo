package processors;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import config.HazelcastConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Matan on 10/06/2019.
 */
@Component
public class AlertsListener implements EntryAddedListener<Long, String>, EntryRemovedListener<Long, String>, EntryUpdatedListener<Long, String> {
    private Logger logger = LoggerFactory.getLogger(AlertsListener.class);

    @Autowired
    private AlertsProcessor alertsProcessor;

    @Autowired
    private HazelcastConfig hazelcastConfig;

    @PostConstruct
    public void configListener() {
        hazelcastConfig.getAlertsCache().addLocalEntryListener(this);
        logger.info("Listener is on AlertsCache!");
    }

    @Override
    public void entryAdded(EntryEvent<Long, String> event) {
        logger.info("Event is on: " + event.toString());
        hazelcastConfig.getAlertsCache().submitToKey(event.getKey(), alertsProcessor);
    }

    @Override
    public void entryRemoved(EntryEvent<Long, String> event) {

    }

    @Override
    public void entryUpdated(EntryEvent<Long, String> event) {

    }
}
