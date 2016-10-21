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

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.social.optimizely.api.Optimizely;
import org.springframework.social.optimizely.api.OptimizelyConnectionFactory;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Onur Aktas
 */
public class BaseOptimizelyTemplateTest {

    public final static String API_TOKEN = "API_TOKEN";

    protected Optimizely optimizely;

    protected RestTemplate restTemplate;

    protected MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        optimizely = OptimizelyConnectionFactory.build(API_TOKEN, restTemplate);
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    protected ClassPathResource file(String filename) throws IOException, URISyntaxException {
        return new ClassPathResource(filename, getClass());
    }
}
