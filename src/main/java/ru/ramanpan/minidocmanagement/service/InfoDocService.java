package ru.ramanpan.minidocmanagement.service;

import org.springframework.stereotype.Service;
import ru.ramanpan.minidocmanagement.dao.InfoDocDao;
import ru.ramanpan.minidocmanagement.dto.RemoveDocDTO;
import ru.ramanpan.minidocmanagement.entity.Doc;
import ru.ramanpan.minidocmanagement.entity.InfoDoc;

import javax.transaction.Transactional;
import java.util.Date;


@Service
public class InfoDocService {
    private final InfoDocDao infoDocDao;

    public InfoDocService(InfoDocDao infoDocDao) {
        this.infoDocDao = infoDocDao;
    }

    @Transactional
    public void removeDoc(Doc doc, RemoveDocDTO removeDocDTO) {
        InfoDoc infoDoc = doc.getInfoDoc();
        infoDoc.setDocExternNumber(removeDocDTO.getDocOutputNumber());
        infoDoc.setToDate(new Date());
        infoDocDao.update(infoDoc);
    }

    @Transactional
    public Long saveInfoDoc(InfoDoc doc) {
        return infoDocDao.save(doc);
    }

    @Transactional
    public void deleteInfoDoc(Long id) {
        infoDocDao.deleteById(id);
    }

    @Transactional
    public InfoDoc findInfoDoc(Long id) {
        return infoDocDao.findById(id);
    }

    @Transactional
    public InfoDoc findInfoDocByDocId(Long docId) {
        return infoDocDao.findByDocId(docId);
    }
}
