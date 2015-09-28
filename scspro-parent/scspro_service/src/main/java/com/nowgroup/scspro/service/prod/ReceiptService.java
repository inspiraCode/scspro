package com.nowgroup.scspro.service.prod;

import com.nowgroup.scspro.dto.ItemByNameException;
import com.nowgroup.scspro.dto.prod.Receipt;
import com.nowgroup.scspro.service.BaseService;

public interface ReceiptService extends BaseService<Receipt> {
    String getSequence(String storageCode) throws ItemByNameException;
}
