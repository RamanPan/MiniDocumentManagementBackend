package ru.ramanpan.minidocmanagement.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.ramanpan.minidocmanagement.dao.InfoDocDao;
import ru.ramanpan.minidocmanagement.entity.InfoDoc;

@Repository
public class InfoDocDaoImpl implements InfoDocDao {
    private final SessionFactory sessionFactory;

    public InfoDocDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(InfoDoc doc) {
        return (Long) sessionFactory.getCurrentSession().save(doc);
    }

    @Override
    public void update(InfoDoc infoDoc) {
        sessionFactory.getCurrentSession().update(infoDoc);
    }

    @Override
    public InfoDoc findById(Long id) {
        return sessionFactory.getCurrentSession().get(InfoDoc.class, id);
    }

    @Override
    public InfoDoc findByDocId(Long docId) {
        return sessionFactory.getCurrentSession().
                createQuery("select i from InfoDoc i where i.docId = :docId", InfoDoc.class).
                setParameter("docId", docId).getResultList().get(0);
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }


}
