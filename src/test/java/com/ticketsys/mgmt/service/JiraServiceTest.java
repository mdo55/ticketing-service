package com.ticketsys.mgmt.service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.atlassian.util.concurrent.Promise;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.net.URI;

public class JiraServiceTest {
    private JiraRestClient jiraRestClient;
    @Ignore
    @Before
    public void setUp() {
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        try{
            URI uri = new URI("https://mdoss.atlassian.net");
//            final URI jiraServerUri = new URI("https://mdoss.atlassian.net/projects/TIC/issues/TIC-1");
            jiraRestClient = factory.createWithBasicHttpAuthentication(uri, "mdoss@altimetrik.com", "Y7Qf6JgQhqfoe6XYCmY3D256");
        }catch (Exception ex) {
//            log.error("Exceptin in jiraRestClient initialization exception: "+ex);
            ex.printStackTrace();
        }
    }
    @Ignore
    @Test
    public void displayProject() {
        Project project = jiraRestClient.getProjectClient().getProject("TIC").claim();
        System.out.println(project);
        jiraRestClient.getUserClient().getUser("");
//        self=https://mdoss.atlassian.net/rest/api/2/user?accountId=5e5613253df51b0c93756bf1
//            URI basicSelf = new URI("https://mdoss.atlassian.net/rest/api/3/user?accountId=5e5613253df51b0c93756bf1");
//            Promise<User> pUser = jiraRestClient.getUserClient().getUser("5e5613253df51b0c93756bf1");
    }
    @Ignore
    @Test
    public void displayProperties() throws Exception {
        Priority priority = jiraRestClient.getMetadataClient().getPriority(new URI("https://mdoss.atlassian.net/rest/api/2/priority/5")).claim();
        System.out.println(priority.getSelf());
//        Promise<Iterable<Priority>> priorities = jiraRestClient.getMetadataClient().getPriorities();
//        priorities.get().forEach(fi -> System.out.println("fields are: "+fi.toString()));
//        Promise<IssueType> pIssueType = jiraRestClient.getMetadataClient().getIssueType(new URI("https://mdoss.atlassian.net/rest/api/3/issue/10001"));
//        System.out.println(pIssueType.get().getDescription());
//        Issue issue = jiraRestClient.getIssueClient().getIssue("TIC-2").claim();
//        System.out.println(issue.getPriority());
//        File file = new File("D:\\magesh\\mag\\info\\certificates\\certificate\\UC-f8387038-d468-41cb-b19c-913010ec76ca.jpg");
//        Promise<Void> addFile = jiraRestClient.getIssueClient().addAttachments(new URI("https://mdoss.atlassian.net/rest/api/3/issue/TIC-2/attachments"), file);
//        System.out.println(addFile.claim());

//        String jiraVersion = jiraRestClient.getMetadataClient()
//                .getServerInfo().claim()
//                .getVersion();
//        System.out.println(jiraVersion);
    }
    @Ignore
    @Test
    public void attachment() throws  Exception {
//        HttpResponse<JsonNode> response = Unirest.post("https://mdoss.atlassian.net/rest/api/3/issue/TIC-2/attachments")
        HttpResponse<JsonNode> response = Unirest.get("https://mdoss.atlassian.net/rest/api/3/issue/TIC-1")
                .basicAuth("mdoss@altimetrik.com", "Y7Qf6JgQhqfoe6XYCmY3D256")
                .header("Accept", "application/json")
                .asJson();
        System.out.println(response.getBody());
    }
    @Ignore
    @Test
    public void user() throws Exception{
        HttpResponse<JsonNode> response = Unirest.get("https://mdoss.atlassian.net/rest/api/3/user?accountId=5e5613253df51b0c93756bf1")
                .basicAuth("mdoss@altimetrik.com", "Y7Qf6JgQhqfoe6XYCmY3D256")
                .header("Accept", "application/json")
                .asJson();
        System.out.println(response.getBody());
    }
    @Ignore
    @Test
    public void attachmentHttpClientTest() throws Exception{
        File file = new File("D:\\magesh\\mag\\info\\certificates\\certificate\\UC-f8387038-d468-41cb-b19c-913010ec76ca.jpg");
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postReq = new HttpPost("https://mdoss.atlassian.net/rest/api/3/issue/TIC-2/attachments");
        Base64 code = new Base64();
        String encode = new String (code.encode("mdoss@altimetrik.com:Y7Qf6JgQhqfoe6XYCmY3D256".getBytes()));
        System.out.println("encode: "+encode);
        postReq.setHeader("Authorization", "Basic "+encode);
        postReq.setHeader("X-Atlassian-Token", "nocheck");
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
//        entityBuilder.addPart("file", new FileBody(file));
        entityBuilder.addBinaryBody("file", new byte[0]);
        postReq.setEntity(entityBuilder.build());
        org.apache.http.HttpResponse response = httpClient.execute(postReq);
        System.out.println(response);
    }
    @Test
    public void splitTest() {
        String content = "data:image/png;base64,hellow how are you";
        String[] arr = content.split(",");
        System.out.println(arr[1]);
        System.out.println(arr[0].split(";")[0].split("/")[1]);
    }
}
