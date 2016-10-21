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
import org.springframework.social.optimizely.api.ScheduleOperations;
import org.springframework.social.optimizely.api.Experiment;
import org.springframework.social.optimizely.api.Schedule;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @author Onur Aktas
 */
class ScheduleTemplate extends AbstractOptimizelyOperations implements ScheduleOperations {
    /**
     *
     */
    private final RestTemplate restTemplate;

    /**
     *
     * @param restTemplate
     */
    public ScheduleTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Schedule create(Experiment experiment, Schedule schedule) {
        URI uri = buildUri("experiments/" + experiment.id + "/schedules");
        return restTemplate.postForObject(uri, schedule, Schedule.class);
    }

    /**
     *
     * @param scheduleId
     * @return
     */
    public Schedule read(int scheduleId) {
        URI uri = buildUri("schedules/" + scheduleId);
        return restTemplate.getForObject(uri, Schedule.class);
    }

    /**
     *
     * @param experimentId
     * @return
     */
    public List<Schedule> list(int experimentId) {
        URI uri = buildUri("experiments/" + experimentId + "/schedules");
        return Arrays.asList(restTemplate.getForObject(uri, Schedule[].class));
    }
}
