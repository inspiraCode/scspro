package com.nowgroup.scspro.service.sys;

import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.dto.sys.Storage;
import com.nowgroup.scspro.service.BaseService;

public interface StorageService extends BaseService<Storage> {
    State getStateInStorage(int id);
}
