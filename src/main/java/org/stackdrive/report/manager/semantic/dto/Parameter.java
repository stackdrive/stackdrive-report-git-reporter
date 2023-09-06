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

/**
 * Mark up class to define parameters
 */
public class Parameter extends NamedElement {

    public enum Direction {
        IN, // Input parameter
        OUT, // Output parameter
        RETURN, // Return parameter
        INOUT; // Input/output parameter
    }

    protected Direction direction;

    /**
     * Return parameter direction
     * @return
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Defines parameter direction
     * @param direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }




}
