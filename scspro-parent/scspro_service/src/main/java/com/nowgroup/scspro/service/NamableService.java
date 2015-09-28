package com.nowgroup.scspro.service;

import com.nowgroup.scspro.dto.ItemByNameException;
import com.nowgroup.scspro.dto.NamableDTO;

public interface NamableService<T extends NamableDTO> extends BaseService<T> {
    T getByName(String name)  throws ItemByNameException;
}
