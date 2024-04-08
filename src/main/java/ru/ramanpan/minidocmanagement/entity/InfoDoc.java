package ru.ramanpan.minidocmanagement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "infodoc")
public class InfoDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docid")
    private Doc docId;
    @Column(name = "docintonumber")
    private String docIntoNumber;
    @Column(name = "docexternnumber")
    private String docExternNumber;
    private Date fromDate;
    private Date toDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doc getDocId() {
        return docId;
    }

    public void setDocId(Doc docId) {
        this.docId = docId;
    }

    public String getDocIntoNumber() {
        return docIntoNumber;
    }

    public void setDocIntoNumber(String docIntoNumber) {
        this.docIntoNumber = docIntoNumber;
    }

    public String getDocExternNumber() {
        return docExternNumber;
    }

    public void setDocExternNumber(String docExternNumber) {
        this.docExternNumber = docExternNumber;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

}
