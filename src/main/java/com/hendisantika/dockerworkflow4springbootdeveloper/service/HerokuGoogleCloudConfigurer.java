package com.hendisantika.dockerworkflow4springbootdeveloper.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;

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
@Profile("heroku")
public class HerokuGoogleCloudConfigurer {
    @Value("${GOOGLECLOUD_AUTH_CONTENT}")
    private String googleCredentialFileContent;

    @PostConstruct
    public void writeEnvironmentVariableToFile() {
        try (FileWriter writer = new FileWriter("google-credential.json")) {
            writer.write(googleCredentialFileContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}