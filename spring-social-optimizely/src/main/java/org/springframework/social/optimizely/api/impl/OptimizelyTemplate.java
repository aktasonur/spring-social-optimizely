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

import org.springframework.social.optimizely.api.ExperimentOperations;
import org.springframework.social.optimizely.api.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Onur Aktas
 */
public class OptimizelyTemplate extends AbstractOptimizely implements Optimizely {

    /**
     *
     */
    final RestTemplate restTemplate;

    public OptimizelyTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        initSubApis();
    }

    private void initSubApis() {
        this.projectOperations = new ProjectTemplate(restTemplate);
        this.experimentOperations = new ExperimentTemplate(restTemplate);
        this.scheduleOperations = new ScheduleTemplate(restTemplate);
        this.variationOperations = new VariationTemplate(restTemplate);
        this.goalOperations = new GoalTemplate(restTemplate);
    }

    public ProjectOperations projectOperations() {
        return projectOperations;
    }

    public ExperimentOperations experimentOperations() {
        return experimentOperations;
    }

    public ScheduleOperations scheduleOperations() {
        return scheduleOperations;
    }

    public VariationOperations variationOperations() {
        return variationOperations;
    }

    public GoalOperations goalOperations() {
        return goalOperations;
    }
}
