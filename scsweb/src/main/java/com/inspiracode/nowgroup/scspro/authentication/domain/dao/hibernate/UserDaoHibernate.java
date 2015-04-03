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

import com.inspiracode.nowgroup.scspro.authentication.domain.User;
import com.inspiracode.nowgroup.scspro.authentication.domain.dao.UserDao;
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
@Repository("userDao")
public class UserDaoHibernate extends ScsproHibernateSupport<User> implements UserDao {

    public UserDaoHibernate() {
	super(User.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.authentication.domain.dao.UserDao#
     * getUserByUserName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public User getUserByUserName(String userName) throws EntityNotFoundException {
	String queryString = "select usr from User usr where usr.userName = :userName";
	List<User> userList = (List<User>) this.getHibernateTemplate().findByNamedParam(queryString, "userName", userName);
	if (userList == null || userList.isEmpty()) {
	    String errorMessage = String.format("Unable to find user with name: %s", userName);
	    logger.warn(errorMessage);
	    throw new EntityNotFoundException(errorMessage);
	}
	logger.info(String.format("Retrieved user information [%s]", userName));
	return userList.get(0);
    }

}
