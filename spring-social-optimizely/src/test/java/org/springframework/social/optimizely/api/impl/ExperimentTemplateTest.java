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
import org.springframework.social.optimizely.api.Experiment;
import org.springframework.social.optimizely.api.Project;
import org.springframework.social.optimizely.api.UrlCondition;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Onur Aktas
 */
public class ExperimentTemplateTest extends BaseOptimizelyTemplateTest {

    @Test
    public void create() throws IOException, URISyntaxException {
        // create
        mockServer
                .expect(requestTo("https://www.optimizelyapis.com/experiment/v1/projects/1234/experiments/"))
                .andExpect(header("Token", API_TOKEN))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(file("create-project.json"), MediaType.APPLICATION_JSON));

        Experiment experiment = new Experiment();
        Project project = new Project(1234);
        optimizely.experimentOperations().create(project, experiment);

        mockServer.verify();
    }

    /**
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void read() throws IOException, URISyntaxException {
        // list
        mockServer.expect(requestTo("https://www.optimizelyapis.com/experiment/v1/experiments/15"))
                .andExpect(method(GET))
                .andExpect(header("Token", API_TOKEN))
                .andRespond(withSuccess(file("read-experiment.json"), MediaType.APPLICATION_JSON));

        Experiment experiment = optimizely.experimentOperations().read(15);

        assertEquals(791495413, experiment.id);
        assertEquals(754864960, experiment.projectId);
        assertArrayEquals(new int[]{800227656, 800227657}, experiment.variationIds);
        assertEquals("http://blog.optimizely.com/2014/04/11/10-reasons-why-your-agency-should-offer-optimization/", experiment.editUrl);
        assertEquals("Wordpress: 10 Reasons Why Your Agency Should Offer Optimization ", experiment.description);
        assertArrayEquals(new int[]{1, 2, 3}, experiment.displayGoalOrderList);
        assertEquals(1234, experiment.primaryGoalId.intValue());
        assertEquals("Experiment to test out blog post.", experiment.details);
        assertEquals("", experiment.customCss);
        assertEquals("", experiment.customJs);
        assertEquals("Not started", experiment.status);

        // URL CONDITIONS
        UrlCondition[] urlConditions = experiment.urlConditions;
        assertEquals(1, urlConditions.length);
        assertEquals(0, urlConditions[0].index);
        assertEquals("simple", urlConditions[0].matchType);
        assertEquals("http://blog.optimizely.com/2014/04/11/10-reasons-why-your-agency-should-offer-optimization/", urlConditions[0].value);
        assertEquals(false, urlConditions[0].negate);

        assertEquals(10000, experiment.percentageIncluded.intValue());
        assertEquals("immediate", experiment.activationMode);
        assertNull(experiment.conditionalCode);
        assertEquals("ab", experiment.experimentType);

        assertArrayEquals(new int[]{100, 200, 300}, experiment.audienceIds);


        assertEquals(false, experiment.multivariate);

        mockServer.verify();
    }

    @Test
    public void delete() throws IOException, URISyntaxException {
        // list
        mockServer.expect(requestTo("https://www.optimizelyapis.com/experiment/v1/experiments/15"))
                .andExpect(method(DELETE))
                .andExpect(header("Token", API_TOKEN))
                .andRespond(withSuccess());

        optimizely.experimentOperations().delete(15);

        mockServer.verify();
    }

    /**
     * Sending DELETE on an experiment will permanently delete the experiment and its results.
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void list() throws IOException, URISyntaxException {
        // list
        mockServer.expect(requestTo("https://www.optimizelyapis.com/experiment/v1/projects/1234/experiments/"))
                .andExpect(method(GET))
                .andExpect(header("Token", API_TOKEN))
                .andRespond(withSuccess(file("list-experiments.json"), MediaType.APPLICATION_JSON));

        List<Experiment> experiments = optimizely.experimentOperations().list(1234);

        assertEquals(2, experiments.size());

        assertEquals(15, experiments.get(0).id);
        assertEquals(1234, experiments.get(0).projectId);
        assertArrayEquals(new int[]{115, 210, 215}, experiments.get(0).variationIds);
        assertEquals("https://mysite.com/products/", experiments.get(0).editUrl);
        assertEquals("Not started", experiments.get(0).status);

        mockServer.verify();
    }

    @Test
    public void archive() throws IOException, URISyntaxException {
        String ctr = "{\"status\":\"Archived\"}";
        // list
        mockServer.expect(requestTo("https://www.optimizelyapis.com/experiment/v1/experiments/15"))
                .andExpect(method(HttpMethod.PUT))
                .andExpect(header("Token", API_TOKEN))
                .andExpect(content().string(ctr))
                .andRespond(withSuccess());

        optimizely.experimentOperations().archive(15);

        mockServer.verify();
    }
}
