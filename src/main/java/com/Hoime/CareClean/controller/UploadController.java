package com.Hoime.CareClean.controller;

import com.Hoime.CareClean.model.domain.*;
import com.Hoime.CareClean.model.wrapper.*;
import com.Hoime.CareClean.fileUploadUtil.FileUploadUtil;
import com.Hoime.CareClean.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/upload")
public class UploadController {
//    @PostMapping(value = "/upload-menu", consumes = "multipart/form-data")
//    public UtilDomain uploadMenu(
//            @RequestParam("file") MultipartFile file,
//            HttpServletRequest req) throws Exception {
//
////        String id = req.getAttribute("id").toString();
//
//
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//
//        String uploadDir =  (Math.random()) + ".jpg";
//
//        new FileUploadUtil().saveFile(uploadDir, fileName, file);
//
//        return null;
//
//    }

    @GetMapping(value = "/image/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable(required = false) String filename) throws IOException {
        // ตรวจสอบว่า filename ไม่เป็น null หรือว่าง
        if (filename == null || filename.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // กำหนด path ของไฟล์รูปภาพ
        String imagePath = "./" + filename; // เปลี่ยนเส้นทางไปที่ไดเรกทอรีที่เก็บรูปภาพของคุณ

        // อ่านไฟล์รูปภาพเป็น byte array
        Path path = Paths.get(imagePath);
        byte[] imageBytes = Files.readAllBytes(path);

        // สร้าง ResponseEntity และตอบกลับไปที่ client
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }
}
