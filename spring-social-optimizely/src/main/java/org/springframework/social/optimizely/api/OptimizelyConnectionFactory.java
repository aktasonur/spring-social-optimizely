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

package org.springframework.social.optimizely.api;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.social.optimizely.api.impl.OptimizelyTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Onur Aktas
 */
public final class OptimizelyConnectionFactory {

    /**
     *
     */
    private OptimizelyConnectionFactory() {
    }

    /**
     *
     * @param apiToken
     * @return
     */
    public static Optimizely build(String apiToken) {
        return build(apiToken, new RestTemplate());
    }

    /**
     * Builds a new Optimizely instance
     *
     * @param apiToken apiToken to work with
     * @param restTemplate
     * @return Created Optimizely impl instnace
     */
    public static Optimizely build(String apiToken, RestTemplate restTemplate) {
        OptimizelyApiTokenInterceptor interceptor = new OptimizelyApiTokenInterceptor(apiToken);
        restTemplate.setInterceptors(Collections.<ClientHttpRequestInterceptor>singletonList(interceptor));

        return buildOptimizely(apiToken, restTemplate);
    }

    /**
     *
     * @return
     */
    private static Optimizely buildOptimizely(String apiToken, RestTemplate restTemplate) {
        OptimizelyTemplate restOptimizely = new OptimizelyTemplate(restTemplate);
        return restOptimizely;
    }
}
