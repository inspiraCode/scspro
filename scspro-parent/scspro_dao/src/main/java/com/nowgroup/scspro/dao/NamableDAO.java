package com.nowgroup.scspro.dao;

import com.nowgroup.scspro.dto.ItemByNameException;
import com.nowgroup.scspro.dto.NamableDTO;

/**
 * This interface is used by DAOs that serves objects that
 * has a Name field.
 * @author torredie
 *
 * @param <T>
 */
public interface NamableDAO<T extends NamableDTO> extends BaseDAO<T> {
    /**
     * Retrieve a DTO using its name property.
     * @param name
     * @return
     * @throws ItemByNameException
     */
    T getByName(String name) throws ItemByNameException;
}
