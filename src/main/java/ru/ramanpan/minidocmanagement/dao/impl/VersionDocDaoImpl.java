package ru.ramanpan.minidocmanagement.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.ramanpan.minidocmanagement.dao.VersionDocDao;
import ru.ramanpan.minidocmanagement.entity.VersionDoc;
import ru.ramanpan.minidocmanagement.utils.HibernateUtils;

import java.util.List;

@Repository
public class VersionDocDaoImpl implements VersionDocDao {
    private final SessionFactory sessionFactory;

    public VersionDocDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(VersionDoc doc) {
        return (Long) sessionFactory.getCurrentSession().save(doc);
    }

    @Override
    public VersionDoc findById(Long id) {
        return sessionFactory.getCurrentSession().get(VersionDoc.class, id);
    }

    @Override
    public List<VersionDoc> findAllByDocId(Long docId) {
        return HibernateUtils.loadAllDataByParam(VersionDoc.class, sessionFactory.getCurrentSession(), "docId", docId);
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }
}
