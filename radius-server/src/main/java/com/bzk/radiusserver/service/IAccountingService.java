package com.bzk.radiusserver.service;

import org.tinyradius.packet.AccountingRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusException;

import java.net.InetSocketAddress;

public interface IAccountingService {

    RadiusPacket dealwithAccountingInfo(AccountingRequest accountingRequest, InetSocketAddress client) throws RadiusException;
}
