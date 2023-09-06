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

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Common supertype for all elements, which has uniq canonical name, and have description
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "element")
public class NamedElement {

    /**
     * Simple element name
     */
    protected String name;

    /**
     * Semantic element description;
     */
    protected String description;

    /**
     * Returns name of element
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Defines name of element
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns description of element
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Defines description of element
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
