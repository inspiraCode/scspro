package com.nowgroup.scspro.service.prod;

import com.nowgroup.scspro.dao.hibernate.ItemByNameException;

public interface ReceiptService {
    String getSequence(String storageCode) throws ItemByNameException;
}
