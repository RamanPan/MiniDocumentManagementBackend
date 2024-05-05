package ru.ramanpan.minidocmanagement.dto;

import lombok.Data;

@Data
public class DocDTO {
    private Long id;

    private Long infoDocId;

    private String docName;

    private String author;

    private String docInputNumber;

    private String docOutputNumber;

    private String dateInit;

    private String dateDeRegistration;
}
