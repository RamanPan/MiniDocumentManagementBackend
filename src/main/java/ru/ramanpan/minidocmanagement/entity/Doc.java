package ru.ramanpan.minidocmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doc")
public class Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "docname")
    private String docName;
    private String author;

    @JsonIgnore
    @OneToOne(mappedBy = "docId")
    private InfoDoc infoDoc;
    @JsonIgnore
    @OneToMany(mappedBy = "docId")
    private List<VersionDoc> versionDocs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public InfoDoc getInfoDoc() {
        return infoDoc;
    }

    public void setInfoDoc(InfoDoc infoDoc) {
        this.infoDoc = infoDoc;
    }

    public List<VersionDoc> getVersionDocs() {
        return versionDocs;
    }

    public void setVersionDocs(List<VersionDoc> versionDocs) {
        this.versionDocs = versionDocs;
    }
}
