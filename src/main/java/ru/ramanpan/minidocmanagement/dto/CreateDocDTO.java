package ru.ramanpan.minidocmanagement.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class CreateDocDTO implements Serializable {
    private String docName;
    private String author;
    private String inputNumber;
    private MultipartFile fileData;
}
