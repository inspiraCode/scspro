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
package com.inspiracode.nowgroup.scspro.domain.dao.hibernate.common;

import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.inspiracode.nowgroup.scspro.domain.common.Dominable;
import com.inspiracode.nowgroup.scspro.domain.dao.common.BaseDao;

/**
 * Implements the basic functionality common for all Hibernate implementations.
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
public abstract class ScsproHibernateSupport<T extends Dominable> extends HibernateDaoSupport implements BaseDao<T> {
    private Class<T> type;

    public ScsproHibernateSupport(Class<T> type) {
	super();
	this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.domain.dao.common.BaseDao#get(java.lang
     * .Long)
     */
    @Override
    public T get(Long id) {
	return getHibernateTemplate().get(type, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.domain.dao.common.BaseDao#getAll()
     */
    @Override
    public List<T> getAll() {
	return getHibernateTemplate().loadAll(type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.inspiracode.nowgroup.scspro.domain.dao.common.BaseDao#save(com.
     * inspiracode.nowgroup.scspro.domain.common.Dominable)
     */
    @Override
    public void save(T object) {
	getHibernateTemplate().save(object);
	getHibernateTemplate().flush();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inspiracode.nowgroup.scspro.domain.dao.common.BaseDao#delete(com.
     * inspiracode.nowgroup.scspro.domain.common.Dominable)
     */
    @Override
    public void delete(T object) {
	getHibernateTemplate().delete(object);
	getHibernateTemplate().flush();
    }

    public void indexEntity(T object) {
	FullTextSession fullTextSession = Search.getFullTextSession(this.getSessionFactory().getCurrentSession());
	ScrollableResults results = fullTextSession.createCriteria(this.type).scroll(ScrollMode.FORWARD_ONLY);
	int counter = 0, numIntemsInGroup = 10;
	while (results.next()) {
	    fullTextSession.index(results.get(0));
	    if (counter++ % numIntemsInGroup == 0) {
		fullTextSession.flushToIndexes();
		fullTextSession.clear();
	    }
	}
    }

    @Autowired
    public void setupSessionFactory(SessionFactory sessionFactory) {
	this.setSessionFactory(sessionFactory);
    }
}