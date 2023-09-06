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
 * Operation model
 */
public class ParametrableOperation extends NamedElement implements ParametrableElement, MarkableElement,
        ProblemableElement, ReferencableElement, RelationableElement {

    /**
     * Related parameters
     */
    private Parameter[] parameters;

    /**
     * Related roles
     */
    private String [] markers;

    /**
     * Related problems
     */
    private Problem[] problems;

    /**
     * Related reference
     */
    private String reference;

    /**
     * References relations
     */
    private Relation [] relations;

    public ParametrableOperation () {
    }

    /**
     * Creates instance by all parameters
     * @param name
     * @param description
     * @param parameters
     * @param roles
     * @param problems
     */
    public ParametrableOperation (String name, String description, Parameter[] parameters, String roles []
        , Problem [] problems, String ref, Relation [] relations) {
        this.name = name;
        this.description = description;
        this.parameters = parameters;
        this.markers = roles;
        this.problems = problems;
        this.reference = ref;
        this.relations = relations;
    }

    @Override
    public Parameter[] getParameters() {
        return parameters;
    }

    /**
     * Defines parameters
     * @param parameters
     */
    public void setParameters (Parameter[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String [] getMarkers() {
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

    @Override
    public Relation[] getRelations() {
        return relations;
    }
}
