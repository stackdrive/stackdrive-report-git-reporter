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
package org.stackdrive.report.manager.semantic.dto.solution;

import org.stackdrive.report.manager.semantic.dto.ReferencableElement;
import org.stackdrive.report.manager.semantic.dto.Type;

/**
 * Reference for resolved type
 */
public class TypeResolved extends Type implements ReferencableElement {

    /**
     * Canonical named
     */
    protected String canonical;

    /**
     * Related reference
     */
    private String reference;

    /**
     * Empty for deserialization
     */
    public TypeResolved () {
    }

    /**
     * Full parameter constructor
     * @param name
     * @param description
     * @param canonical
     */
    public TypeResolved (String name, String description, String canonical, String ref) {
        this.name = name;
        this.description = description;
        this.canonical = canonical;
        this.reference = ref;
    }


    /**
     * Returns canonical name
     * @return
     */
    public String getCanonical() {
        return canonical;
    }

    /**
     * Defines canonical name
     * @param canonical
     */
    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }

    @Override
    public String getReference() {
        return reference;
    }

    public void setReference(String ref) {
        reference = ref;
    }
}
