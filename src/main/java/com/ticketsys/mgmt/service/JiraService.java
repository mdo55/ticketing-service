package com.ticketsys.mgmt.service;

import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.ticketsys.mgmt.domain.AppIssue;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
/**
 * The interface for Jira Service
 * @author mdoss.
 */
public interface JiraService {

    /**
     * Create support jira on JIRA.
     *
     * @param appIssue the issue to be created
     * @return the issue with the id
     */
    BasicIssue createIssueInJira(AppIssue appIssue) throws URISyntaxException;

    /**
     * Return all supported fields on jira.
     *
     * @return jira supported fields
     */
    List<Field> getAllField();

    /**
     * Return jira field.
     *
     * @param fieldName the jira field name
     * @return jira field
     */
    Field getField(String fieldName);

    /**
     * Update a issue on JIRA.
     *
     * @param issue the issue.
     * @param newFieldList the list of field to be updated.
     * @return the issue with the id
     */
    Issue updateIssueInJira(Issue issue, Iterable<FieldInput> newFieldList);

    /**
     * Returns a issue from JIRA.
     *
     * @param issueKey the issue key
     * @return the issue with the id
     */
    Issue getIssue(String issueKey);

    /**
     * validate if an user exist on JIRA.
     *
     * @param username the jira user
     * @return true if exist else false
     */
    boolean isUserExist(String username);

    /**
     * Returns a JIRA user.
     *
     * @param username the jira user
     * @return the user
     */
    User getUserByName(String username);

    /**
     * Returns all projects on jira.
     *
     * @return the list of project.
     */
    Project getProjectByName(String projectName);

}
