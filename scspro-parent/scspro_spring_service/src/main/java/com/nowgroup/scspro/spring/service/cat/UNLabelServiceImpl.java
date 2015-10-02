package com.nowgroup.scspro.spring.service.cat;

import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.cat.UNLabelDAO;
import com.nowgroup.scspro.dto.cat.UNLabel;
import com.nowgroup.scspro.service.cat.UNLabelService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class UNLabelServiceImpl extends BaseSpringService<UNLabel> implements UNLabelService {
    private UNLabelDAO unDAO;

    public UNLabelDAO getUnDAO() {
	return unDAO;
    }

    public void setUnDAO(UNLabelDAO unDAO) {
	super.setDaoFactory((BaseDAO<UNLabel>) unDAO);
	this.unDAO = unDAO;
    }
}
