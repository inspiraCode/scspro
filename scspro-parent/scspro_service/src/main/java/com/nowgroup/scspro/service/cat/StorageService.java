package com.nowgroup.scspro.service.cat;

import com.nowgroup.scspro.dto.cat.Storage;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.BaseService;

public interface StorageService extends BaseService<Storage> {
    State getStateInStorage(int id);
}
