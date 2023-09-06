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
 * Java class model
 */
public class ClassType extends TypeResolved implements MarkableElement, ProblemableElement, RelationableElement {

    /**
     * Sign, that class is abstract
     */
    private boolean isAbstract;

    /**
     * Sign, that class is interface
     */
    private boolean isInterface;

    /**
     * Generic defenition
     */
    private String generics;

    /**
     * List of fields
     */
    private TypedField[] fields;

    /**
     * List of operations
     */
    private ParametrableOperation [] operations;

    /**
     * Roles of type
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
     * Contains canonical name
     */
    private String canonical;

    /**
     * References relations
     */
    private Relation[] relations;

    /**
     * References to supertypes
     */
    private Type [] superTypes;

    /**
     * Empty constuctor for deserialization
     */
    public ClassType () {
    }

    /**
     * Defines class by all params
     * @param name
     * @param description
     * @param isAbstract
     * @param isInterface
     * @param generics
     * @param fields
     * @param operations
     * @param markers
     * @param problems
     * @param ref
     * @param relations
     * @param canonical
     */
    public ClassType (String name, String description, boolean isAbstract, boolean isInterface, String generics
        , TypedField[] fields, ParametrableOperation [] operations, String [] markers, Problem[] problems
        , String ref, Relation [] relations, String canonical, Type [] superTypes) {
        this.name = name;
        this.description = description;
        this.isAbstract = isAbstract;
        this.isInterface = isInterface;
        this.generics = generics;
        this.fields = fields;
        this.operations = operations;
        this.markers = markers;
        this.problems = problems;
        this.reference = ref;
        this.relations = relations;
        this.canonical = canonical;
        this.superTypes = superTypes;
    }

    /**
     * Returns sign, that class is abstract
     * @return
     */
    public boolean isAbstract() {
        return isAbstract;
    }

    /**
     * Defines sign, that class is abstract
     * @param anAbstract
     */
    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    /**
     * Returns sign, that class is interface
     * @return
     */
    public boolean isInterface() {
        return isInterface;
    }

    /**
     * Defines sign, that class is interface
     * @param isInterface
     */
    public void setInterface(boolean isInterface) {
        this.isInterface = isInterface;
    }

    /**
     * Returns generic definition string
     * @return
     */
    public String getGenerics() {
        return generics;
    }

    /**
     * Defines generic defenition string
     * @param generics
     */
    public void setGenerics(String generics) {
        this.generics = generics;
    }

    /**
     * Returns list of fields
     * @return
     */
    public TypedField[] getFields() {
        return fields;
    }

    /**
     * Defines list of fields
     * @param fields
     */
    public void setFields(TypedField[] fields) {
        this.fields = fields;
    }

    /**
     * Returns list of operations
     * @return
     */
    public ParametrableOperation[] getOperations() {
        return operations;
    }

    /**
     * Defines operations list
     * @param operations
     */
    public void setOperations(ParametrableOperation[] operations) {
        this.operations = operations;
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

    /**
     * Returns canonical
     * @return
     */
    public String getCanonical() {
        return canonical;
    }

    /**
     * Defines canonical
     * @param canonical
     */
    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }

    /**
     * Returns supertypes
     * @return
     */
    public Type[] getSuperTypes() {
        return superTypes;
    }

    /**
     * Defines supertypes
     * @param superTypes
     */
    public void setSuperTypes(Type[] superTypes) {
        this.superTypes = superTypes;
    }
}
