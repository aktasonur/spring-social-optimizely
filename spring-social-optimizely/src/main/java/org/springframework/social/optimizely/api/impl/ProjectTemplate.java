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

import org.springframework.social.optimizely.api.AbstractOptimizelyOperations;
import org.springframework.social.optimizely.api.ProjectOperations;
import org.springframework.social.optimizely.api.Project;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Onur Aktas
 */
public class ProjectTemplate extends AbstractOptimizelyOperations implements ProjectOperations {
    /**
     *
     */
    private final RestTemplate restTemplate;

    /**
     *
     * @param template
     */
    public ProjectTemplate(final RestTemplate template) {
        this.restTemplate = template;
    }

    /**
     *
     * @param project
     * @return
     */
    public Project create(Project project) {
        return restTemplate.postForObject(buildUri("projects/"), project, Project.class);
    }

    /**
     *
     * @param id
     * @return
     */
    public Project read(long id) {
        return restTemplate.getForObject(buildUri("projects/" + id), Project.class);
    }

    /**
     *
     * @return
     */
    public List<Project> list() {
        return Arrays.asList(restTemplate.getForObject(buildUri("projects/"), Project[].class));
    }
}
