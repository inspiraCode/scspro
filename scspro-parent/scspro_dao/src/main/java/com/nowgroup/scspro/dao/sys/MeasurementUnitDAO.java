package com.nowgroup.scspro.dao.sys;

import java.util.List;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dto.sys.MeasurementUnit;

public interface MeasurementUnitDAO extends BaseDAO<MeasurementUnit> {
    public final static String QUERY_BY_USAGE = "select mu.id from MeasurementUnit mu join mu.roles mur where mur.name = ?";

    List<MeasurementUnit> getMeasurementUnitByUsage(String usage);
}
