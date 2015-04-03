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

import com.inspiracode.nowgroup.scspro.authentication.domain.Permission;
import com.inspiracode.nowgroup.scspro.authentication.domain.dao.PermissionDao;
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
@Repository("permissionDao")
public class PermissionDaoHibernate extends ScsproHibernateSupport<Permission> implements PermissionDao {

    public PermissionDaoHibernate() {
	super(Permission.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.authentication.domain.dao.PermissionDao
     * #getPermissionByName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Permission getPermissionByName(String permissionName) throws EntityNotFoundException {
	String queryString = "select permission from Permission permission where permission.permissionName = :permissionName";
	List<Permission> roleList = (List<Permission>) this.getHibernateTemplate().findByNamedParam(
			queryString, "permissionName", permissionName);
	if (roleList == null || roleList.isEmpty()){
		String errorMessage = String.format("Unable to find permission with name: %s", permissionName);
		logger.warn(errorMessage);
		throw new EntityNotFoundException(errorMessage);
	}
	logger.info(String.format("Retrieved permission information [%s]", permissionName));
	return roleList.get(0);
    }

}
