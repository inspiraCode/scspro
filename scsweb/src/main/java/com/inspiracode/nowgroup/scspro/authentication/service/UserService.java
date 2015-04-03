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

import java.util.Set;

import com.inspiracode.nowgroup.scspro.authentication.domain.Permission;
import com.inspiracode.nowgroup.scspro.authentication.domain.Role;
import com.inspiracode.nowgroup.scspro.authentication.domain.User;

/**
 * All database authentication records are handled by this service implementation.
 * User related records include users, roles and permissions.
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
public interface UserService {
    /**
     * Get user and details by Id
     * @param id
     * @return
     */
    User getUserById(Long id);
    /**
     * Get user and details by user name
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);
    /**
     * Add or update User in database.
     * @param user
     * @return
     */
    User saveUser(User user);
    /**
     * Remove user record from database.
     * @param user
     */
    void deleteUser(User user);
    
    /**
     * Get role record and its permissions using a role id.
     * @param id
     * @return
     */
    Role getRoleById(Long id);
    /**
     * Get a role Set using a user name.
     * @param userName
     * @return Roles assigned to the given user
     */
    Set<Role> getRolesByUserName(String userName);
    /**
     * Get role record and its permissions using a role name.
     * @param roleName
     * @return
     */
    Role getRoleByName(String roleName);
    /**
     * Add or updata a Role record in database.
     * @param role
     * @return
     */
    Role saveRole(Role role);
    /**
     * Delete a role record from database.
     * @param role
     */
    void deleteRole(Role role);

    /**
     * Get a permission record using its id in database.
     * @param id
     * @return
     */
    Permission getPermissionById(Long id);
    /**
     * Get a permission record using its name
     * @param permissionName
     * @return
     */
    Permission getPermissionByName(String permissionName);
    /**
     * Add or update a permission record in database.
     * @param permission
     * @return
     */
    Permission savePermission(Permission permission);
    /**
     * Remove a permission record from database.
     * @param permission
     */
    void deletePermission(Permission permission);
    /**
     * Get a permission's set using a user name.
     * @param userName
     * @return The assigned permissions to the given user.
     */
    Set<Permission> getPermissionsByUserName(String userName);
    /**
     * Get a permission's set using a role name.
     * @param roleName
     * @return The assigned permissions to the given role.
     */
    Set<Permission> getPermissionsByRoleName(String roleName);
    
}
