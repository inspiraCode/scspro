package com.nowgroup.scspro.service.prod;

import com.nowgroup.scspro.dto.prod.ReceiptFreight;
import com.nowgroup.scspro.service.BaseService;

public interface ReceiptFreightService extends BaseService<ReceiptFreight> {
    boolean isGuideDuplicated(int freighterId, String guide, int freightId);
}
