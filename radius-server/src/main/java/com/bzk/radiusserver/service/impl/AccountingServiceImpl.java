package com.bzk.radiusserver.service.impl;

import com.bzk.radiusserver.service.IAccountingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tinyradius.packet.AccountingRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusException;

import java.net.InetSocketAddress;

@Slf4j
@Service
public class AccountingServiceImpl implements IAccountingService {
    @Override
    public RadiusPacket dealwithAccountingInfo(AccountingRequest accountingRequest, InetSocketAddress client) {
        RadiusPacket answer = null;
        try{
            int acctStatusType = accountingRequest.getAcctStatusType();
            switch (acctStatusType) {
                case AccountingRequest.ACCT_STATUS_TYPE_START:
                    answer = doStart(accountingRequest);
                    break;
                case AccountingRequest.ACCT_STATUS_TYPE_INTERIM_UPDATE:
                    answer = doUpdate(accountingRequest);
                    break;
                case AccountingRequest.ACCT_STATUS_TYPE_STOP:
                    answer = doStop(accountingRequest);
                    break;
                default:
                    answer = new RadiusPacket(RadiusPacket.ACCOUNTING_STATUS,accountingRequest.getPacketIdentifier());
            }
        } catch (RadiusException e) {
            log.warn("dealwithAccountingInfo -> RadiusException: ",e);
            answer = new RadiusPacket(RadiusPacket.ACCOUNTING_STATUS,accountingRequest.getPacketIdentifier());
        } catch (Exception e) {
            log.warn("dealwithAccountingInfo -> Exception: ",e);
            answer = new RadiusPacket(RadiusPacket.UNDEFINED,accountingRequest.getPacketIdentifier());
        }
        return answer;
    }

    private RadiusPacket doStop(AccountingRequest accountingRequest) {
        return null;
    }

    private RadiusPacket doUpdate(AccountingRequest accountingRequest) {
        return null;
    }

    private RadiusPacket doStart(AccountingRequest accountingRequest) {
        return null;
    }
}
