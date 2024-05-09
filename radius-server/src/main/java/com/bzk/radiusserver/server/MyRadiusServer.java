package com.bzk.radiusserver.server;

import com.bzk.radiusserver.service.IAccountingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.AccountingRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusException;
import org.tinyradius.util.RadiusServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyRadiusServer extends RadiusServer {

    @Autowired
    IAccountingService accountingService;



    @Override
    public String getSharedSecret(InetSocketAddress inetSocketAddress) {
        return "choahvbdvhieuvhvhofaihi";
    }

    @Override
    public String getUserPassword(String s) {
        // mapper.selectPasswordByUserName
        return "21424324132523523";
    }

    @Override
    public void start(boolean listenAuth, boolean listenAcct){
        //初始化一个线程池，用于处理大批量UDP请求接收
        executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors()*2,
                Runtime.getRuntime().availableProcessors()*2,
                0,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(100));
        super.start(listenAuth,listenAcct);
    }

    @Override
    public void stop() {
        super.stop();
    }

    /**
     * 访问请求接收
     * @param accessRequest
     * @param client
     * @return
     * @throws RadiusException
     */
    @Override
    public RadiusPacket accessRequestReceived(AccessRequest accessRequest, InetSocketAddress client) throws RadiusException {
        log.info("lalallallaall");
        return super.accessRequestReceived(accessRequest, client);
    }

    /**
     * 计费请求接收
     * @param accountingRequest
     * @param client
     * @return
     * @throws RadiusException
     */
    @Override
    public RadiusPacket accountingRequestReceived(AccountingRequest accountingRequest, InetSocketAddress client) throws RadiusException {
        accountingService.dealwithAccountingInfo(accountingRequest,client);
        return super.accountingRequestReceived(accountingRequest,client);
    }
}
