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
package org.stackdrive.report.script.properteis;

import org.stackdrive.report.script.AbstractGitScript;
import org.stackdrive.tools.tool.config.ConfigUtils;
import java.util.*;

/**
 * Script to update properties
 */
public class UpdatePropertiesScript extends AbstractGitScript {

    /**
     * Updates properties
     * @param repo
     * @param sectionName
     * @param modifiedProperties
     * @return
     */
    public String updateProperties (String repo, String sectionName, List<String> modifiedProperties) {
        List<String> rl = new ArrayList<>();
        rl.add(repo);
        this.projectName = this.createProjectName(rl);
        this.cloneGit(rl);
        this.initConfigs();
        String pattern = this.getPatternForSection(sectionName);
        Set<Object> cs = this.loadConfigurable(pattern);
        this.initConfigurable(cs);
        List<Object> cl = this.sortConfigurableByTitle(cs);
        this.setConfigurable(cl, modifiedProperties);
        this.saveConfigs();
        String branch = "stackdrive_properties" + System.currentTimeMillis();
        this.createBranchAndPush(branch, "stackdrive properties updated");
        this.clearProjectDirectory();
        return "Создана новая ветка: " + branch;
    }

    /**
     * Defines and updates configurable state
     * @param cl
     * @param mpl
     */
    private void setConfigurable (List<Object> cl, List<String> mpl) {
        for (Object c : cl) {
            String fn = ConfigUtils.getConfigFieldName(c);
            String pn = c.getClass().getCanonicalName() + "." + fn;
            properties.setProperty(pn, "false");
        }
        for (String mp : mpl) {
            try {
                Object c = cl.get(Integer.parseInt(mp));
                String fn = ConfigUtils.getConfigFieldName(c);
                String pn = c.getClass().getCanonicalName() + "." + fn;
                properties.setProperty(pn, "true");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
