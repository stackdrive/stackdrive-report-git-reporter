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

import org.stackdrive.report.manager.semantic.dto.*;

/**
 * Fild, which is typed
 */
public class TypedField extends NamedElement implements TypableElement, MarkableElement, ProblemableElement, ReferencableElement {

    /**
     * Related type. Resolved or not
     */
    private Type type;

    /**
     * Roles of type
     */
    private String [] markers;

    /**
     * Related problems
     */
    private Problem [] problems;

    /**
     * Related reference
     */
    private String reference;

    /**
     * Empty constructor for deserialization
     */
    public TypedField() {
    }

    /**
     * Defines by all class parameters
     * @param name
     * @param description
     * @param type
     */
    public TypedField(String name, String description, Type type, String [] markers, Problem [] problems, String ref) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.markers = markers;
        this.problems = problems;
        this.reference = ref;
    }

    @Override
    public Type getType() {
        return type;
    }

    /**
     * Defines type
     * @param type
     */
    public void setType (Type type) {
        this.type = type;
    }

    @Override
    public String[] getMarkers() {
        return markers;
    }

    @Override
    public Problem[] getProblems() {
        return problems;
    }

    @Override
    public String getReference() {
        return reference;
    }

    public void setReference (String reference) {
        this.reference = reference;
    }
}
