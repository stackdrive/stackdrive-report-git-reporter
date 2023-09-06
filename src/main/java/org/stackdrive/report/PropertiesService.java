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

import org.stackdrive.report.script.properteis.UpdatePropertiesScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class PropertiesService {

    private static final Logger log = LoggerFactory.getLogger(PropertiesService.class);

    /**
     * Updates properties
     * @param slug
     * @param sectionName
     * @param modifiedProperties
     * @return
     */
    public String updateProperties (String slug, String sectionName, List<String> modifiedProperties) {
        log.info(">>> updateProperties");
        log.info("slug: {}", slug);
        byte[] decodedBase64 = Base64.getUrlDecoder().decode(slug);
        String deBase64Git = new String(decodedBase64, StandardCharsets.UTF_8);
        log.info("deBase64Git", deBase64Git);
        log.info("<<< updateProperties");
        return new UpdatePropertiesScript().updateProperties(deBase64Git, sectionName, modifiedProperties);
    }

}
