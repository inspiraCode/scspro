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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.inspiracode.nowgroup.scspro.authentication.domain.User;
import com.inspiracode.nowgroup.scspro.logger.ScsproLoggeable;
import com.inspiracode.nowgroup.scspro.logger.ScsproLoggeableFactory;

/**
 * Authentication success handler for Supply Chain Software pro.<br/>
 * Used in spring/security-context.xml authenticationSuccessHandler beanId.
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
public class ScsproAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements AuthenticationSuccessHandler {
    private static final ScsproLoggeable logger = ScsproLoggeableFactory.getLoggeableInstance(ScsproAuthenticationSuccessHandler.class.getName());
    private static final String DEFAULT_TARGET = "/program.do";

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.web.authentication.AuthenticationSuccessHandler
     * #onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
	    ServletException {
	logger.debug("AuthenticationSuccessHandler.onAuthenticationSuccess()");
	User user = ((ScsproUserDetails) authentication.getPrincipal()).getUser();

	request.getSession().setAttribute("LOGGED_IN_USER", user);

	logger.debug("Current user is attached to the session.");
	logger.debug(String.format("Roles: %s", user.getRoles()));
	logger.debug("User successfully Logged in");

	setDefaultTargetUrl(DEFAULT_TARGET);
	setAlwaysUseDefaultTargetUrl(true);
	handle(request, response, authentication);
	postProcessRequest(request, user);
    }

    protected void postProcessRequest(HttpServletRequest request, User user) {
	HttpSession session = request.getSession(false);
	if (session == null) {
	    logger.warn("Current user is NOT attached to the session.");
	    return;
	}
	session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
