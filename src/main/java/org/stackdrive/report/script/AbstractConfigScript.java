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

import org.stackdrive.tools.behavior.config.BooleanConfig;
import org.stackdrive.tools.behavior.config.IntegerConfig;
import org.stackdrive.tools.behavior.config.StringConfig;
import org.stackdrive.tools.tool.config.ConfigUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

/**
 * Contains methods to get data from configs
 */
public abstract class AbstractConfigScript extends AbstractProjectScript {

    /**
     * Common logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractConfigScript.class);

    /**
     * Config properties
     */
    protected Properties properties;

    /**
     * Returns pattern for section
     *
     * @param section
     * @return
     */
    protected String getPatternForSection(String section) {
        if ("Risks".equals(section)) {
            return ".ext.pattern.";
        }
        return ".impossable.classpath.";
    }

    /**
     * Load instance of configurable classes
     *
     * @param pattern
     * @return
     */
    protected Set<Object> loadConfigurable(String pattern) {
        Set<Object> os = new HashSet<>();
        Enumeration<?> pen = this.properties.propertyNames();
        while (pen.hasMoreElements()) {
            String name = pen.nextElement().toString();
            if (name.contains(pattern)) {
                try {
                    Object ob = Class.forName(name.substring(0, name.lastIndexOf("."))).getDeclaredConstructor().newInstance();
                    os.add(ob);
                } catch (Throwable e) {
                    LOG.error("loadConfigurable error", e);
                }
            }
        }
        return os;
    }

    /**
     * Init configurable
     *
     * @param cs
     */
    protected void initConfigurable(Set<Object> cs) {
        for (Object c : cs) {
            String fn = ConfigUtils.getConfigFieldName(c);
            if (fn != null) {
                boolean isSelected = Boolean.parseBoolean(properties.getProperty(c.getClass()
                    .getCanonicalName() + "." + fn));
                ConfigUtils.selectConfig(c, isSelected);
            }

        }
    }

    /**
     * Returns list of sorted configurable objects
     *
     * @param cs
     * @return
     */
    protected List<Object> sortConfigurableByTitle(Set<Object> cs) {
        List<Object> cl = new ArrayList<>();
        cl.addAll(cs);
        Collections.sort(cl, new ConfigurableTitleComparator());
        return cl;
    }

    /**
     * Loads properties file
     */
    protected void initConfigs() {
        LOG.info(">>> init configs");
        if (this.properties != null)
            return;
        String canonical = getBasePath() + "/.stackdrive/stackdrive.properties";
        Path path = FileSystems.getDefault().getPath(canonical);
        // Create file if excepted
        if (!path.toFile().exists()) {
            try {
                File fs = new File(canonical);
                boolean cd = fs.getParentFile().mkdirs();
                boolean cf = fs.createNewFile();
                if (!cf && !cd) {
                    throw new IOException("Can't create file/dir");
                }
            } catch (IOException ioe) {
                LOG.error("context", ioe);
            }
        }
        // Load properties
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(canonical), StandardCharsets.UTF_8)) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException ee) {
            LOG.error("context", ee);
        }
        LOG.info("<<< done init configs");
    }

    /**
     * Saves properties file
     */
    public void saveConfigs() {
        String canonical = getBasePath() + "/" + this.getProjectName() + "/.stackdrive/stackdrive.properties";
        Path path = FileSystems.getDefault().getPath(canonical);
        // Create file if excepted
        if (!path.toFile().exists()) {
            try {
                File fs = new File(canonical);
                boolean cd = fs.getParentFile().mkdirs();
                boolean cf = fs.createNewFile();
                if (!cf && !cd) {
                    throw new IOException("Can't create file/dir");
                }
            } catch (IOException ioe) {
                LOG.error("context", ioe);
            }
        }
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(canonical, false), StandardCharsets.UTF_8)) {
            properties.store(writer, "# changed at " + (new Date()));
        } catch (IOException ee) {
            LOG.error("context", ee);
        }
    }


    /**
     * Loads object
     *
     * @param o
     */
    protected void load(Object o) {
        Field[] fl = FieldUtils.getAllFields(o.getClass());
        for (Field f : fl)
            if (f.isAnnotationPresent(BooleanConfig.class))
                this.loadAsBoolean(o, f);
            else if (f.isAnnotationPresent(StringConfig.class))
                this.loadAsString(o, f);
            else if (f.isAnnotationPresent(IntegerConfig.class))
                this.loadAsInteger(o, f);
    }

    /**
     * Load parameter as boolean value
     *
     * @param o
     * @param f
     */
    private void loadAsBoolean(Object o, Field f) {
        String key = this.createKey(o, f);
        String value = properties.getProperty(key);
        if (value != null)
            try {
                f.setAccessible(true);
                f.set(o, Boolean.valueOf(value));
            } catch (Exception e) {
                LOG.error("context", e);
            }
    }

    /**
     * Load parameter as string value
     *
     * @param o
     * @param f
     */
    private void loadAsString(Object o, Field f) {
        String key = this.createKey(o, f);
        String value = properties.getProperty(key);
        if (value != null)
            try {
                f.setAccessible(true);
                f.set(o, value);
            } catch (Exception e) {
                LOG.error("context", e);
            }
    }

    /**
     * Load parameter as integer value
     *
     * @param o
     * @param f
     */
    private void loadAsInteger(Object o, Field f) {
        String key = this.createKey(o, f);
        String value = properties.getProperty(key);
        if (value != null)
            try {
                f.setAccessible(true);
                f.set(o, Integer.valueOf(value));
            } catch (Exception e) {
                LOG.error("context", e);
            }
    }

    /**
     * Creates key
     *
     * @param o
     * @param f
     * @return
     */
    private String createKey(Object o, Field f) {
        StringBuilder keysb = new StringBuilder(o.getClass().getCanonicalName());
        keysb.append(".");
        keysb.append(f.getName());
        return keysb.toString();
    }

    /**
     * Comparator to sort title objects
     */
    private class ConfigurableTitleComparator implements Comparator<Object> {

        @Override
        public int compare(Object o1, Object o2) {
            String t1 = ConfigUtils.getConfigurableTitle(o1);
            String t2 = ConfigUtils.getConfigurableTitle(o2);
            return t1.compareTo(t2);
        }
    }


}
