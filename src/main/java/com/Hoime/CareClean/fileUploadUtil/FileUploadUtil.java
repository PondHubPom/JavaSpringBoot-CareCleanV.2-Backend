package com.Hoime.CareClean.fileUploadUtil;

import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

    public void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        try {
            Path copyLocation = Paths.get(uploadDir);
            Files.copy(multipartFile.getInputStream(),copyLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new IOException("File Not Found");
        }
    }
}