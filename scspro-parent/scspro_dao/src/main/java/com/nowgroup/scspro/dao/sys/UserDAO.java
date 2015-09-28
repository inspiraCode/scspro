package com.nowgroup.scspro.dao.sys;

import com.nowgroup.scspro.dao.NamableDAO;
import com.nowgroup.scspro.dto.ItemByNameException;
import com.nowgroup.scspro.dto.sys.User;

public interface UserDAO extends NamableDAO<User> {
    User getByName(String name) throws ItemByNameException;
}
