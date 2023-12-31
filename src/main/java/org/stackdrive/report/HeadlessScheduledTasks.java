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
package org.stackdrive.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HeadlessScheduledTasks {

    private final SolutionScriptService solutionService;

    private static final Logger log = LoggerFactory.getLogger(HeadlessScheduledTasks.class);

    @Autowired
    public HeadlessScheduledTasks(
        SolutionScriptService solutionScriptService
    ) {
        this.solutionService = solutionScriptService;
    }

    @Scheduled(fixedDelay = 180000, initialDelay = 60000)
    public void cleanFailed() {
        log.trace(">>> scheduled clean stuck solutions");
        solutionService.cleanFailed();
        log.trace("<<< scheduled clean stuck solutions");
    }
}
