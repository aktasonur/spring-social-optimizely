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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.social.optimizely.api.ExperimentOperations;
import org.springframework.social.optimizely.api.AbstractOptimizelyOperations;
import org.springframework.social.optimizely.api.Experiment;
import org.springframework.social.optimizely.api.Project;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @author Onur Aktas
 */
class ExperimentTemplate extends AbstractOptimizelyOperations implements ExperimentOperations {
    private final RestTemplate restTemplate;

    /**
     *
     * @param restTemplate
     */
    public ExperimentTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Experiment create(Project project, Experiment experiment) {
        URI uri = buildUri("projects/" + project.id + "/experiments/");
        return restTemplate.postForObject(uri, experiment, Experiment.class);
    }

    /**
     *
     * @param experimentId
     * @return
     */
    public Experiment read(int experimentId) {
        URI uri = buildUri("experiments/" + experimentId);
        return restTemplate.getForObject(uri, Experiment.class);
    }

    /**
     *
     * @param experimentId
     */
    public void delete(int experimentId) {
        URI uri = buildUri("experiments/" + experimentId);
        restTemplate.delete(uri);
    }

    /**
     *
     * @param experimentId
     */
    public void archive(int experimentId) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("status", "Archived");
        try {
            json = mapper.writeValueAsString(objectNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        URI uri = buildUri("experiments/" + experimentId);
        restTemplate.put(uri, json);
    }

    /**
     *
     * @param projectId
     * @return
     */
    public List<Experiment> list(int projectId) {
        URI uri = buildUri("projects/" + projectId + "/experiments/");
        return Arrays.asList(restTemplate.getForObject(uri, Experiment[].class));
    }
}
