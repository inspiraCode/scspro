package com.nowgroup.scspro.dao.common;

import com.nowgroup.scspro.dao.hibernate.ItemByNameException;
import com.nowgroup.scspro.dto.NamableDTO;

/**
 * This interface is used by DAOs that serves objects that
 * 
 * @author torredie
 *
 * @param <T>
 */
public interface NamableDAO<T extends NamableDTO> extends BaseDAO<T> {
	T getByName(String name) throws ItemByNameException;
}
