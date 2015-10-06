package com.nowgroup.scspro.dao.prod;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dto.prod.ReceiptFreight;

public interface ReceiptFreightDAO extends BaseDAO<ReceiptFreight> {
    public final static String QUERY_BY_GUIDE = "from ReceiptFreight freight join freight.freighter freighter WHERE freighter.id=? AND freight.guide=? AND not freight.id=?";

    boolean isGuideDuplicated(int freighterId, String guide, int freightId);
}
