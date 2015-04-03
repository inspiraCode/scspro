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
package com.inspiracode.nowgroup.scspro.authentication.authority;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.inspiracode.nowgroup.scspro.authentication.domain.Permission;
import com.inspiracode.nowgroup.scspro.authentication.domain.Role;
import com.inspiracode.nowgroup.scspro.authentication.domain.User;
import com.inspiracode.nowgroup.scspro.authentication.service.UserService;
import com.inspiracode.nowgroup.scspro.logger.ScsproLoggeable;
import com.inspiracode.nowgroup.scspro.logger.ScsproLoggeableFactory;

/**
 * Implements the service description for the authentication user details.
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
@Repository
public class ScsproUserDetailsService implements UserDetailsService {
    private static final ScsproLoggeable logger = ScsproLoggeableFactory.getLoggeableInstance(ScsproUserDetailsService.class.getName());

    @Autowired
    private UserService userService;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	logger.info(String.format("ScsproUserDetailsService.loadUserByUsername() = %s", userName));
	User user = userService.getUserByUserName(userName);
	logger.info(String.format("AberatyUserDetailService.loadUserByUsername() = %s", user));
	List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

	for (Role role : user.getRoles()) {
	    for (Permission permission : role.getPermissions()) {
		grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
	    }
	}

	UserDetails userDetails = new ScsproUserDetails(user, grantedAuthorities);
	return userDetails;
    }

}
