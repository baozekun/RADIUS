package com.bzk.radiusserver.service.impl;

import com.bzk.radiusserver.service.IAccountingService;
import org.springframework.stereotype.Service;
import org.tinyradius.packet.AccountingRequest;

import java.net.InetSocketAddress;

@Service
public class AccountingServiceImpl implements IAccountingService {
    @Override
    public void dealwithAccountingInfo(AccountingRequest accountingRequest, InetSocketAddress client) {
        // TODO 解析 accountingRequest，将计费信息放入存储介质
    }
}
