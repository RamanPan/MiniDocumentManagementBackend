package ru.ramanpan.minidocmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ramanpan.minidocmanagement.dao.DocDao;
import ru.ramanpan.minidocmanagement.dto.CreateDocDTO;
import ru.ramanpan.minidocmanagement.dto.DocDTO;
import ru.ramanpan.minidocmanagement.entity.Doc;
import ru.ramanpan.minidocmanagement.entity.InfoDoc;
import ru.ramanpan.minidocmanagement.entity.VersionDoc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocService {
    private final DocDao docDao;
    private final InfoDocService infoDocService;
    private final VersionDocService versionDocService;

    private DocDTO mapToDTO(Doc doc) {
        DocDTO docDTO = new DocDTO();
        docDTO.setId(doc.getId());
        docDTO.setInfoDocId(doc.getInfoDoc().getId());
        docDTO.setDocName(doc.getDocName());
        docDTO.setDocInputNumber(doc.getInfoDoc().getDocIntoNumber());
        docDTO.setDateInit(doc.getInfoDoc().getFromDate().toString());
        docDTO.setDocOutputNumber(doc.getInfoDoc().getDocExternNumber());
        docDTO.setAuthor(doc.getAuthor());
        return docDTO;
    }

    public DocService(DocDao docDao, InfoDocService infoDocService, VersionDocService versionDocService) {
        this.docDao = docDao;
        this.infoDocService = infoDocService;
        this.versionDocService = versionDocService;
    }

    @Transactional
    public void createDoc(CreateDocDTO createDocData) {
        Doc doc = new Doc();
        InfoDoc infoDoc = new InfoDoc();
        VersionDoc versionDoc = new VersionDoc();
        MultipartFile multipartFile = createDocData.getFileData();
        if (createDocData.getFileData() != null) {
            try {
                versionDoc.setFileName(multipartFile.getOriginalFilename());
                versionDoc.setFileType(multipartFile.getContentType());
                versionDoc.setFileData(multipartFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        doc.setDocName(createDocData.getDocName());
        doc.setAuthor(createDocData.getAuthor());
        System.out.println(doc);
        doc.setId(saveDoc(doc));
        infoDoc.setDocId(doc);
        infoDoc.setDocIntoNumber(createDocData.getInputNumber());
        infoDoc.setFromDate(new Date());
        infoDoc.setId(infoDocService.saveInfoDoc(infoDoc));
        doc.setInfoDoc(infoDoc);
        docDao.update(doc);
        versionDoc.setAuthor(createDocData.getAuthor());
        versionDoc.setDocId(doc);
        versionDocService.saveVersionDoc(versionDoc);
    }

    @Transactional
    public List<DocDTO> findAllDocs() {
        return docDao.getAllDocs().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional
    public Long saveDoc(Doc doc) {
        return docDao.save(doc);
    }

    @Transactional
    public void update(Doc doc) {
        docDao.update(doc);
    }

    @Transactional
    public void deleteDoc(Long id) {
        docDao.deleteById(id);
    }

    @Transactional
    public Doc findDoc(Long id) {
        return docDao.findById(id);
    }
}
