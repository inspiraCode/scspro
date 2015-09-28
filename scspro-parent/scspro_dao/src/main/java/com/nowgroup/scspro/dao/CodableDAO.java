package com.nowgroup.scspro.dao;

import com.nowgroup.scspro.dto.NamableDTO;

public interface CodableDAO<T extends NamableDTO> extends BaseDAO<T> {
    /**
     * Retrieve DTO using the code property.
     * Only applies to those DTOs which contains a Code field.
     * @param code
     * @return
     */
    T getByCode(String code);
}
