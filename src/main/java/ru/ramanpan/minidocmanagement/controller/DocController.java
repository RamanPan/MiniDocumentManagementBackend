package ru.ramanpan.minidocmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ramanpan.minidocmanagement.dto.CreateDocDTO;
import ru.ramanpan.minidocmanagement.dto.DocDTO;
import ru.ramanpan.minidocmanagement.dto.NewVersionDocDTO;
import ru.ramanpan.minidocmanagement.dto.RemoveDocDTO;
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
    public ResponseEntity.BodyBuilder createDoc(@ModelAttribute CreateDocDTO createDocData) {
        docService.createDoc(createDocData);
        return ResponseEntity.ok();
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeDoc(@RequestBody RemoveDocDTO removeDocDTO) {
        infoDocService.removeDoc(docService.findDoc(removeDocDTO.getId()),removeDocDTO);
        return ResponseEntity.ok(removeDocDTO.getDocOutputNumber());
    }

    @PostMapping("/newversiondoc")
    public ResponseEntity.BodyBuilder newVersionDoc(@ModelAttribute NewVersionDocDTO newVersionDocDTO) {
        versionDocService.saveVersionDoc(newVersionDocDTO, docService.findDoc(newVersionDocDTO.getId()));
        return ResponseEntity.ok();
    }

    @GetMapping("/versiondoc/{id}")
    public ResponseEntity<List<VersionDoc>> getAllVersionDocByDocId(@PathVariable("id") Long docId) {
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
}
