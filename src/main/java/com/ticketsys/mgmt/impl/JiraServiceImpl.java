package com.ticketsys.mgmt.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.ticketsys.mgmt.config.Config;
import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;
import com.ticketsys.mgmt.service.TicketService;
import com.ticketsys.mgmt.util.Base64Util;
import com.ticketsys.mgmt.util.JiraUtil;
import com.ticketsys.mgmt.util.MapperUtil;
import io.atlassian.util.concurrent.Promise;
import com.ticketsys.mgmt.domain.AppIssue;
import com.ticketsys.mgmt.service.JiraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Jira service implementaion, Jira activities like create, update issue performed.
 * @author mdoss.
 */
@Slf4j
@Component
public class JiraServiceImpl implements JiraService {

    @Autowired(required = true)
    private JiraRestClient jiraRestClient;

    @Autowired(required = true)
    private TicketService ticketService;

    @Autowired(required = true)
    private JiraUtil jiraUtil;

    @Autowired(required = true)
    private Config config;

    /**
     * create Issue in Jira
     * @param appIssue the issue to be created
     * @return basicIssue.
     * @throws URISyntaxException
     */
    @Override
    public BasicIssue createIssueInJira(AppIssue appIssue) throws URISyntaxException {
        log.info("call createIssueInJira method...");
        try {
            Project project = jiraRestClient.getProjectClient().getProject("TIC").claim();
            IssueType issueType = jiraRestClient.getMetadataClient().getIssueType(new URI(config.getDomainUrl() + config.getIssueTypeUrl() + appIssue.getIssueTypeId())).claim();
            Priority priority = jiraRestClient.getMetadataClient().getPriority(new URI(config.getDomainUrl() + config.getPriorityUrl() + appIssue.getPriorityId())).claim();
//            IssueInputBuilder issueBuilder = new IssueInputBuilder("TIC", 10005l);
            IssueInputBuilder issueBuilder = new IssueInputBuilder(project, issueType);
            issueBuilder.setDescription(appIssue.getDescription());
            issueBuilder.setSummary(appIssue.getSummary());
            issueBuilder.setPriority(priority);
            IssueInput issueInput = issueBuilder.build();
            BasicIssue bIssue = jiraRestClient.getIssueClient().createIssue(issueInput).claim();
            if(appIssue.isFileAttached()) {
                updateIssueInJira(bIssue.getKey(), appIssue.getTicketId());
            }
            TicketInfoResponse response = ticketService.findById(appIssue.getTicketId());
            TicketInfoRequest ticketInfoRequest = MapperUtil.getInstance().mapTicketResponseToRequest(response);
            String issueInfo = MapperUtil.getInstance().issueInfoInJsonFormat(project.getKey(), bIssue.getKey());
            ticketInfoRequest.setJiraIssueInfo(issueInfo);
            ticketInfoRequest.setCreateIssueInJira(true);
            ticketService.updateTicket(ticketInfoRequest);
        }catch (Exception ex) {
            log.error("Exception in createIssueInJira method: ",ex);
        }
        log.info("call createIssueInJira method...end");
        return null;
    }

    @Override
    public List<Field> getAllField() {
        try {
            Promise<Iterable<Field>> fields = jiraRestClient.getMetadataClient().getFields();
//            fields.get().forEach(fi -> System.out.println("fields are: "+fi.toString()));
            //map(field -> field.forEach( fi -> System.out.println(fi.toString())));
            Promise<Iterable<IssueType>> issueTypes = jiraRestClient.getMetadataClient().getIssueTypes();
//            issueTypes.get().forEach(issueType -> System.out.println("issueType: "+issueType.toString()));
            createIssueInJira(new AppIssue());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Field getField(String fieldName) {
        return null;
    }

    @Override
    public Issue updateIssueInJira(String issueKey, int ticketId) {
        log.info("updateIssueInJira called...");
        TicketInfoResponse response = ticketService.findById(ticketId);
        File file = Base64Util.fileContentasBytes(response.getFileBase64());
        if(Objects.nonNull(file)) {
            jiraUtil.attachmentInIssueThroughHttpClient(file, issueKey);
        }
        log.info("updateIssueInJira call...end ");
        return null;
    }

    @Override
    public Issue getIssue(String issueKey) {
        return jiraRestClient.getIssueClient().getIssue(issueKey).claim();
    }

    @Override
    public boolean isUserExist(String username) {
        return false;
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public Project getProjectByName(String projectKey) {
//        try {
//            BasicProject project = getAllProject().get(0);
//        }catch (Exception ex) {
//            if(ex instanceof ExecutionException) {
//                ExecutionException eex = (ExecutionException)ex;
//                if(eex.getCause() instanceof RestClientException) {
//                    RestClientException rest = (RestClientException) eex.getCause();
//                    System.out.println("statusCode: " + rest.getStatusCode().get() + "\n --" + rest.getMessage());
//                }
//            }
//            ex.printStackTrace();
//        }
        return jiraRestClient.getProjectClient().getProject(projectKey).claim();
    }
    @Override
    public List<BasicProject> getAllProject() {
        final List<BasicProject> list = new ArrayList<>();
        try {
            Promise<Iterable<BasicProject>> projects = jiraRestClient.getProjectClient().getAllProjects();
            final BasicProject project=null;
                    projects.get().forEach(pro -> {
                if("TIC".equals(pro.getKey())) {
                    list.add(pro);
                }
            });
            projects.get().forEach(pro -> System.out.println("id: " + pro.getId()
                    + "\nkey: " + pro.getKey()
                    + "\nname: " + pro.getName()
                    + "\nself: " + pro.getSelf()));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
