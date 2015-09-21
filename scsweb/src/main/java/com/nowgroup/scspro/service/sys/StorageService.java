package com.nowgroup.scspro.service.sys;

import java.util.List;

import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.dto.sys.Storage;

public interface StorageService {
	void addStorage(Storage storage);

	void deleteStorage(Storage storage);

	void updateStorage(Storage storage);

	Storage getStorageById(int id);

	State getStateInStorage(int id);

	List<Storage> getStorages();

}
