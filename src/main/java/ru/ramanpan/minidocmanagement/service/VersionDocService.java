package ru.ramanpan.minidocmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ramanpan.minidocmanagement.dao.VersionDocDao;
import ru.ramanpan.minidocmanagement.dto.CreateVersionDocDTO;
import ru.ramanpan.minidocmanagement.dto.VersionDocDTO;
import ru.ramanpan.minidocmanagement.entity.Doc;
import ru.ramanpan.minidocmanagement.entity.VersionDoc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VersionDocService {
    private final VersionDocDao versionDocDao;

    private Integer numberCount;

    private VersionDocDTO mapToDTO(VersionDoc versionDoc) {
        VersionDocDTO versionDocDTO = new VersionDocDTO();
        versionDocDTO.setNumber(numberCount++);
        versionDocDTO.setId(versionDoc.getId());
        versionDocDTO.setDocId(versionDoc.getDocId().getId());
        versionDocDTO.setAuthor(versionDoc.getAuthor());
        versionDocDTO.setFileData(versionDoc.getFileData());
        versionDocDTO.setFileName(versionDoc.getFileName());
        versionDocDTO.setFileType(versionDoc.getFileType());
        return versionDocDTO;
    }

    public VersionDocService(VersionDocDao versionDocDao) {
        this.versionDocDao = versionDocDao;
    }

    @Transactional
    public void saveVersionDoc(CreateVersionDocDTO createVersionDocDTO, Doc doc) {
        VersionDoc versionDoc = new VersionDoc();
        MultipartFile multipartFile = createVersionDocDTO.getFileData();
        try {
            if (createVersionDocDTO.getFileData() != null) {
                versionDoc.setFileName(multipartFile.getOriginalFilename());
                versionDoc.setFileType(multipartFile.getContentType());
                versionDoc.setFileData(multipartFile.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        versionDoc.setAuthor(createVersionDocDTO.getAuthor());
        versionDoc.setDocId(doc);
        versionDocDao.save(versionDoc);
    }

    @Transactional
    public VersionDoc findById(Long id) {
        return versionDocDao.findById(id);
    }

    @Transactional
    public void saveVersionDoc(VersionDoc versionDoc) {
        versionDocDao.save(versionDoc);
    }

    @Transactional
    public List<VersionDocDTO> getVersionDocsByDocId(Long docId) {
        numberCount = 1;
        return versionDocDao.findAllByDocId(docId).stream().map(this::mapToDTO).toList();
    }
}
