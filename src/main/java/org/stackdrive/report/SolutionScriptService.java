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

import org.stackdrive.report.script.solution.HeadlessSolutionScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.stackdrive.model.SolutionDTO;
import org.stackdrive.model.headless.SolutionStatus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SolutionScriptService {

    private static final Logger log = LoggerFactory.getLogger(SolutionScriptService.class);

    private final Map<String, SolutionStatus> statusMap = new ConcurrentHashMap<>();

    private final Map<String, SolutionDTO> solutionMap = new ConcurrentHashMap<>();

    @Async
    public void startCreateSolutionAsync(List<String> gitUrls, String uid, String login) {
        log.info(">>> startCreateSolutionAsync uid {} urls {}", uid, gitUrls);
        SolutionResult solutionResult = (SolutionResult) new HeadlessSolutionScript().solutionReport(gitUrls);

        if (solutionResult.getSolutionStatus() == SolutionStatus.READY) {
            solutionMap.put(uid, solutionResult.getSolutionDTO());
        }
        statusMap.put(uid, solutionResult.getSolutionStatus());
        log.info("<<< startCreateSolutionAsync uid {} status {}", uid, solutionResult.getSolutionStatus());
    }

    public void statusAdd(String uid, SolutionStatus status) {
        statusMap.put(uid, status);
    }

    public SolutionStatus statusGet(String uid) {
        return statusMap.get(uid);
    }

    public SolutionDTO solutionGet(String uid) {
        return solutionMap.get(uid);
    }

    public void cleanFailed() {
        statusMap.forEach((uid, status) ->
            {
                if (status == SolutionStatus.FAIL) {
                    log.info("--- clean stuck solution {} {}", uid, status);
                    statusMap.remove(uid);
                }
            }
        );
    }
}
