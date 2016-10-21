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
import org.springframework.social.optimizely.api.Schedule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Onur Aktas
 */
public class ScheduleTemplateTest extends BaseOptimizelyTemplateTest {

    @Test
    public void create() throws IOException, URISyntaxException {
        // create
        mockServer
                .expect(requestTo("https://www.optimizelyapis.com/experiment/v1/experiments/1234/schedules"))
                .andExpect(header("Token", API_TOKEN))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(file("create-schedule.json"), MediaType.APPLICATION_JSON));

        Schedule schedule = new Schedule();
        Experiment experiment = new Experiment();
        experiment.id = 1234;
        schedule = optimizely.scheduleOperations().create(experiment, schedule);
        assertEquals(5678, schedule.id);

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
        mockServer.expect(requestTo("https://www.optimizelyapis.com/experiment/v1/schedules/1234"))
                .andExpect(method(GET))
                .andExpect(header("Token", API_TOKEN))
                .andRespond(withSuccess(file("read-schedule.json"), MediaType.APPLICATION_JSON));

        Schedule schedule = optimizely.scheduleOperations().read(1234);

        assertEquals(1234, schedule.id);
        assertEquals(5678, schedule.experimentId);
        assertEquals("ACTIVE", schedule.status);
        assertEquals("2015-01-01T08:00:00Z", schedule.startTime);
        assertEquals("2016-01-01T08:00:00Z", schedule.stopTime);

        mockServer.verify();
    }

    /**
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void list() throws IOException, URISyntaxException {
        // list
        mockServer.expect(requestTo("https://www.optimizelyapis.com/experiment/v1/experiments/1234/schedules"))
                .andExpect(method(GET))
                .andExpect(header("Token", API_TOKEN))
                .andRespond(withSuccess(file("list-schedules.json"), MediaType.APPLICATION_JSON));

        List<Schedule> schedules = optimizely.scheduleOperations().list(1234);

        assertEquals(3, schedules.size());

        assertEquals(5678, schedules.get(0).id);
        assertEquals(1234, schedules.get(0).experimentId);
        assertEquals("ACTIVE", schedules.get(0).status);
        assertEquals("2015-01-01T08:00:00Z", schedules.get(0).startTime);
        assertEquals(null, schedules.get(0).stopTime);

        mockServer.verify();
    }
}