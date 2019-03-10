package com.hendisantika.dockerworkflow4springbootdeveloper.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : docker-workflow-4-springboot-developer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-03-11
 * Time: 03:35
 */
public interface FileService {
    void simpan(String nama, InputStream contentStream);

    List<Map<String, Object>> daftarFile();

    void hapus(String nama);

    InputStream ambil(String nama);
}
