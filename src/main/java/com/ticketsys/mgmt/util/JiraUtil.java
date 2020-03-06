package com.ticketsys.mgmt.util;

import com.ticketsys.mgmt.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author mdoss
 */
@Slf4j
@Service
public class JiraUtil {

    @Autowired(required = true)
    private Config config;

    public void attachmentInIssueThroughHttpClient(File file, String issueKey) {
        log.info("attachmentInIssueThroughHttpClient method called...");
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            StringBuilder urlBuilder = new StringBuilder(config.getDomainUrl());
            urlBuilder.append(config.getIssueUrl()).append(issueKey).append("/attachments");
            HttpPost postReq = new HttpPost(urlBuilder.toString());
            Base64 code = new Base64();
            StringBuilder userTokenBuilder = new StringBuilder(config.getJiraUser());
            userTokenBuilder.append(":").append(config.getJiraApiToken());
            String encode = new String(code.encode(userTokenBuilder.toString().getBytes()));

            postReq.setHeader("Authorization", "Basic " + encode);
            postReq.setHeader("X-Atlassian-Token", "nocheck");
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.addPart("file", new FileBody(file));
            postReq.setEntity(entityBuilder.build());
            org.apache.http.HttpResponse response = httpClient.execute(postReq);
//            System.out.println("jira.user: " + config.getJiraUser());
            boolean isDelete = Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
            System.out.println("deleteIfExists: "+isDelete);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("attachmentInIssueThroughHttpClient method call...end");
    }
}
