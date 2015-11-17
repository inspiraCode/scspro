package com.nowgroup.scspro.spring.service.cat;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.cat.MaterialDAO;
import com.nowgroup.scspro.dto.cat.Material;
import com.nowgroup.scspro.service.cat.MaterialService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

public class MaterialServiceImpl extends BaseSpringService<Material> implements MaterialService {
    private MaterialDAO materialDAO;

    public MaterialDAO getMaterialDAO() {
	return materialDAO;
    }

    public void setMaterialDAO(MaterialDAO materialDAO) {
	super.setDaoFactory((BaseDAO<Material>) materialDAO);
	this.materialDAO = materialDAO;
    }
}
