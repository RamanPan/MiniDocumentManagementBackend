package ru.ramanpan.minidocmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ramanpan.minidocmanagement.dao.VersionDocDao;
import ru.ramanpan.minidocmanagement.dto.NewVersionDocDTO;
import ru.ramanpan.minidocmanagement.entity.Doc;
import ru.ramanpan.minidocmanagement.entity.VersionDoc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class VersionDocService {
    private final VersionDocDao versionDocDao;

    public VersionDocService(VersionDocDao versionDocDao) {
        this.versionDocDao = versionDocDao;
    }

    @Transactional
    public Long saveVersionDoc(NewVersionDocDTO newVersionDocDTO, Doc doc) {
        VersionDoc versionDoc = new VersionDoc();
        MultipartFile multipartFile = newVersionDocDTO.getFileData();
        try {
            if (newVersionDocDTO.getFileData() != null) {
                versionDoc.setFileName(multipartFile.getOriginalFilename());
                versionDoc.setFileType(multipartFile.getContentType());
                versionDoc.setFileData(multipartFile.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        versionDoc.setAuthor(newVersionDocDTO.getAuthor());
        versionDoc.setDocId(doc);
        return versionDocDao.save(versionDoc);
    }

    @Transactional
    public Long saveVersionDoc(VersionDoc versionDoc) {
        return versionDocDao.save(versionDoc);
    }

    @Transactional
    public List<VersionDoc> getVersionDocsByDocId(Long docId) {
        return versionDocDao.findAllByDocId(docId);
    }
}
