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

import org.stackdrive.report.manager.semantic.dto.solution.Solution;

/**
 * Resposne of solution
 */
public class SolutionResponse {

    /**
     * Array of git repo's, was used to build solution report
     */
    private String [] repos;

    /**
     * Resulted solution
     */
    private Solution solution;

    /**
     * Empty for deserialization
     */
    public SolutionResponse () {
    }

    /**
     * For single repo
     * @param solution
     * @param repo
     */
    public SolutionResponse (Solution solution, String repo) {
        this.solution = solution;
        this.repos = new String[1];
        this.repos[0] = repo;
    }

    /**
     * Returns repos  was used to build solution report
     * @return
     */
    public String[] getRepos() {
        return repos;
    }

    /**
     * Defines repos  was used to build solution report
     * @param repos
     */
    public void setRepos(String[] repos) {
        this.repos = repos;
    }

    /**
     * Returns root solution object
     * @return
     */
    public Solution getSolution() {
        return solution;
    }

    /**
     * Defines root solution object
     * @param solution
     */
    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}
