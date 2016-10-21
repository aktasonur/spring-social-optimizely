/*
 * Copyright 2016 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.springframework.social.optimizely.api.impl;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.optimizely.api.Project;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Onur Aktas
 */
public class ProjectTemplateTest extends BaseOptimizelyTemplateTest {

    @Test
    public void create() throws IOException, URISyntaxException {
        // create
        mockServer
                .expect(requestTo("https://www.optimizelyapis.com/experiment/v1/projects/"))
                .andExpect(header("Token", API_TOKEN))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(file("create-project.json"), MediaType.APPLICATION_JSON));

        Project project = new Project();

        project = optimizely.projectOperations().create(project);
        assertEquals(555650815, project.accountId);
        assertEquals(859720118, project.id);
        assertEquals("Test App", project.projectName);
        assertEquals("Active", project.projectStatus);

        assertEquals(false, project.includeJQuery);
        assertNull(project.projectJavascript);

        mockServer.verify();
    }

    @Test
    public void read() throws IOException, URISyntaxException {
        // create
        mockServer
                .expect(requestTo("https://www.optimizelyapis.com/experiment/v1/projects/1234"))
                .andExpect(header("Token", API_TOKEN))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(file("read-project.json"), MediaType.APPLICATION_JSON));

        Project project = optimizely.projectOperations().read(1234);
        assertEquals(859720118, project.id);
        assertEquals(555650815, project.accountId);
        assertEquals("My even newer project name", project.projectName);
        assertEquals("Active", project.projectStatus);

        assertEquals("jquery-1.6.4-trim", project.library);
        assertEquals(false, project.includeJQuery);

        assertEquals("alertFunc = function() {alert(1)}", project.projectJavascript);
        assertEquals(false, project.enableForceVariation);

        assertEquals(false, project.excludeDisabledExperiments);
        assertNull(project.excludeNames);

        assertEquals(false, project.ipAnonymization);
        assertEquals("1.2.3.4", project.ipFilter);

        mockServer.verify();
    }

    @Test
    public void list() throws IOException, URISyntaxException {
        // list
        mockServer.expect(requestTo("https://www.optimizelyapis.com/experiment/v1/projects/"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Token", API_TOKEN))
                .andRespond(withSuccess(file("list-projects.json"), MediaType.APPLICATION_JSON));

        List<Project> projects = optimizely.projectOperations().list();

        assertEquals(2, projects.size());

        assertEquals("My new project", projects.get(0).projectName);
        assertEquals(819000157, projects.get(0).id);
        assertEquals(555650815, projects.get(0).accountId);

        assertEquals("My even newer project name", projects.get(1).projectName);
        assertEquals(859720118, projects.get(1).id);
        assertEquals(555650815, projects.get(1).accountId);

        mockServer.verify();
    }
}
