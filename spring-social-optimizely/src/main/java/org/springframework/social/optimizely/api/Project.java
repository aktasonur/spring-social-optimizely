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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Onur Aktas
 */
public class Project {
    /**
     *
     */
    @JsonProperty("id")
    public long id;

    /**
     *
     */
    @JsonProperty("project_name")
    public String projectName;

    /**
     *
     */
    @JsonProperty("account_id")
    public long accountId;

    /**
     *
     */
    @JsonProperty("project_status")
    public String projectStatus;

    /**
     *
     */
    @JsonProperty("include_jquery")
    public boolean includeJQuery;

    /**
     *
     */
    @JsonProperty("library")
    public String library;

    /**
     *
     */
    @JsonProperty("project_javascript")
    public String projectJavascript;

    /**
     *
     */
    @JsonProperty("ip_anonymization")
    public boolean ipAnonymization;

    /**
     *
     */
    @JsonProperty("ip_filter")
    public String ipFilter;

    /**
     *
     */
    @JsonProperty("enable_force_variation")
    public boolean enableForceVariation;

    /**
     *
     */
    @JsonProperty("exclude_disabled_experiments")
    public boolean excludeDisabledExperiments;

    /**
     *
     */
    @JsonProperty("exclude_names")
    public String excludeNames;

    public Project(int id) {
        this.id = id;
    }

    public Project() {
    }
}
