/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.stackdrive.report.manager.semantic.dto;

import org.stackdrive.report.manager.semantic.dto.consts.FilterTypes;

/**
 * Request to get solution report
 */
public class SolutionRequest {

    /**
     * Array of git repo's, which should be used
     */
    private String [] repos;

    /**
     * Code of problems, should be used
     */
    private String [] problems;

    /**
     * Filters, should used for result
     */
    private FilterTypes [] filters;

    /**
     * Sign, that stub should be called
     */
    private boolean useStub;

    /**
     * Sign, that dependencies should bu used to build solution report
     */
    private boolean useDependencies;

    /**
     * Empty for deserialization
     */
    public SolutionRequest () {
    }

    /**
     * Defines by repos
     * @param repos
     */
    public SolutionRequest (String [] repos) {
        this.repos = repos;
    }

    /**
     * Returns repos to be analized
     * @return
     */
    public String[] getRepos() {
        return repos;
    }

    /**
     * Defines repos to be analized
     * @param repos
     */
    public void setRepos(String[] repos) {
        this.repos = repos;
    }

    /**
     * Returns array of codes to be used to analyzed
     * @return
     */
    public String[] getProblems() {
        return problems;
    }

    /**
     * Defines array of codes to analyze
     * @param problems
     */
    public void setProblems(String[] problems) {
        this.problems = problems;
    }

    /**
     * Returns array of filters
     * @return
     */
    public FilterTypes[] getFilters() {
        return filters;
    }

    /**
     * Defines array of filters for resulted data
     * @param filters
     */
    public void setFilters(FilterTypes[] filters) {
        this.filters = filters;
    }

    /**
     * Returns sign, that dependencies should be used
     * @return
     */
    public boolean isUseDependencies() {
        return useDependencies;
    }

    /**
     * Defines sign to use dependencies
     * @param useDependencies
     */
    public void setUseDependencies(boolean useDependencies) {
        this.useDependencies = useDependencies;
    }

    /**
     * Returns sign, that stub should be used
     * @return
     */
    public boolean isUseStub() {
        return useStub;
    }

    /**
     * Defines sign, that stub should be used
     * @param useStub
     */
    public void setUseStub(boolean useStub) {
        this.useStub = useStub;
    }
}
