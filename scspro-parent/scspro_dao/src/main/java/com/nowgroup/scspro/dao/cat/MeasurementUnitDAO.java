package com.nowgroup.scspro.dao.cat;

import java.util.List;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dto.cat.MeasurementUnit;

public interface MeasurementUnitDAO extends BaseDAO<MeasurementUnit> {
    public final static String QUERY_BY_USAGE = "select mu.id from MeasurementUnit mu join mu.roles mur where mur.name = ?";

    List<MeasurementUnit> getMeasurementUnitByUsage(String usage);
}
