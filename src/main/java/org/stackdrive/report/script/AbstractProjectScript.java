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
package org.stackdrive.report.script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract supertype for all scripts are used to create projects
 */
public class AbstractProjectScript {

    /**
     * Temporary directory for project
     */
    protected String tempDirectory;

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractProjectScript.class);

    /**
     * Returns project name
     * @return
     */
    protected String getProjectName () {
        return "myProject";
    }

    /**
     * Return absolute path to the test data. Not intended to be overridden in tests written as part of the IntelliJ IDEA codebase;
     * must be overridden in plugins which use the test framework.
     *
     * @see #getBasePath()
     */
    protected String getTestDataPath() {
        return this.getBasePath();
    }

    /**
     * Returns relative path to the test data.
     */
    protected String getBasePath() {
        return this.tempDirectory;
    }
}
