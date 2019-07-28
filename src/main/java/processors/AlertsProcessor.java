package processors;

import com.hazelcast.core.Offloadable;
import com.hazelcast.map.AbstractEntryProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Matan on 10/06/2019.
 */
@Component
public class AlertsProcessor extends AbstractEntryProcessor<Long, String> implements Offloadable {
    private Logger logger = LoggerFactory.getLogger(AlertsProcessor.class);

    public AlertsProcessor() {
        super(false);
    }



    @Override
    public Object process(Map.Entry<Long, String> entry) {
        String value = entry.getValue();
        value += " Modified!";
        logger.info("Processor: key=" + entry.getKey() + ", oldValue=" + entry.getValue() + ", newValue=" + value);
        logger.info("SLEEP: " + entry.getKey());
        try { Thread.sleep(7500); } catch (Exception e) { logger.error("Could not sleep!", e); }
        logger.info("Awake: " + entry.getKey());
        entry.setValue(value);
        return null;
    }

    @Override
    public String getExecutorName() {
        return "alerts-exec";
    }
}
