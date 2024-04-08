package ru.ramanpan.minidocmanagement.dao;

import ru.ramanpan.minidocmanagement.entity.Doc;

import java.util.List;

public interface DocDao {
    Long save(Doc doc);

    void update(Doc doc);

    Doc findById(Long id);

    void deleteById(Long id);

    List<Doc> getAllDocs();
}
