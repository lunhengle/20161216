package com.lhl.utils20161216.bean.hi;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.List;

/**
 * Created by lunhengle on 2017/1/4.
 * 分页功能
 */
public class PageTemplate {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Page getPage(final String hql, final Object[] params, final int currentPage, final int pageSize) {
        List listCount = hibernateTemplate.find(hql, params);
        int totalCount = listCount.size();
        Page page = new Page();
        //当前多少页
        page.setCurrentPage(currentPage);
        //每页多少条
        page.setPageSize(pageSize);
        //一共多少条
        page.setTotalCount(totalCount);
        //一共多少页
        page.setTotalPage((totalCount / currentPage + (totalCount % currentPage > 1 ? 1 : 0)));
        List list = hibernateTemplate.execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                for (int i = 0; null != params && i < params.length; i++) {
                    query.setParameter(i, params[i]);
                }
                query.setFirstResult((currentPage - 1) * pageSize);
                query.setMaxResults(pageSize);
                return query.list();
            }
        });
        page.setList(list);
        return page;
    }
}
