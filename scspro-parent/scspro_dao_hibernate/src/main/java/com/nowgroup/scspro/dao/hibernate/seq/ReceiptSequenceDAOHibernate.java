package com.nowgroup.scspro.dao.hibernate.seq;

import java.util.Calendar;
import java.util.Date;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.seq.ReceiptSequenceDAO;
import com.nowgroup.scspro.dto.seq.SeqReceipt;

public class ReceiptSequenceDAOHibernate extends BaseHibernateDAO<SeqReceipt> implements ReceiptSequenceDAO {
    public ReceiptSequenceDAOHibernate() {
	super(SeqReceipt.class);
    }

    public int sequenceByUser(SeqReceipt seqReceipt) {
	add(seqReceipt);
	Calendar date = Calendar.getInstance();
	date.set(Calendar.HOUR_OF_DAY, 0);
	date.set(Calendar.MINUTE, 0);
	date.set(Calendar.SECOND, 0);
	date.set(Calendar.MILLISECOND, 0);

	Date today = date.getTime();
	date.add(Calendar.DATE, 1);
	Date tomorrow = date.getTime();

	int result = ((Long) getHibernateTemplate().find(QUERY_COUNTER_BY_USER, seqReceipt.getSeqBy(), today, tomorrow).iterator().next()).intValue();
	if (result == 0)
	    result = 1;
	return result;
    }
}
