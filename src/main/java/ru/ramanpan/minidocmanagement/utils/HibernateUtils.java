package ru.ramanpan.minidocmanagement.utils;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateUtils {
    private HibernateUtils() {
    }

    public static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return session.createQuery(criteria).getResultList();
    }

    public static <T, U> List<T> loadAllDataByParam(Class<T> type, Session session, String field, U value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> root = criteria.from(type);
        criteria.where(builder.equal(root.get(field), value));
        return session.createQuery(criteria).getResultList();
    }
}
