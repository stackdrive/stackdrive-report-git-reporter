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

import org.stackdrive.report.manager.semantic.dto.MarkableElement;
import org.stackdrive.report.manager.semantic.dto.NamedElement;
import org.stackdrive.report.manager.semantic.dto.Type;

/**
 * Component model
 */
public class Component extends NamedElement implements MarkableElement {

    /**
     * Src and resource code roots for component
     */
    private Type[] types;

    /**
     * Related roles
     */
    private String [] markers;

    /**
     * Version of component
     */
    private String version;

    /**
     * Empty constructor for deserialization
     */
    public Component () {
    }

    /**
     * Defines all parameters
     * @param name
     */
    public Component (String name, Type [] types, String [] markers, String version) {
        this.name = name;
        this.types = types;
        this.markers = markers;
        this.version = version;
    }

    @Override
    public String[] getMarkers() {
        return markers;
    }

    /**
     * Returns classes
     * @return
     */
    public Type[] getTypes() {
        return types;
    }

    /**
     * Defines classes
     * @param types
     */
    public void setTypes(Type[] types) {
        this.types = types;
    }

    /**
     * Returns version of component
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     * Defines version of component
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
