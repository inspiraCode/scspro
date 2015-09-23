package com.nowgroup.scspro.dao.common;

import com.nowgroup.scspro.dto.NamableDTO;

public interface CodableDAO<T extends NamableDTO> extends BaseDAO<T> {
    T getByCode(String code);
}
