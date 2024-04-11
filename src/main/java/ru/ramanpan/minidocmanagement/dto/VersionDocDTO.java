package ru.ramanpan.minidocmanagement.dto;

import lombok.Data;

@Data
public class VersionDocDTO {
    private Long docId;
    private Integer number;
    private String author;
    private String fileName;
    private String fileType;
    private byte[] fileData;
}
