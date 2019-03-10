package com.hendisantika.dockerworkflow4springbootdeveloper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import java.io.File;
import java.nio.file.Files;

@SpringBootApplication
@EnableJdbcHttpSession
public class DockerWorkflow4SpringbootDeveloperApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DockerWorkflow4SpringbootDeveloperApplication.class, args);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DockerWorkflow4SpringbootDeveloperApplication.class);

    @Value("${upload.location}")
    private String uploadLocation;

    @Override
    public void run(String... args) throws Exception {
        File uploadFolder = new File(uploadLocation);
        LOGGER.info("Creating upload folder [{}] at [{}]", uploadFolder.toPath(), uploadFolder.getAbsolutePath());
        Files.createDirectories(uploadFolder.toPath());
    }

}
