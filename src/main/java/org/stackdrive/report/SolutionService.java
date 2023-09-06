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
import org.springframework.stereotype.Service;
import org.stackdrive.model.SolutionDTO;
import org.stackdrive.model.headless.SolutionStatus;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SolutionService {

    private static final Logger log = LoggerFactory.getLogger(SolutionService.class);

    private final SolutionScriptService solutionScriptService;

    @Autowired
    public SolutionService(SolutionScriptService solutionScriptService) {
        this.solutionScriptService = solutionScriptService;
    }

    public String start(List<String> encodedUrls, String login) {
        String uid = String.join(",", encodedUrls);
        log.info(">>> start creating solution uid {}", uid);
        if (Objects.isNull(solutionScriptService.statusGet(uid))) {
            solutionScriptService.statusAdd(uid, SolutionStatus.PROGRESS);

            List<String> gitUrls = encodedUrls
                .stream()
                .map(SolutionService::decodeGitUrl)
                .collect(Collectors.toList());
            solutionScriptService.startCreateSolutionAsync(gitUrls, uid, login);
        }

        log.info("<<< start creating solution uid {}", uid);
        return uid;
    }


    /**
     * Decoding data
     *
     * @param encodedUrl
     * @return
     */
    private static String decodeGitUrl(String encodedUrl) {
        byte[] decodedBase64 = Base64.getUrlDecoder().decode(encodedUrl);
        return new String(decodedBase64, StandardCharsets.UTF_8);
    }

    SolutionStatus getStatus(String uid) {
        log.info(">>> getStatus uid {}", uid);
        final SolutionStatus status = solutionScriptService.statusGet(uid);
        log.info("<<< getStatus uid {}, status {}", uid, status);
        return status;
    }

    SolutionDTO getSolution(List<String> encodedUrls, String login) {
        String uid = String.join(",", encodedUrls);
        log.info(">>> getSolution {} {}", uid, solutionScriptService.statusGet(uid));
        if (Objects.isNull(solutionScriptService.statusGet(uid))) {
            start(encodedUrls, login);
        }
        return solutionScriptService.solutionGet(uid);
    }
}