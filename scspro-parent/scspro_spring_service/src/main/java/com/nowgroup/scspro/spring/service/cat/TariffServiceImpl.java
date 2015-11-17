package com.nowgroup.scspro.spring.service.cat;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.cat.TariffDAO;
import com.nowgroup.scspro.dto.cat.Tariff;
import com.nowgroup.scspro.service.cat.TariffService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

public class TariffServiceImpl extends BaseSpringService<Tariff> implements TariffService {
    private TariffDAO tariffDAO;

    public TariffDAO getTariffDAO() {
	return tariffDAO;
    }

    public void setTariffDAO(TariffDAO tariffDAO) {
	super.setDaoFactory((BaseDAO<Tariff>) tariffDAO);
	this.tariffDAO = tariffDAO;
    }
}
