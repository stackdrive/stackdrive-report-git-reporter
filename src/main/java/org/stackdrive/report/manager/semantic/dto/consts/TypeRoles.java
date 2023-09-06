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
package org.stackdrive.report.manager.semantic.dto.consts;

/**
 * Contains semantic roles of type
 */
public class TypeRoles {

    /**
     * Any data type
     */
    public static String DATA = "Data";

    /**
     * Value object type
     */
    public static String VO = "VO";

    /**
     * Persistance type
     */
    public static String PERSISTANCE = "Persistance";

    /**
     * DTO type
     */
    public static String DTO = "DTO";

    /**
     * Service type
     */
    public static String SERVICE = "Service";

    /**
     * Public service or any endpoint
     */
    public static String PUBLIC_SERVICE = "Public_Service";

    /**
     * Private (inner service), business logic facade
     */
    public static String PRIVATE_SERVICE = "Private_Service";

    /**
     * Integration facade
     */
    public static String INTEGRATION_FACADE = "Integration_Facade";

    /**
     * Any bean
     */
    public static String BEAN = "Bean";

    /**
     * Stateful bean
     */
    public static String STATEFUL_BEAN = "Stateful_Bean";

    /**
     * Stateless bean
     */
    public static String STATELESS_BEAN = "Stateless_Bean";

    /**
     * Factory
     */
    public static String FACTORY = "Factory";
}
