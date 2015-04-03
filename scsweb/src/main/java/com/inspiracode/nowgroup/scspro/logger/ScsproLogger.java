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
package com.inspiracode.nowgroup.scspro.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * log4j logger implementation. Used by ScsproLoggeableFactory to generate an
 * instance for log management.
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
public class ScsproLogger implements ScsproLoggeable {
    private transient final Logger logger;

    public ScsproLogger(String className) {
	System.getProperty("java.util.logging.manager", "org.apache.loggin.log4j.jul.LogManager");
	logger = LogManager.getLogger(className);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.logger.ScsproLoggeable#log(int,
     * java.lang.String)
     */
    @Override
    public void log(int level, String message) {
	message = logger.getName() + ": " + message;
	switch (level) {
	case 1:
	    logger.trace(message);
	    break;
	case 2:
	    logger.info(message);
	    break;
	case 3:
	    logger.debug(message);
	    break;
	case 4:
	    logger.warn(message);
	    break;
	case 5:
	    logger.error(message);
	    break;
	default:
	    logger.warn("No Logger Level Found");
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.logger.ScsproLoggeable#log(int,
     * java.lang.String, java.lang.Throwable)
     */
    @Override
    public void log(int level, String message, Throwable thrower) {
	message = logger.getName() + ": " + message;
	switch (level) {
	case 1:
	    logger.trace(message, thrower);
	    break;
	case 2:
	    logger.info(message, thrower);
	    break;
	case 3:
	    logger.debug(message, thrower);
	    break;
	case 4:
	    logger.warn(message, thrower);
	    break;
	case 5:
	    logger.error(message, thrower);
	    break;
	default:
	    logger.warn("No Logger Level Found");
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.logger.ScsproLoggeable#debug(java.lang
     * .String)
     */
    @Override
    public void debug(String message) {
	logger.debug(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.logger.ScsproLoggeable#info(java.lang
     * .String)
     */
    @Override
    public void info(String message) {
	logger.info(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.logger.ScsproLoggeable#warn(java.lang
     * .String)
     */
    @Override
    public void warn(String message) {
	logger.warn(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.logger.ScsproLoggeable#error(java.lang
     * .String, java.lang.Throwable)
     */
    @Override
    public void error(String message, Throwable thrower) {
	logger.error(message, thrower);
    }
}
