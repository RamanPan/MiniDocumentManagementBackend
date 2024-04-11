package ru.ramanpan.minidocmanagement.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateVersionDocDTO {
    private Long id;
    private String author;
    private MultipartFile fileData;
}
