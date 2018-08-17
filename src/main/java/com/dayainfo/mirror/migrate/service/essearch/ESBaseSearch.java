package com.dayainfo.mirror.migrate.service.essearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.stream.Stream;
import com.dayainfo.modules.utils.NumberUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public abstract class ESBaseSearch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ESBaseSearch.class);
    private TransportClient client;
    @Value("${es.cluster.name}")
    private String ES_ClusterName;
    @Value("${es.ip}")
    private String ES_IP;

    protected synchronized TransportClient getClient() {
        if (client != null) {
            return client;
        }

        client = new PreBuiltTransportClient(Settings.builder()
                                                     .put("cluster.name", ES_ClusterName)
                                                     .put("client.transport.sniff", true)
                                                     .build());
        String[] ips = ES_IP.split(",");
        if (ips.length == 0) {
            throw new IllegalArgumentException("es.ip config error");
        }
        Stream.of(ips).map(ip -> ip.split(":")).filter(f -> f.length == 2).map(f -> {
            try {
                return new TransportAddress(InetAddress.getByName(f[0]), NumberUtils.toInt(f[1]));
            } catch (UnknownHostException e) {
                LOGGER.error("parse es.ip error", e);
                return null;
            }
        }).filter(Objects::nonNull).forEach(f -> client.addTransportAddress(f));
        return client;
    }
    
    public synchronized void destroy() {
        if (client != null) {
            client.close();
            client = null;
        }
    }
}
