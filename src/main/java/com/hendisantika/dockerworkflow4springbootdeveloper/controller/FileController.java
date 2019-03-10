package com.hendisantika.dockerworkflow4springbootdeveloper.controller;

import com.hendisantika.dockerworkflow4springbootdeveloper.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * Project : docker-workflow-4-springboot-developer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-03-11
 * Time: 03:40
 */
@RestController
@RequestMapping("/api")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/file")
    public ResponseEntity uploadFile(@RequestParam("uploadfile") MultipartFile upload) {
        Map<String, String> hasil = new TreeMap<>();
        try {
            fileService.simpan(upload.getOriginalFilename(), upload.getInputStream());
            hasil.put("success", "File tersimpan di " + upload.getOriginalFilename());
            return ResponseEntity.ok(hasil);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            hasil.put("err", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(hasil);
        }
    }

    @GetMapping("/file")
    public List<Map<String, Object>> daftarFile() {
        return fileService.daftarFile();
    }

    @GetMapping("/file/{nama:.+}")
    public ResponseEntity<Object> getFile(@PathVariable String nama) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nama);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(fileService.ambil(nama)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }

    @DeleteMapping("/file/{nama:.+}")
    public void delete(@PathVariable String nama) {
        fileService.hapus(nama);
    }
}

