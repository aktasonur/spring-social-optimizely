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

/**
 * @author Onur Aktas
 */
public abstract class AbstractOptimizely {
    /**
     * Stores project ops information
     */
    protected ProjectOperations projectOperations;

    /**
     * Stores experiment ops
     */
    protected ExperimentOperations experimentOperations;

    /**
     * Stores schedule ops
     */
    protected ScheduleOperations scheduleOperations;

    /**
     * Stores schedule ops
     */
    protected VariationOperations variationOperations;

    /**
     * Stores schedule ops
     */
    protected GoalOperations goalOperations;

    /**
     *
     * @param projectOperations
     */
    public void setProjectOperations(ProjectOperations projectOperations) {
        this.projectOperations = projectOperations;
    }

    /**
     *
     * @param experimentOperations
     */
    public void setExperimentOperations(ExperimentOperations experimentOperations) {
        this.experimentOperations = experimentOperations;
    }

    /**
     *
     * @param scheduleOperations
     */
    public void setScheduleOperations(ScheduleOperations scheduleOperations) {
        this.scheduleOperations = scheduleOperations;
    }

    /**
     *
     * @param variationOperations
     */
    public void setVariationOperations(VariationOperations variationOperations) {
        this.variationOperations = variationOperations;
    }

    /**
     *
     * @param goalOperations
     */
    public void setGoalOperations(GoalOperations goalOperations) {
        this.goalOperations = goalOperations;
    }
}
