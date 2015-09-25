package com.nowgroup.scspro.spring.service.prod;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.hibernate.ItemByNameException;
import com.nowgroup.scspro.dao.seq.ReceiptSequenceDAO;
import com.nowgroup.scspro.dao.sys.UserDAO;
import com.nowgroup.scspro.dto.seq.SeqReceipt;
import com.nowgroup.scspro.dto.sys.User;
import com.nowgroup.scspro.service.prod.ReceiptService;

@Service
@Transactional(readOnly = true)
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptSequenceDAO receiptSequenceDAO;

    @Autowired
    private UserDAO userDAOImpl;

    @Transactional(readOnly = false)
    public String getSequence(String storageCode) throws ItemByNameException {
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR) - 2000;
	int doy = now.get(Calendar.DAY_OF_YEAR);
	
	int userId = userId();
	
	SeqReceipt seq = new SeqReceipt();
	seq.setSeqBy(userId);
	
	int seqCounter = receiptSequenceDAO.sequenceByUser(seq);

	return storageCode + userId() + SeqReceipt.RECEIPT_SEQUENCE_PREFIX + year + doy + seqCounter;
    }

    private int userId() throws ItemByNameException {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String name = auth.getName();

	User loggedUser = userDAOImpl.getByName(name);

	return loggedUser.getId();
    }

    public ReceiptSequenceDAO getReceiptSequenceDAO() {
	return receiptSequenceDAO;
    }

    public void setReceiptSequenceDAO(ReceiptSequenceDAO receiptSequenceDAO) {
	this.receiptSequenceDAO = receiptSequenceDAO;
    }

    public UserDAO getUserDAOImpl() {
	return userDAOImpl;
    }

    public void setUserDAOImpl(UserDAO userDAOImpl) {
	this.userDAOImpl = userDAOImpl;
    }

}
