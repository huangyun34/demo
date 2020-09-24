package com.my.admin.searchEngine.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.client.impl.ClusterCanalConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

/**
 * 配置canal详解，请参考：https://github.com/alibaba/canal/wiki/QuickStart
 */
@Configuration
public class CanalConfig {
    private static final Logger LOG = LoggerFactory.getLogger(CanalConfig.class);

    /**
     * 链接配置
     * @param username
     * @param password
     * @param cluster
     * @param destination
     * @param server
     * @return
     */
    @Bean
    public CanalConnector canalConnector(@Value("${canal.username}")String username,
                                         @Value("${canal.password}")String password,
                                         @Value("${canal.cluster}")Boolean cluster,
                                         @Value("${canal.destination}")String destination,
                                         @Value("${zookeeper.server}")String server){
        LOG.info("========== " + server + ", " + destination);
        CanalConnector canalConnector;
        if (cluster) {
            canalConnector = CanalConnectors.newClusterConnector(server, destination, username, password);
        } else {
            canalConnector = CanalConnectors.newSingleConnector(new InetSocketAddress(server.split(":")[0], Integer.parseInt(server.split(":")[1])), destination, username, password);
        }
        return canalConnector;
    }
}
