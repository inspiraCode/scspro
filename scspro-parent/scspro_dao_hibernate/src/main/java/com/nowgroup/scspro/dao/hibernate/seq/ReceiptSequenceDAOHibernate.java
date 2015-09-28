package com.nowgroup.scspro.dao.hibernate.seq;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.seq.ReceiptSequenceDAO;
import com.nowgroup.scspro.dto.seq.SeqReceipt;

public class ReceiptSequenceDAOHibernate extends BaseHibernateDAO<SeqReceipt> implements ReceiptSequenceDAO {
    public ReceiptSequenceDAOHibernate() {
	super(SeqReceipt.class);
    }

    public int sequenceByUser(SeqReceipt seqReceipt) {
	add(seqReceipt);
	int result = ((Long) getHibernateTemplate().find(QUERY_COUNTER_BY_USER, seqReceipt.getSeqBy()).iterator().next()).intValue();
	if (result == 0)
	    result = 1;
	return result;
    }
}
