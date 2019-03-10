package com.hendisantika.dockerworkflow4springbootdeveloper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.GitProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * Project : docker-workflow-4-springboot-developer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-03-11
 * Time: 03:41
 */
@RestController
@RequestMapping("/api")
public class HomeController {

    @Value("${upload.location}")
    private String uploadLocation;

    @Autowired
    private Environment environment;
    @Autowired(required = false)
    private GitProperties gitProperties;

    @GetMapping("/host")
    public Map<String, Object> hostInfo(HttpServletRequest request) {
        Map<String, Object> info = new TreeMap<>();
        info.put("Hostname", request.getLocalName());
        info.put("IP Address Local", request.getLocalAddr());
        info.put("Port Local", request.getLocalPort());
        info.put("Active Profiles", environment.getActiveProfiles());
        info.put("Upload Location Configuration", uploadLocation);
        if (uploadLocation != null) {
            info.put("Upload Location Path", new File(uploadLocation).getAbsolutePath());
        }
        if (gitProperties != null) {
            info.put("Git Branch", gitProperties.getBranch());
            info.put("Git Commit ID", gitProperties.getShortCommitId());
            info.put("Git Commit Time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(gitProperties.getCommitTime()));
        }
        return info;
    }

    @GetMapping("/session")
    public Map<String, Object> getSessionVariables(HttpSession session) {
        Map<String, Object> sessionVariables = new TreeMap<>();
        Collections.list(session.getAttributeNames())
                .forEach(name -> {
                    sessionVariables.put(name, session.getAttribute(name));
                });
        return sessionVariables;
    }

    @PostMapping("/session")
    @ResponseStatus(HttpStatus.CREATED)
    public void putSessionVariable(@RequestBody Map<String, String> data, HttpSession session) {
        data.keySet()
                .stream()
                .forEach(key -> session.setAttribute(key, data.get(key)));
    }

    @DeleteMapping("/session/{varname}")
    @ResponseStatus(HttpStatus.OK)
    public void putSessionVariable(@PathVariable String varname, HttpSession session) {
        session.removeAttribute(varname);
    }
}
