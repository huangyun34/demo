package com.my.admin.aoplog;

import com.github.LogData;
import com.github.collector.LogCollector;
import com.my.admin.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AopLogCollector implements LogCollector {
    private static final Logger LOGGER = LoggerFactory.getLogger(AopLogCollector.class);
    @Override
    public void collect(LogData logData) {
        LOGGER.info(JsonUtils.toJson(logData));
    }
}
