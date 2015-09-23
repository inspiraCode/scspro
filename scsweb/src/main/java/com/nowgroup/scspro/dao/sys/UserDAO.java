package com.nowgroup.scspro.dao.sys;

import com.nowgroup.scspro.dao.common.NamableDAO;
import com.nowgroup.scspro.dao.hibernate.ItemByNameException;
import com.nowgroup.scspro.dto.sys.User;

public interface UserDAO extends NamableDAO<User> {
    User getByName(String name) throws ItemByNameException;
}
