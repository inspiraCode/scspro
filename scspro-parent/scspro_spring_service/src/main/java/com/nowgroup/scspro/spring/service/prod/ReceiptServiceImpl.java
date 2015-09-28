package com.nowgroup.scspro.spring.service.prod;

import java.util.Calendar;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.prod.ReceiptDAO;
import com.nowgroup.scspro.dao.seq.ReceiptSequenceDAO;
import com.nowgroup.scspro.dao.sys.UserDAO;
import com.nowgroup.scspro.dto.ItemByNameException;
import com.nowgroup.scspro.dto.prod.Receipt;
import com.nowgroup.scspro.dto.seq.SeqReceipt;
import com.nowgroup.scspro.dto.sys.User;
import com.nowgroup.scspro.service.prod.ReceiptService;
import com.nowgroup.scspro.spring.service.BaseHibernateIndexableService;

@Transactional(readOnly = true)
public class ReceiptServiceImpl extends BaseHibernateIndexableService<Receipt> implements ReceiptService {

    private ReceiptDAO receiptDAO;
    private ReceiptSequenceDAO sequenceDAO;
    private UserDAO userDAO;

    @Transactional(readOnly = false)
    public String getSequence(String storageCode) throws ItemByNameException {
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR) - 2000;
	int doy = now.get(Calendar.DAY_OF_YEAR);

	int userId = userId();

	SeqReceipt seq = new SeqReceipt();
	seq.setSeqBy(userId);

	int seqCounter = getSequenceDAO().sequenceByUser(seq);

	return storageCode + userId() + SeqReceipt.RECEIPT_SEQUENCE_PREFIX + year + doy + String.format("%03d", seqCounter);
    }

    private int userId() throws ItemByNameException {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String name = auth.getName();

	User loggedUser = getUserDAO().getByName(name);

	return loggedUser.getId();
    }

    public ReceiptDAO getReceiptDAO() {
	return receiptDAO;
    }

    public void setReceiptDAO(ReceiptDAO receiptDAO) {
	super.setDaoFactory((BaseDAO<Receipt>) receiptDAO);
	this.receiptDAO = receiptDAO;
    }

    public ReceiptSequenceDAO getSequenceDAO() {
	return sequenceDAO;
    }

    public void setSequenceDAO(ReceiptSequenceDAO sequenceDAO) {
	this.sequenceDAO = sequenceDAO;
    }

    public UserDAO getUserDAO() {
	return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
	this.userDAO = userDAO;
    }
}
