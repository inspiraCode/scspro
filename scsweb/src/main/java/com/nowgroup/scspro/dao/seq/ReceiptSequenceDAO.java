package com.nowgroup.scspro.dao.seq;

import com.nowgroup.scspro.dao.common.BaseDAO;
import com.nowgroup.scspro.dto.seq.SeqReceipt;

public interface ReceiptSequenceDAO extends BaseDAO<SeqReceipt> {
    public final static String QUERY_COUNTER_BY_USER = "select count(seq.id) from SeqReceipt seq where seq.seqBy = ?";
    int sequenceByUser(SeqReceipt seqReceipt);
}
