package ru.ramanpan.minidocmanagement.dao;

import ru.ramanpan.minidocmanagement.entity.VersionDoc;

import java.util.List;

public interface VersionDocDao {
    Long save(VersionDoc versionDoc);

    VersionDoc findById(Long id);

    List<VersionDoc> findAllByDocId(Long docId);

    void deleteById(Long id);
}
