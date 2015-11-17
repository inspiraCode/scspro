package com.nowgroup.scspro.dao.hibernate.cat;

import com.nowgroup.scspro.dao.cat.MaterialDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.Material;

public class MaterialDAOHibernate extends BaseHibernateDAO<Material> implements MaterialDAO {
    public MaterialDAOHibernate() {
	super(Material.class);
    }
}
