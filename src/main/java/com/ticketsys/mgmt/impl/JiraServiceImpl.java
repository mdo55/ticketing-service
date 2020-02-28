package com.ticketsys.mgmt.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Field;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.util.concurrent.Promise;
import com.ticketsys.mgmt.domain.AppIssue;
import com.ticketsys.mgmt.service.JiraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Jira service implementaion, Jira activities like create, update issue performed.
 * @author mdoss.
 */
@Slf4j
@Component
public class JiraServiceImpl implements JiraService {

    @Autowired(required = true)
    private JiraRestClient jiraRestClient;

    @Override
    public BasicIssue createIssueInJira(AppIssue appIssue) throws URISyntaxException {
        return null;
    }

    @Override
    public List<Field> getAllField() {
        try {
            Promise<Iterable<Field>> fields = jiraRestClient.getMetadataClient().getFields();
            fields.get().forEach(fi -> System.out.println("fields are: "+fi.toString()));
            //map(field -> field.forEach( fi -> System.out.println(fi.toString())));
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
    public com.atlassian.jira.rest.client.api.domain.Issue updateIssueInJira(com.atlassian.jira.rest.client.api.domain.Issue issue,
        Iterable<FieldInput> newFieldList) {
        return null;
    }

    @Override
    public com.atlassian.jira.rest.client.api.domain.Issue getIssue(String issueKey) {
        return null;
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
    public Project getProjectByName(String projectName) {
//        jiraRestClient.getMetadataClient().
        return null;
    }
}
