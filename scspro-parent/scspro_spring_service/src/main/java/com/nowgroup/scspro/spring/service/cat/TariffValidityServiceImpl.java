package com.nowgroup.scspro.spring.service.cat;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.cat.TariffValidityDAO;
import com.nowgroup.scspro.dto.cat.TariffValidity;
import com.nowgroup.scspro.service.cat.TariffValidityService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

public class TariffValidityServiceImpl extends BaseSpringService<TariffValidity> implements TariffValidityService {
    private TariffValidityDAO tariffValidityDAO;

    public TariffValidityDAO getTariffValidityDAO() {
	return tariffValidityDAO;
    }

    public void setTariffValidityDAO(TariffValidityDAO tariffValidityDAO) {
	super.setDaoFactory((BaseDAO<TariffValidity>) tariffValidityDAO);
	this.tariffValidityDAO = tariffValidityDAO;
    }
}
