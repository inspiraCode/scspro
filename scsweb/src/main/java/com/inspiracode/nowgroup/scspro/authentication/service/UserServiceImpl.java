/**
 * THIS IS A COMMERCIAL PROGRAM PROVIDED FOR INSPIRACODE AND IT'S ASSOCIATES
 * BUILT BY EXTERNAL SOFTWARE PROVIDERS.
 * THE SOFTWARE COMPRISING THIS SYSTEM IS THE PROPERTY OF INSPIRACODE OR ITS
 * LICENSORS.
 *
 * ALL COPYRIGHT, PATENT, TRADE SECRET, AND OTHER INTELLECTUAL PROPERTY RIGHTS
 * IN THE SOFTWARE COMPRISING THIS SYSTEM ARE, AND SHALL REMAIN, THE VALUABLE
 * PROPERTY OF INSPIRACODE OR ITS LICENSORS.
 *
 * USE, DISCLOSURE, OR REPRODUCTION OF THIS SOFTWARE IS STRICTLY PROHIBITED,
 * EXCEPT UNDER WRITTEN LICENSE FROM INSPIRACODE OR ITS LICENSORS.
 *
 * &copy; COPYRIGHT 2015 INSPIRACODE. ALL RIGHTS RESERVED.
 */
package com.inspiracode.nowgroup.scspro.authentication.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.nowgroup.scspro.authentication.domain.Permission;
import com.inspiracode.nowgroup.scspro.authentication.domain.Role;
import com.inspiracode.nowgroup.scspro.authentication.domain.User;
import com.inspiracode.nowgroup.scspro.authentication.domain.dao.PermissionDao;
import com.inspiracode.nowgroup.scspro.authentication.domain.dao.RoleDao;
import com.inspiracode.nowgroup.scspro.authentication.domain.dao.UserDao;
import com.inspiracode.nowgroup.scspro.logger.ScsproLoggeable;
import com.inspiracode.nowgroup.scspro.logger.ScsproLoggeableFactory;

/**
 * User service implementation. Uses the DAO configurations to find the database
 * for user operations.
 * 
 * <B>Revision History:</B>
 * 
 * <PRE>
 * ====================================================================================
 * Date-------- By---------------- Description
 * ------------ --------------------------- -------------------------------------------
 * 03/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
@Service
public class UserServiceImpl implements UserService {
    private static final ScsproLoggeable logger = ScsproLoggeableFactory.getLoggeableInstance(UserServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private MessageDigestPasswordEncoder passwordEncoder;

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * getUserById(java.lang.Long)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public User getUserById(Long id) {
	logger.debug("Getting user by id [" + id + "]");
	User result = userDao.get(id);
	logger.debug("Got [" + result + "] from database.");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * getUserByUserName(java.lang.String)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public User getUserByUserName(String userName) {
	logger.debug("Getting user by user name [" + userName + "]");
	User result = userDao.getUserByUserName(userName);
	logger.debug("Got [" + result + "] from database.");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.authentication.service.UserService#saveUser
     * (com.inspiracode.nowgroup.scspro.authentication.model.domain.User)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User saveUser(User user) {
	logger.debug("About to save user [" + user.getUserName() + "] in database, checking password encryption");
	String unencrypted = user.getPassword();

	if (user.getUserId() == 0) {
	    String encrypted = passwordEncoder.encodePassword(unencrypted, null);
	    user.setPassword(encrypted);
	    logger.debug("No user ID detected, I am supposing new user, encrypted: [" + encrypted + "]");
	} else {
	    User dbUser = userDao.get(user.getUserId());
	    if ("$NO_CHANGED".equals(unencrypted)) {
		user.setPassword(dbUser.getPassword());
		logger.debug("Password did not changed, leaving as it is.");
	    } else {
		String encrypted = passwordEncoder.encodePassword(unencrypted, null);
		user.setPassword(encrypted);
		logger.debug("Password changed, encrypted: [" + encrypted + "]");
	    }
	}

	logger.debug("Saving user in database [" + user + "]");
	userDao.save(user);
	logger.debug("User stored in database, retrieving confirmation details");
	User result = userDao.getUserByUserName(user.getUserName());
	logger.debug("User stored in database, db details: [" + result + "]");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.authentication.service.UserService#deleteUser
     * (com.inspiracode.nowgroup.scspro.authentication.model.domain.User)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUser(User user) {
	logger.debug("About to delete user [" + user.getUserName() + "] from database");
	userDao.delete(user);
	logger.debug("User [" + user.getUserName() + "] removed from database");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * getRoleById(java.lang.Long)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Role getRoleById(Long id) {
	logger.debug("Getting role by id [" + id + "]");
	Role result = roleDao.get(id);
	logger.debug("Got [" + result + "] from database.");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * getRolesByUserName(java.lang.String)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Set<Role> getRolesByUserName(String userName) {
	logger.debug("Getting roles by user name [" + userName + "]");
	Set<Role> result = userDao.getUserByUserName(userName).getRoles();
	logger.debug("Got [" + result + "] from database");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * getRoleByName(java.lang.String)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Role getRoleByName(String roleName) {
	logger.debug("Getting role by role name [" + roleName + "]");
	Role result = roleDao.getRoleByName(roleName);
	logger.debug("Got [" + result + "] from database.");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.authentication.service.UserService#saveRole
     * (com.inspiracode.nowgroup.scspro.authentication.model.domain.Role)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Role saveRole(Role role) {
	logger.debug("Saving role in database [" + role + "]");
	roleDao.save(role);
	logger.debug("Role stored in database, retrieving confirmation details");
	Role result = roleDao.getRoleByName(role.getRoleName());
	logger.debug("Role stored in database, db details: [" + result + "]");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.authentication.service.UserService#deleteRole
     * (com.inspiracode.nowgroup.scspro.authentication.model.domain.Role)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteRole(Role role) {
	logger.debug("About to delete role [" + role.getRoleName() + "] from database");
	roleDao.delete(role);
	logger.debug("Role [" + role.getRoleName() + "] removed from database");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * getPermissionById(java.lang.Long)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Permission getPermissionById(Long id) {
	logger.debug("Getting permission by id [" + id + "]");
	Permission result = permissionDao.get(id);
	logger.debug("Got [" + result + "] from database.");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * getPermissionByName(java.lang.String)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Permission getPermissionByName(String permissionName) {
	logger.debug("Getting permission by permission name [" + permissionName + "]");
	Permission result = permissionDao.getPermissionByName(permissionName);
	logger.debug("Got [" + result + "] from database.");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * savePermission
     * (com.inspiracode.nowgroup.scspro.authentication.model.domain.Permission)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Permission savePermission(Permission permission) {
	logger.debug("Saving permission in database [" + permission + "]");
	permissionDao.save(permission);
	logger.debug("Permission stored in database, retrieving confirmation details");
	Permission result = permissionDao.getPermissionByName(permission.getPermissionName());
	logger.debug("Permission stored in database, db details: [" + result + "]");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * deletePermission
     * (com.inspiracode.nowgroup.scspro.authentication.model.domain.Permission)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deletePermission(Permission permission) {
	logger.debug("About to delete permission [" + permission.getPermissionName() + "] from database");
	permissionDao.delete(permission);
	logger.debug("Permission [" + permission.getPermissionName() + "] removed from database");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * getPermissionsByUserName(java.lang.String)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Set<Permission> getPermissionsByUserName(String userName) {
	logger.debug("Getting permissions by user name [" + userName + "]");
	User user = userDao.getUserByUserName(userName);
	logger.debug("Got user [" + user + "] from database; extracting permissions");

	Set<Permission> result = new HashSet<Permission>();
	for (Role role : user.getRoles()) {
	    for (Permission permission : role.getPermissions()) {
		if (!result.contains(permission))
		    result.add(permission);
	    }
	}

	logger.debug("Extracted permissions: [" + result + "] from user");
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.service.UserService#
     * getPermissionsByRoleName(java.lang.String)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Set<Permission> getPermissionsByRoleName(String roleName) {
	logger.debug("Getting permissions by role name [" + roleName + "]");
	Set<Permission> result = roleDao.getRoleByName(roleName).getPermissions();
	logger.debug("Got [" + result + "] from database");
	return result;
    }

}
