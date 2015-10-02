package com.nowgroup.scspro.dao.hibernate.sys;

import java.util.LinkedList;
import java.util.List;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.MeasurementUnitDAO;
import com.nowgroup.scspro.dto.sys.MeasurementUnit;

public class MeasurementUnitDAOHibernate extends BaseHibernateDAO<MeasurementUnit> implements MeasurementUnitDAO {

    public MeasurementUnitDAOHibernate() {
	super(MeasurementUnit.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MeasurementUnit> getMeasurementUnitByUsage(String usage) {
	List<Integer> find = (List<Integer>) getHibernateTemplate().find(QUERY_BY_USAGE, usage);
	List<MeasurementUnit> result = new LinkedList<MeasurementUnit>();
	for (int iUnit : find) {
	    MeasurementUnit unit = get(iUnit);
	    result.add(unit);
	}
	return result;
    }
}
