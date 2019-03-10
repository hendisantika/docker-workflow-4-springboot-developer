package com.hendisantika.dockerworkflow4springbootdeveloper.service;

import com.google.cloud.Page;
import com.google.cloud.storage.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
 * Time: 03:36
 */
@Component
@Profile("gcloudstorage")
public class GoogleCloudStorage implements FileService {

    private static final String BUCKET_NAME = "belajar-ci";
    private Storage storage;

    @Override
    public void simpan(String nama, InputStream contentStream) {
        getStorage().create(BlobInfo.newBuilder(BUCKET_NAME, nama).build(),
                contentStream);
    }

    @Override
    public List<Map<String, Object>> daftarFile() {
        List<Map<String, Object>> hasil = new ArrayList<>();
        Page<Blob> daftarFile = getStorage().list(BUCKET_NAME, Storage.BlobListOption.currentDirectory());
        daftarFile.getValues().forEach(blob -> {
            Map<String, Object> fileInfo = new TreeMap<>();
            fileInfo.put("nama", blob.getName());
            fileInfo.put("ukuran", blob.getSize());
            hasil.add(fileInfo);
        });
        return hasil;
    }

    @Override
    public void hapus(String nama) {
        getStorage().delete(BlobId.of(BUCKET_NAME, nama));
    }

    @Override
    public InputStream ambil(String nama) {
        return new ByteArrayInputStream(
                getStorage().readAllBytes(BlobId.of(BUCKET_NAME, nama)));
    }

    private Storage getStorage() {
        if (storage == null) {
            storage = StorageOptions.getDefaultInstance().getService();
        }
        return storage;
    }
}
