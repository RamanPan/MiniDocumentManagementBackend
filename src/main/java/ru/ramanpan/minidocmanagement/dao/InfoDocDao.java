package ru.ramanpan.minidocmanagement.dao;

import ru.ramanpan.minidocmanagement.entity.InfoDoc;

public interface InfoDocDao {
    Long save(InfoDoc infoDoc);

    void update(InfoDoc infoDoc);
    InfoDoc findById(Long id);
    InfoDoc findByDocId(Long docId);

    void deleteById(Long id);
}
