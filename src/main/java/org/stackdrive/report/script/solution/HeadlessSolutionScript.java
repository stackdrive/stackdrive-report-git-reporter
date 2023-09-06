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
package org.stackdrive.report.script.solution;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.stackdrive.report.script.AbstractGitScript;
import org.stackdrive.tools.tool.config.ConfigUtils;
import org.stackdrive.tools.tool.pattern.model.AbstractPatternValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackdrive.report.SolutionResult;
import org.stackdrive.model.SectionDTO;
import org.stackdrive.model.SectionMetadata;
import org.stackdrive.model.SolutionDTO;
import org.stackdrive.model.headless.SolutionStatus;
import org.stackdrive.model.tabbed.TabPane;
import org.stackdrive.model.views.table.CheckedTableRow;
import org.stackdrive.model.views.table.SettingsTable;
import org.stackdrive.model.views.table.TableColumn;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Script is used to make solution report
 */
public class HeadlessSolutionScript extends AbstractGitScript {

    private static final Logger LOG = LoggerFactory.getLogger(HeadlessSolutionScript.class);

    /**
     * Makes solution report
     * @return
     */
    public synchronized Object solutionReport (List<String> repositories) {
        try {
            this.projectName = this.createProjectName(repositories);
            this.cloneGit(repositories);
            LOG.info(">>> try to load solution");
            Object report = this.searchReport();
            if (report != null) {
                this.updateSolution((SolutionDTO) report, "Risks");
                this.clearProjectDirectory();
                return new SolutionResult((SolutionDTO) report, SolutionStatus.READY);
            } else {
                return new SolutionResult(new SolutionDTO(), SolutionStatus.FAIL);
            }
        } catch (Exception e) {
            this.clearProjectDirectory();
            return new SolutionResult(new SolutionDTO(), SolutionStatus.FAIL);
        }
    }

    /**
     * Try to load report from local data
     * @return
     */
    protected SolutionDTO searchReport() {
        LOG.info(">> searchReport maintenance json");
        String canonical = getBasePath() + "/.stackdrive/maintenance.json";
        if (! new File(canonical).exists()) {
            return null;
        }
        try (final FileReader json = new FileReader(canonical)) {
            final TypeToken<SolutionDTO> requestListTypeToken = new TypeToken<SolutionDTO>() {
            };
            final Gson gson = new GsonBuilder().registerTypeAdapter(TabPane.class, new TabPaneDeserializer()).create();
            return gson.fromJson(json, requestListTypeToken.getType());
        } catch (Exception e) {
            LOG.error("searchReport", e);
            return null;
        }
    }

    /**
     * Updates solution dto by properties in settings table
     *
     * @param solution
     * @param sectionName
     */
    protected void updateSolution(SolutionDTO solution, String sectionName) {
        LOG.info("Update solution for section: {}", sectionName);
        SectionDTO section = this.getSection(solution, sectionName);
        SectionMetadata smd = section.getSectionMetadata();
        smd.setHasOptions(true);
        List<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Правило", "cell1", "cell1"));
        List<CheckedTableRow> tableRows = new ArrayList<>();
        List<String> selectedRowKeys = new ArrayList<>();
        SettingsTable st = new SettingsTable(tableColumns, tableRows, selectedRowKeys);
        smd.setSettingsTable(st);
        this.initConfigs();
        String pattern = this.getPatternForSection(sectionName);
        Set<Object> cs = this.loadConfigurable(pattern);
        LOG.info("Loaded this properties: {}", cs.size());
        this.initConfigurable(cs);
        LOG.info("Inited this properties: {}", cs.size());
        List<Object> cl = this.sortConfigurableByTitle(cs);
        int index = 0;
        LOG.info("Will add to solution propertes: {}", cl.size());
        for (Object c : cl) {
            String title = ConfigUtils.getConfigurableTitle(c);
            if (c instanceof AbstractPatternValidator) {
                String[] labs = ((AbstractPatternValidator) c).getLabels();
                for (String lab : labs) {
                    title = title + " / ";
                    title = title + lab;
                }
            }
            CheckedTableRow ctr = new CheckedTableRow("empty", title, index + "");
            tableRows.add(ctr);
            if (ConfigUtils.isSelectedConfig(c)) {
                selectedRowKeys.add(index + "");
            }
            index++;
        }
    }

    /**
     * Returns section for name
     *
     * @param solution
     * @param sectionName
     * @return
     */
    private SectionDTO getSection(SolutionDTO solution, String sectionName) {
        Map<String, SectionDTO> sections = solution.getSectionList();
        return sections.get(sectionName);
    }
}
