package com.bzk.radiusserver.service;

import org.tinyradius.packet.AccountingRequest;

import java.net.InetSocketAddress;

public interface IAccountingService {

    void dealwithAccountingInfo(AccountingRequest accountingRequest, InetSocketAddress client);
}
