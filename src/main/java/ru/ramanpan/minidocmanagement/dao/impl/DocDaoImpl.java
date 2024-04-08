package ru.ramanpan.minidocmanagement.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.ramanpan.minidocmanagement.dao.DocDao;
import ru.ramanpan.minidocmanagement.entity.Doc;
import ru.ramanpan.minidocmanagement.utils.HibernateUtils;

import java.util.List;

@Repository
public class DocDaoImpl implements DocDao {
    private final SessionFactory sessionFactory;

    public DocDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(Doc doc) {
        return (Long) sessionFactory.getCurrentSession().save(doc);
    }

    @Override
    public void update(Doc doc) {
        sessionFactory.getCurrentSession().update(doc);
    }

    @Override
    public Doc findById(Long id) {
        return sessionFactory.getCurrentSession().get(Doc.class, id);
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public List<Doc> getAllDocs() {
        return HibernateUtils.loadAllData(Doc.class, sessionFactory.getCurrentSession());
    }
}
