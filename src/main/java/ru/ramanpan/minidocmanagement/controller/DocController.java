package ru.ramanpan.minidocmanagement.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ramanpan.minidocmanagement.dto.*;
import ru.ramanpan.minidocmanagement.entity.Doc;
import ru.ramanpan.minidocmanagement.entity.VersionDoc;
import ru.ramanpan.minidocmanagement.service.DocService;
import ru.ramanpan.minidocmanagement.service.InfoDocService;
import ru.ramanpan.minidocmanagement.service.VersionDocService;

import java.util.List;

@Controller
@RequestMapping("/api/doc")
public class DocController {

    private final DocService docService;

    private final InfoDocService infoDocService;

    private final VersionDocService versionDocService;

    public DocController(DocService docService, InfoDocService infoDocService, VersionDocService versionDocService) {
        this.docService = docService;
        this.infoDocService = infoDocService;
        this.versionDocService = versionDocService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDoc(@ModelAttribute CreateDocDTO createDocData) {
        docService.createDoc(createDocData);
        return ResponseEntity.ok(HttpHeaders.ACCEPT);
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeDoc(@RequestBody RemoveDocDTO removeDocDTO) {
        infoDocService.removeDoc(docService.findDoc(removeDocDTO.getId()), removeDocDTO);
        return ResponseEntity.ok(removeDocDTO.getDocOutputNumber());
    }

    @PostMapping("/newversiondoc")
    public ResponseEntity<String> newVersionDoc(@ModelAttribute CreateVersionDocDTO createVersionDocDTO) {
        versionDocService.saveVersionDoc(createVersionDocDTO, docService.findDoc(createVersionDocDTO.getId()));
        return ResponseEntity.ok(HttpHeaders.ACCEPT);
    }

    @GetMapping("/versiondoc/{id}")
    public ResponseEntity<List<VersionDocDTO>> getAllVersionDocByDocId(@PathVariable("id") Long docId) {
        return ResponseEntity.ok(versionDocService.getVersionDocsByDocId(docId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doc> findDoc(@PathVariable("id") Long id) {
        return ResponseEntity.ok(docService.findDoc(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<DocDTO>> findAllDocs() {
        return ResponseEntity.ok(docService.findAllDocs());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity.BodyBuilder deleteDoc(@PathVariable("id") Long id) {
        docService.deleteDoc(id);
        return ResponseEntity.ok();
    }

    @GetMapping("/versiondocfile/{id}")
    public ResponseEntity<byte[]> getFileByVersionDocId(@PathVariable("id") Long docId) {
        VersionDoc versionDoc = versionDocService.findById(docId);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(versionDoc.getFileType()));
        header.setContentLength(versionDoc.getFileData().length);
        header.set("Content-Disposition", "attachment; filename=" +
                versionDoc.getFileName());
        return new ResponseEntity<>(versionDoc.getFileData(), header, HttpStatus.OK);
    }
}
