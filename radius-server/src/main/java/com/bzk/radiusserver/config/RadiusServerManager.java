package com.bzk.radiusserver.config;


import com.bzk.radiusserver.server.MyRadiusServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class RadiusServerManager {

    @Value("${radius.listenAuth}")
    boolean listenAuth;
    @Value("${radius.listenAcct}")
    boolean listenAcct;

    @Autowired
    MyRadiusServer radiusService;

    @PostConstruct
    public void init() {
        radiusService.start(listenAuth,listenAcct); // 应用启动时，启动RADIUS服务
    }

    @PreDestroy
    public void cleanup() {
        radiusService.stop(); // 应用退出时，停止RADIUS服务
    }
}
