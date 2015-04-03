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
package com.inspiracode.nowgroup.scspro.authentication.domain.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import com.inspiracode.nowgroup.scspro.authentication.domain.Role;
import com.inspiracode.nowgroup.scspro.authentication.domain.dao.RoleDao;
import com.inspiracode.nowgroup.scspro.domain.dao.hibernate.common.ScsproHibernateSupport;

/**
 * USAGE HERE
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
@Repository("roleDao")
public class RoleDaoHibernate extends ScsproHibernateSupport<Role> implements RoleDao {

    public RoleDaoHibernate() {
	super(Role.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.domain.dao.RoleDao#
     * getRoleByName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Role getRoleByName(String roleName) throws EntityNotFoundException {
	String queryString = "select role from Role role where role.roleName = :roleName";
	List<Role> roleList = (List<Role>) this.getHibernateTemplate().findByNamedParam(queryString, "roleName", roleName);
	if (roleList == null || roleList.isEmpty()) {
	    String errorMessage = String.format("Unable to find role with name: %s", roleName);
	    logger.warn(errorMessage);
	    throw new EntityNotFoundException(errorMessage);
	}
	logger.info(String.format("Retrieved role information [%s]", roleName));
	return roleList.get(0);
    }

}
