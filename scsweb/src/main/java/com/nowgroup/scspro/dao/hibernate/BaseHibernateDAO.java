package com.nowgroup.scspro.dao.hibernate;

import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.nowgroup.scspro.dao.common.BaseDAO;
import com.nowgroup.scspro.dto.BaseDTO;

public abstract class BaseHibernateDAO<T extends BaseDTO> extends HibernateDaoSupport implements BaseDAO<T> {
    private Class<T> type;

    public BaseHibernateDAO(Class<T> type) {
	super();
	this.type = type;
    }

    public T get(int id) {
	return getHibernateTemplate().get(type, id);
    }

    public List<T> getAll() {
	return getHibernateTemplate().loadAll(type);
    }

    public int add(T object) {
	getHibernateTemplate().save(object);
	getHibernateTemplate().flush();
	return object.getId();
    }

    public void update(T object) {
	getHibernateTemplate().update(object);
	getHibernateTemplate().flush();
    }

    public void delete(T object) {
	getHibernateTemplate().delete(object);
	getHibernateTemplate().flush();
    }

    public void indexEntity(T object) {
	FullTextSession fullTextSession = Search.getFullTextSession(this.getSessionFactory().getCurrentSession());
	ScrollableResults results = fullTextSession.createCriteria(this.type).scroll(ScrollMode.FORWARD_ONLY);
	int counter = 0, numItemsInGroup = 10;
	while (results.next()) {
	    fullTextSession.index(results.get(0));
	    if (counter++ % numItemsInGroup == 0) {
		fullTextSession.flushToIndexes();
		fullTextSession.clear();
	    }
	}
    }

    protected Session conn() {
	return getSessionFactory().getCurrentSession();
    }

    @Autowired
    public void setupSessionFactory(SessionFactory sessionFactory) {
	this.setSessionFactory(sessionFactory);
    }

}
