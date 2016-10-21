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
public class Experiment {
    /**
     *
     */
    @JsonProperty("id")
    public long id;

    /**
     *
     */
    @JsonProperty("project_id")
    public long projectId;

    /**
     *
     */
    @JsonProperty("variation_ids")
    public int[] variationIds;

    /**
     *
     */
    @JsonProperty("edit_url")
    public String editUrl;

    /**
     *
     */
    @JsonProperty("description")
    public String description;

    /**
     *
     */
    @JsonProperty("display_goal_order_lst")
    public int[] displayGoalOrderList;

    /**
     *
     */
    @JsonProperty("primary_goal_id")
    public Long primaryGoalId;

    /**
     *
     */
    @JsonProperty("details")
    public String details;

    /**
     *
     */
    @JsonProperty("custom_css")
    public String customCss;

    /**
     *
     */
    @JsonProperty("custom_js")
    public String customJs;

    /**
     *
     */
    @JsonProperty("status")
    public String status;

    /**
     *
     */
    @JsonProperty("url_conditions")
    public UrlCondition[] urlConditions;

    /**
     *
     */
    @JsonProperty("percentage_included")
    public Integer percentageIncluded;

    /**
     *
     */
    @JsonProperty("activation_mode")
    public String activationMode;

    /**
     *
     */
    @JsonProperty("conditional_code")
    public String conditionalCode;

    /**
     *
     */
    @JsonProperty("experiment_type")
    public String experimentType;

    /**
     *
     */
    @JsonProperty("shareable_results_link")
    public String sharableResultsLink;

    /**
     *
     */
    @JsonProperty("audience_ids")
    public int[] audienceIds;

    /**
     *
     */
    @JsonProperty("is_multivariate")
    public boolean multivariate;
}
