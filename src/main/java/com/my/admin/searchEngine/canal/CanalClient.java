package com.my.admin.searchEngine.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CanalClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CanalClient.class);

    @Autowired
    private CanalConnector canalConnector;

    @Value("${canal.table_filter}")
    private String tableFilter;

    @PostConstruct
    public void init() {
        Assert.notNull(canalConnector, "connector is null");
        new Thread(() -> {
            this.process();
        }).start();
    }

    private void process() {
        int batchSize = 5 * 1024;
        try {
            canalConnector.connect();
            canalConnector.subscribe(tableFilter);
            while (true) {
                Message message = canalConnector.get(batchSize, 1l, TimeUnit.SECONDS);
                if (!CollectionUtils.isEmpty(message.getEntries())) {
                    for (CanalEntry.Entry entry : message.getEntries()) {
                        if (entry.getEntryType().equals(CanalEntry.EntryType.ROWDATA)) {
                            String tableName = entry.getHeader().getTableName();
                            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                            if (rowChange.getEventType().equals(CanalEntry.EventType.INSERT)) {
                                List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
                                for (CanalEntry.RowData rowData : rowDataList) {
                                    List<CanalEntry.Column> columns = rowData.getAfterColumnsList();
                                    for (CanalEntry.Column column : columns) {
                                        if (column.getIsKey()) {
                                            System.out.println(tableName + "表新增id" + column.getValue());
                                        }
                                    }
                                }
                            } else if (rowChange.getEventType().equals(CanalEntry.EventType.UPDATE)) {
                                List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
                                for (CanalEntry.RowData rowData : rowDataList) {
                                    List<CanalEntry.Column> columns = rowData.getAfterColumnsList();
                                    for (CanalEntry.Column column : columns) {
                                        if (column.getIsKey()) {
                                            System.out.println(tableName + "表更新id" + column.getValue());
                                        }
                                    }
                                }
                            } else if (rowChange.getEventType().equals(CanalEntry.EventType.DELETE)) {
                                List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
                                for (CanalEntry.RowData rowData : rowDataList) {
                                    List<CanalEntry.Column> columns = rowData.getBeforeColumnsList();
                                    for (CanalEntry.Column column : columns) {
                                        if (column.getIsKey()) {
                                            System.out.println(tableName + "删除id" + column.getValue());
                                        }
                                    }
                                }
                            }
                        }
//                        LOGGER.info(entry.toString());
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("canal出错", e);
        } finally {
            try {
                canalConnector.disconnect();
            } catch (Exception e) {
                LOGGER.error("杀死canal出错", e);
            }
        }
    }
}
